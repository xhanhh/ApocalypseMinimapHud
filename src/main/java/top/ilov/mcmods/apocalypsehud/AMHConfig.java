package top.ilov.mcmods.apocalypsehud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.SneakyThrows;
import top.ilov.mcmods.apocalypsehud.utils.FMLUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Data
public final class AMHConfig {

    public AMHConfig() {
    }

    private boolean enableApocalypseDisplayText;
    private boolean hideDifficultyRate;
    private boolean disableDifficultyDisplayColor;
    private boolean enableXaeroMinimapDifficultyDisplay;

    static File config = new File(FMLUtils.getConfigDir().toFile(), "apocalypseminimaphud-client.json");

    private static final boolean DEFAULT_ENABLE_APOCALYPSE_DISPLAY_TEXT = false;
    private static final boolean DEFAULT_HIDE_DIFFICULTY_RATE = false;
    private static final boolean DEFAULT_DISABLE_DIFFICULTY_DISPLAY_COLOR = false;

    @SneakyThrows
    public static AMHConfig loadConfig() {

        AMHConfig defaultConfig = new AMHConfig();
        defaultConfig.enableApocalypseDisplayText = DEFAULT_ENABLE_APOCALYPSE_DISPLAY_TEXT;
        defaultConfig.hideDifficultyRate = DEFAULT_HIDE_DIFFICULTY_RATE;
        defaultConfig.disableDifficultyDisplayColor = DEFAULT_DISABLE_DIFFICULTY_DISPLAY_COLOR;

        if (!config.exists()) {
            write(defaultConfig);
            return defaultConfig;
        }

        JsonObject json;
        try (BufferedReader reader = Files.newBufferedReader(config.toPath(), StandardCharsets.UTF_8)) {
            json = JsonParser.parseReader(reader).getAsJsonObject();
        }

        AMHConfig loaded = new Gson().fromJson(json, AMHConfig.class);
        if (loaded == null) {
            loaded = defaultConfig;
        }

        boolean changed = false;
        if (!json.has("enableApocalypseDisplayText")) {
            loaded.enableApocalypseDisplayText = DEFAULT_ENABLE_APOCALYPSE_DISPLAY_TEXT;
            changed = true;
        }
        if (!json.has("hideDifficultyRate")) {
            loaded.hideDifficultyRate = DEFAULT_HIDE_DIFFICULTY_RATE;
            changed = true;
        }
        if (!json.has("disableDifficultyDisplayColor")) {
            loaded.disableDifficultyDisplayColor = DEFAULT_DISABLE_DIFFICULTY_DISPLAY_COLOR;
            changed = true;
        }

        if (changed) {
            write(loaded);
        }

        return loaded;

    }

    @SneakyThrows
    public static void write(AMHConfig amhConfig) {

        Files.createDirectories(config.toPath().getParent());
        try (Writer writer = Files.newBufferedWriter(config.toPath(), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create();
            writer.write(gson.toJson(amhConfig));
        }

    }

}