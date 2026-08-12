package dev.BloodyDreamsWork.backport.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;

@Mixin(TreeGrower.class)
public interface TreeGrowerAccessor {

    @Accessor("tree")
    Optional<ResourceKey<ConfiguredFeature<?, ?>>> backport$tree();

    @Accessor("secondaryTree")
    Optional<ResourceKey<ConfiguredFeature<?, ?>>> backport$secondaryTree();

    @Accessor("flowers")
    Optional<ResourceKey<ConfiguredFeature<?, ?>>> backport$flowers();

    @Accessor("megaTree")
    Optional<ResourceKey<ConfiguredFeature<?, ?>>> backport$megaTree();
}
