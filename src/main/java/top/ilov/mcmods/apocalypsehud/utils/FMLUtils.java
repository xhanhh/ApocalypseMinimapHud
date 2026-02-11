package top.ilov.mcmods.apocalypsehud.utils;

import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;
import org.spongepowered.asm.service.MixinService;

import java.io.IOException;
import java.nio.file.Path;

@UtilityClass
public class FMLUtils {

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isClassPresent(String className) {
        try {
            MixinService.getService().getBytecodeProvider().getClassNode(className);
            return true;
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
    }

}
