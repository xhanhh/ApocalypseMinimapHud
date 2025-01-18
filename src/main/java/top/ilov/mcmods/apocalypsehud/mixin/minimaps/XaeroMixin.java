package top.ilov.mcmods.apocalypsehud.mixin.minimaps;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.ilov.mcmods.apocalypsehud.integration.xaero.DifficultyInfoDisplays;
import xaero.common.minimap.info.BuiltInInfoDisplays;
import xaero.common.minimap.info.InfoDisplayManager;

@Mixin(BuiltInInfoDisplays.class)
public class XaeroMixin {

    @Inject(method = "addToManager", at = @At("HEAD"), remap = false)
    private static void addToManager(InfoDisplayManager manager, CallbackInfo ci) {
        manager.add(DifficultyInfoDisplays.DIFFICULTY_DISPLAY);
    }
}
