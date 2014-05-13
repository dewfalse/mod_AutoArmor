package autoarmor;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "AutoArmor", name = "AutoArmor", version = "1.0")
public class AutoArmor {
	@SidedProxy(clientSide = "autoarmor.ClientProxy", serverSide = "autoarmor.CommonProxy")
	public static CommonProxy proxy;

	enum EnumMode {ALWAYS, HOTBAR, INVENTORY, STOP};
	public static EnumMode mode = EnumMode.HOTBAR;

    @Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

    @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	public static void toggleMode() {
		mode = EnumMode.values()[(mode.ordinal()+1) % EnumMode.values().length];
	}

	public static EnumMode getMode() {
		return mode;
	}
}
