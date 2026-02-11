package top.ilov.mcmods.apocalypsehud.integration.xaero;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import top.ilov.mcmods.apocalypsehud.ApocalypseMinimapHudMod;
import top.ilov.mcmods.apocalypsehud.utils.DifficultyUtils;
import xaero.hud.minimap.info.InfoDisplay;
import xaero.hud.minimap.info.InfoDisplay.Builder;
import xaero.hud.minimap.info.codec.InfoDisplayCommonStateCodecs;
import xaero.hud.minimap.info.widget.InfoDisplayCommonWidgetFactories;

public class DifficultyInfoDisplays {

    public static final Builder<Boolean> DIFFICULTY_BUILDER;
    public static InfoDisplay<Boolean> DIFFICULTY_DISPLAY;

    static {
        Minecraft mc = Minecraft.getInstance();

        Builder<Boolean> builder = Builder.begin();
        DIFFICULTY_BUILDER = builder
                .setId("apocalypse-difficulty")
                .setName(Component.translatable("xaerominimap.apocalypseminimaphud.infodisplay.apocalypse"))
                .setDefaultState(true)
                .setCodec(InfoDisplayCommonStateCodecs.BOOLEAN)
                .setWidgetFactory(InfoDisplayCommonWidgetFactories.OFF_ON)
                .setCompiler((displayInfo, compiler, session, availableWidth, playerPos) -> {
                    if (!Boolean.TRUE.equals(displayInfo.getState())) {
                        return;
                    }
                    if (ApocalypseMinimapHudMod.CONFIG == null || !ApocalypseMinimapHudMod.CONFIG.isEnableXaeroMinimapDifficultyDisplay()) {
                        return;
                    }

                    Player player = mc.player;
                    if (player == null || mc.level == null || mc.options.hideGui) {
                        return;
                    }

                    compiler.addLine(DifficultyUtils.getDisplayText(player));
                });
    }

}
