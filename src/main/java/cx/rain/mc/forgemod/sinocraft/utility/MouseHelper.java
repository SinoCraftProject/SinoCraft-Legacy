package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MouseHelper {

    private static boolean main = false;
    private static boolean lastMain = false;
    private static boolean off = false;
    private static boolean lastOff = false;
    private static boolean middle = false;
    private static boolean lastMiddle = false;

    public static void update() {
        GameSettings settings = Minecraft.getInstance().gameSettings;
        lastMain = main;
        main = settings.keyBindAttack.isKeyDown();
        lastOff = off;
        off = settings.keyBindUseItem.isKeyDown();
        lastMiddle = middle;
        middle = settings.keyBindPickBlock.isKeyDown();
    }

    public static boolean isMainKeyPressed() {
        return main;
    }

    public static boolean isMainKeyHolding() {
        return main && lastMain;
    }

    public static boolean isSecondaryKeyPressed() {
        return off;
    }

    public static boolean isSecondaryKeyHolding() {
        return off && lastOff;
    }

    public static boolean isMiddleKeyPressed() {
        return middle;
    }

    public static boolean isMiddleKeyHolding() {
        return middle && lastMiddle;
    }

    public static boolean isMousePressed(Hand hand) {
        return hand == Hand.MAIN_HAND ? isMainKeyPressed() : isSecondaryKeyPressed();
    }

    public static boolean isMouseHolding(Hand hand) {
        return hand == Hand.MAIN_HAND ? isMainKeyHolding() : isSecondaryKeyHolding();
    }

    public static boolean isMouseNotHolding(Hand hand) {
        return isMousePressed(hand) && !isMouseHolding(hand);
    }
}
