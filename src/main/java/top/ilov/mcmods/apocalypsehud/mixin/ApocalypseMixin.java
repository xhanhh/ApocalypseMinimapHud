package top.ilov.mcmods.apocalypsehud.mixin;

import com.toast.apocalypse.client.event.DifficultyRenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.ilov.mcmods.apocalypsehud.ApocalypseMinimapHudMod;

@Mixin(DifficultyRenderHandler.class)
public class ApocalypseMixin {

    @Inject(method = "renderDifficulty", at = @At("HEAD"), cancellable = true)
    private static void onRenderDifficulty(RenderGuiOverlayEvent.Post event, Minecraft minecraft, CallbackInfo ci) {
        if (!ApocalypseMinimapHudMod.CONFIG.isEnableApocalypseDisplayText()) {
            ci.cancel();
            return;
        }
    }
}
