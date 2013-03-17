package autoarmor;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.CLIENT))) {
			GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
			if (guiscreen == null) {
				onTickInGame();
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	private void onTickInGame() {
		Minecraft mc = Minecraft.getMinecraft();
		int start = 0;
		int size = 0;
		switch(AutoArmor.getMode()) {
		case ALWAYS:
			size = mc.thePlayer.inventory.getSizeInventory();
			break;
		case HOTBAR:
			size = mc.thePlayer.inventory.getHotbarSize();
			break;
		case INVENTORY:
			start = mc.thePlayer.inventory.getHotbarSize();
			size = mc.thePlayer.inventory.getSizeInventory() - mc.thePlayer.inventory.getHotbarSize();
			break;
		}
		for(int i = start; i < size; ++i) {
			ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
			if(itemStack == null) continue;

			Item item = Item.itemsList[itemStack.itemID];
			if(item == null) continue;
			if(item instanceof ItemArmor == false) continue;

			int index = 3 - ((ItemArmor) item).armorType;
			if(mc.thePlayer.inventory.armorInventory[index] != null) continue;

			mc.thePlayer.inventory.armorInventory[index] = itemStack;
			mc.thePlayer.inventory.setInventorySlotContents(i, null);
		}
		mc.thePlayer.inventoryContainer.detectAndSendChanges();
	}

}
