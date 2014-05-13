package autoarmor;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ClientTickHandler {

    @SubscribeEvent
    public void tickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
            onTickInGame();
        }
    }

	private void onTickInGame() {
		Minecraft mc = Minecraft.getMinecraft();
        if(mc == null) return;
        if(mc.thePlayer == null) return;
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

			Item item = itemStack.getItem();
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
