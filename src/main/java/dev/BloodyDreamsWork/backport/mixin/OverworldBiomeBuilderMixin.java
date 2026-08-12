package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.BackportConfig;
import dev.BloodyDreamsWork.backport.content.VanillaRegistryContext;
import dev.BloodyDreamsWork.backport.worldgen.ModBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraftforge.data.loading.DatagenModLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {

    private static final int COOL_TEMPERATURE = 1;

    private static final int NEUTRAL_HUMIDITY = 2;

    @Shadow
    @Final
    private ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void backport$addDappledForest(CallbackInfo callback) {
        if (VanillaRegistryContext.isBuilding() || DatagenModLoader.isRunningDataGen()
                || !BackportConfig.generateDappledForest()) {
            return;
        }
        this.MIDDLE_BIOMES_VARIANT[COOL_TEMPERATURE][NEUTRAL_HUMIDITY] = ModBiomes.DAPPLED_FOREST;
    }
}
