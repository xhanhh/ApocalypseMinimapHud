package top.ilov.mcmods.apocalypsehud.utils;

import com.toast.apocalypse.common.capability.CapabilityHelper;
import com.toast.apocalypse.common.util.References;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import top.ilov.mcmods.apocalypsehud.ApocalypseMinimapHudMod;

public class DifficultyUtils {

    public static long COLOR_CHANGE;
    public static final int[] COLORS = {
            0xFFFFFF, 0x88FFFF, 0x88FF88, 0xFFFF88, 0xFFBB88, 0xFF8888
    };

    public static MutableComponent getDisplayText(Player player) {

        long difficulty = CapabilityHelper.getPlayerDifficulty(player);
        double difficultyRate = CapabilityHelper.getPlayerDifficultyMult(player);
        long maxDifficulty = CapabilityHelper.getMaxPlayerDifficulty(player);

        if (maxDifficulty > -1) {
            COLOR_CHANGE = maxDifficulty;
        } else {
            COLOR_CHANGE = References.DEFAULT_COLOR_CHANGE;
        }

        int partialDifficulty = difficulty <= 0 ? 0 : (int) (difficulty % 24000L / 2400);

        int color = COLORS[0];
        if (difficulty >= 0L) {
            if (difficulty >= COLOR_CHANGE) {
                color = COLORS[COLORS.length - 1];
            }
            else {
                color = COLORS[(int) (difficulty / (double) COLOR_CHANGE * COLORS.length)];
            }
        }

        difficulty /= 24000L;
        String parsedDifficulty = difficulty > 0L ? (difficulty + "." + partialDifficulty) : "0.0";

        Style colorStyle;
        if (!ApocalypseMinimapHudMod.CONFIG.isDisableDifficultyDisplayColor()) {
            colorStyle = Style.EMPTY.withColor(color);
        } else {
            colorStyle = Style.EMPTY.withColor(COLORS[0]);
        }

        MutableComponent difficultyText = Component.translatable("apocalypseminimaphud.info.difficulty", parsedDifficulty)
                .withStyle(colorStyle);

        if (difficultyRate != 1.0 && !ApocalypseMinimapHudMod.CONFIG.isHideDifficultyRate()) {
            String rateText = Component.translatable("apocalypseminimaphud.info.difficulty_rate",
                    (int) (difficultyRate * 100) + "%").getString();
            difficultyText.append(" ").append(Component.literal(rateText)).withStyle(colorStyle);;
        }

        return difficultyText;
    }

}
