package top.ilov.mcmods.apocalypsehud.integration.clothconfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import top.ilov.mcmods.apocalypsehud.AMHConfig;
import top.ilov.mcmods.apocalypsehud.ApocalypseMinimapHudMod;

public class ClothConfig {

    public static Screen genConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(Component.translatable("config.apocalypseminimaphud.title"))
                .setParentScreen(parent)
                .setSavingRunnable(() -> AMHConfig.write(ApocalypseMinimapHudMod.CONFIG));

        ConfigCategory client = builder.getOrCreateCategory(Component.translatable("config.apocalypseminimaphud.client"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        client.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("config.apocalypseminimaphud.enable_apocalypse_display_text"),
                        ApocalypseMinimapHudMod.CONFIG.isEnableApocalypseDisplayText())
                .setDefaultValue(false)
                .setTooltip(Component.translatable("config.apocalypseminimaphud.enable_apocalypse_display_text.tooltip"))
                .setSaveConsumer(newValue -> ApocalypseMinimapHudMod.CONFIG.setEnableApocalypseDisplayText(newValue))
                .build());

        client.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("config.apocalypseminimaphud.enable_xaero_minimap_difficulty_display"),
                        ApocalypseMinimapHudMod.CONFIG.isEnableXaeroMinimapDifficultyDisplay())
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> ApocalypseMinimapHudMod.CONFIG.setEnableXaeroMinimapDifficultyDisplay(newValue))
                .build());

        client.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("config.apocalypseminimaphud.hide_difficulty_rate"),
                        ApocalypseMinimapHudMod.CONFIG.isHideDifficultyRate())
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ApocalypseMinimapHudMod.CONFIG.setHideDifficultyRate(newValue))
                .build());

        client.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("config.apocalypseminimaphud.disable_difficulty_display_color"),
                        ApocalypseMinimapHudMod.CONFIG.isDisableDifficultyDisplayColor())
                .setDefaultValue(false)
                .requireRestart()
                .setSaveConsumer(newValue -> ApocalypseMinimapHudMod.CONFIG.setDisableDifficultyDisplayColor(newValue))
                .build());

        return builder.build();
    }

}
