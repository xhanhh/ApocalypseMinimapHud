package top.ilov.mcmods.apocalypsehud;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ilov.mcmods.apocalypsehud.integration.clothconfig.ClothConfig;

@Mod(ApocalypseMinimapHudMod.MOD_ID)
public class ApocalypseMinimapHudMod {

    public static final String MOD_ID = "apocalypseminimaphud";

    public static final Logger LOGGER = LoggerFactory.getLogger("ApocalypseMinimapHud");

    public static AMHConfig CONFIG = new AMHConfig();

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public ApocalypseMinimapHudMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::clientSetup);

        CONFIG = AMHConfig.loadConfig();


        MinecraftForge.EVENT_BUS.register(this);

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> ClothConfig.genConfigScreen(screen)));
    }

}
