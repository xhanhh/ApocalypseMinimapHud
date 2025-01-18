package top.ilov.mcmods.apocalypsehud.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import top.ilov.mcmods.apocalypsehud.utils.FMLUtils;

import java.util.List;
import java.util.Set;

public class ApocalypseHudMixinPlugin implements IMixinConfigPlugin {

    boolean IS_APOCALYPSE_LOADED = FMLUtils.isModLoaded("apocalypse");
    boolean IS_XEARO_LOADED = FMLUtils.isModLoaded("xaerominimap");

    @Override
    public void onLoad(String s) {

    }

    @Override
    public String getRefMapperConfig() {
        return "";
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals("top.ilov.mcmods.apocalypsehud.mixin.ApocalypseMixin")) {
            return IS_APOCALYPSE_LOADED;
        } else if (mixinClassName.equals("top.ilov.mcmods.apocalypsehud.mixin.minimaps.XaeroMixin")) {
            return IS_XEARO_LOADED;
        }
        return false;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
