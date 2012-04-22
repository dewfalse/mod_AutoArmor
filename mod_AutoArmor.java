package net.minecraft.src;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Slot;


public class mod_AutoArmor extends BaseMod {

	@MLProp(info = "Setting for SinglePlay")
	public static boolean itemslot = true;

	@Override
	public boolean onTickInGame(float tick, Minecraft game) {
		int i = itemslot ? 0 : 9;
		for(; i < game.thePlayer.inventory.mainInventory.length; i++) {
			ItemStack itemStack = game.thePlayer.inventory.mainInventory[i];
			if(itemStack == null) {
				continue;
			}
			if(Item.itemsList[itemStack.itemID] instanceof ItemArmor == false) {
				continue;
			}

			ItemArmor armor = (ItemArmor) Item.itemsList[itemStack.itemID];
			int slotIndex = 3 - armor.armorType;
			if(game.thePlayer.inventory.armorInventory[slotIndex] == null) {
				game.thePlayer.inventory.setInventorySlotContents(game.thePlayer.inventory.mainInventory.length + slotIndex, itemStack);
				game.thePlayer.inventory.setInventorySlotContents(i, null);
			}
			game.thePlayer.inventory.onInventoryChanged();

		}

		return true;
	}


	@Override
	public String getVersion() {
		return "[1.2.5] AutoArmor 0.0.1";
	}

	@Override
	public void load() {
		ModLoader.setInGameHook(this, true, true);
	}

}
