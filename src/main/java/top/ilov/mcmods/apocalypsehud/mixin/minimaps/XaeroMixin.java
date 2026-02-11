package top.ilov.mcmods.apocalypsehud.mixin.minimaps;

import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.ilov.mcmods.apocalypsehud.integration.xaero.DifficultyInfoDisplays;
import xaero.hud.minimap.info.BuiltInInfoDisplays;
import xaero.hud.minimap.info.InfoDisplay;

import java.util.List;
import java.util.Objects;

@Mixin(value = BuiltInInfoDisplays.class, remap = false)
public class XaeroMixin {

    @Shadow
    private static List<InfoDisplay<?>> ALL;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onClinit(CallbackInfo ci) {
        Objects.requireNonNull(ALL);
        DifficultyInfoDisplays.DIFFICULTY_DISPLAY = DifficultyInfoDisplays.DIFFICULTY_BUILDER
                .setDestination(ALL::add)
                .build();
    }
}
