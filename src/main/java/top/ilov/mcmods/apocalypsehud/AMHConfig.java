package top.ilov.mcmods.apocalypsehud;

import com.google.gson.Gson;
import lombok.Data;
import top.ilov.mcmods.apocalypsehud.utils.FMLUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * From CakeDelight
 */
@Data
public final class AMHConfig {

    public AMHConfig() {
    }

    private boolean enableApocalypseDisplayText;
    private boolean hideDifficultyRate;
    private boolean disableDifficultyDisplayColor;

    static File config = new File(FMLUtils.getConfigDir().toFile(), "apocalypseminimaphud-client.json");

    public static AMHConfig loadConfig() {

        AMHConfig amhConfig = new AMHConfig();

        if (!config.exists()) {
            write(amhConfig);
        }

        BufferedReader reader;
        try {
            reader = Files.newBufferedReader(config.toPath());
            Gson gson = new Gson();
            amhConfig = gson.fromJson(reader, AMHConfig.class);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return amhConfig;

    }

    public static void write(AMHConfig cakeConfig) {

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(config);
            Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(cakeConfig));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
