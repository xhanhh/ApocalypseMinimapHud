package top.ilov.mcmods.apocalypsehud.integration.xaero;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import top.ilov.mcmods.apocalypsehud.utils.DifficultyUtils;
import xaero.common.minimap.info.InfoDisplay;
import xaero.common.minimap.info.codec.InfoDisplayCommonStateCodecs;
import xaero.common.minimap.info.widget.InfoDisplayCommonWidgetFactories;

import java.util.ArrayList;
import java.util.List;

public class DifficultyInfoDisplays {

    public static final InfoDisplay<Boolean> DIFFICULTY_DISPLAY;
    private static final List<InfoDisplay<?>> ALL = new ArrayList<>();

    static {
        Minecraft mc = Minecraft.getInstance();


        DIFFICULTY_DISPLAY = new InfoDisplay<>("apocalypse-difficulty",
                Component.translatable("xaerominimap.apocalypseminimaphud.infodisplay.apocalypse"),
                true,
                InfoDisplayCommonStateCodecs.BOOLEAN,
                InfoDisplayCommonWidgetFactories.OFF_ON,
                (displayInfo, compiler, session, processor, x, y, w, h, scale, size, playerBlockX, playerBlockY, playerBlockZ, playerPos) -> {
                    if (displayInfo.getState()) {
                        Player player = mc.player;
                        if (player != null && mc.level != null && !mc.options.hideGui) {
                            compiler.addLine(DifficultyUtils.getDisplayText(player));
                        }
                    }
                },
                ALL
        );
    }

}
