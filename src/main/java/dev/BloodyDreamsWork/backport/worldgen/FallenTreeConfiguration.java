package dev.BloodyDreamsWork.backport.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public record FallenTreeConfiguration(BlockStateProvider trunkProvider,
                                      IntProvider logLength,
                                      List<TreeDecorator> stumpDecorators,
                                      List<TreeDecorator> logDecorators) implements FeatureConfiguration {

    public static final Codec<FallenTreeConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("trunk_provider")
                    .forGetter(FallenTreeConfiguration::trunkProvider),
            IntProviders.codec(1, 16).fieldOf("log_length")
                    .forGetter(FallenTreeConfiguration::logLength),
            TreeDecorator.CODEC.listOf().fieldOf("stump_decorators")
                    .forGetter(FallenTreeConfiguration::stumpDecorators),
            TreeDecorator.CODEC.listOf().fieldOf("log_decorators")
                    .forGetter(FallenTreeConfiguration::logDecorators)
    ).apply(instance, FallenTreeConfiguration::new));
}
