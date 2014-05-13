package autoarmor;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentTranslation;
import org.lwjgl.input.Keyboard;

public class ModeKeyHandler {

	static KeyBinding modeKeyBinding = new KeyBinding("AutoArmor", Keyboard.KEY_M, "AutoArmor");

    public ModeKeyHandler() {
        ClientRegistry.registerKeyBinding(modeKeyBinding);
    }

    @SubscribeEvent
    public void KeyInputEvent(InputEvent.KeyInputEvent event) {
        if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
            if(modeKeyBinding.isPressed()) {
                AutoArmor.toggleMode();
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentTranslation("AutoArmor " + AutoArmor.getMode().toString()));
            }
        }
    }
}
