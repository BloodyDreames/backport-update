package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorStructureStateMixin {

    private static final ResourceLocation ABANDONED_CAMP =
            new ResourceLocation(Backport.MODID, "abandoned_camp");

    @Inject(method = "possibleStructureSets", at = @At("RETURN"), cancellable = true)
    private void backport$dropDisabledStructures(
            CallbackInfoReturnable<Stream<Holder<StructureSet>>> callback) {
        if (BackportConfig.generateAbandonedCamp()) {
            return;
        }
        callback.setReturnValue(callback.getReturnValue()
                .filter(set -> set.unwrapKey()
                        .map(key -> !key.location().equals(ABANDONED_CAMP))
                        .orElse(true)));
    }
}
