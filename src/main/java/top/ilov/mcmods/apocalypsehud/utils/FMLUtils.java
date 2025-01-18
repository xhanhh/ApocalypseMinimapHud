package top.ilov.mcmods.apocalypsehud.utils;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class FMLUtils {

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

}
