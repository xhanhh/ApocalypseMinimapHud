package top.ilov.mcmods.apocalypsehud.mixin;

import com.toast.apocalypse.client.renderer.DifficultyOverlayRenderHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.ilov.mcmods.apocalypsehud.ApocalypseMinimapHudMod;

@Mixin(DifficultyOverlayRenderHandler.class)
public class ApocalypseMixin {

    @Inject(method = "renderDifficulty", at = @At("HEAD"), cancellable = true)
    private static void renderDifficulty(ForgeGui gui, GuiGraphics guiGraphics,
                                         int width, int height, CallbackInfo ci) {
        if (!ApocalypseMinimapHudMod.CONFIG.isEnableApocalypseDisplayText()) {
            ci.cancel();
            return;
        }
    }
}
