package dev.BloodyDreamsWork.backport.mixin;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.BackportConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ChunkGeneratorStructureState.class)
public class ChunkGeneratorStructureStateMixin {

    private static final ResourceLocation ABANDONED_CAMP =
            ResourceLocation.fromNamespaceAndPath(Backport.MODID, "abandoned_camp");

    @Shadow
    @Final
    @Mutable
    private List<Holder<StructureSet>> possibleStructureSets;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void backport$dropDisabledStructures(CallbackInfo callback) {
        if (BackportConfig.generateAbandonedCamp()) {
            return;
        }
        this.possibleStructureSets = this.possibleStructureSets.stream()
                .filter(set -> set.unwrapKey()
                        .map(key -> !key.location().equals(ABANDONED_CAMP))
                        .orElse(true))
                .toList();
    }
}
