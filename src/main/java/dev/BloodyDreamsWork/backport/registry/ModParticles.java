package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModParticles {
    public static final ModRegister<ParticleType<?>> PARTICLES =
            ModRegister.create(BuiltInRegistries.PARTICLE_TYPE);

    public static final ModRegister.Entry<SimpleParticleType> RED_POPLAR_LEAVES =
            PARTICLES.register("red_poplar_leaves", FabricParticleTypes::simple);

    public static final ModRegister.Entry<SimpleParticleType> ORANGE_POPLAR_LEAVES =
            PARTICLES.register("orange_poplar_leaves", FabricParticleTypes::simple);

    public static final ModRegister.Entry<SimpleParticleType> YELLOW_POPLAR_LEAVES =
            PARTICLES.register("yellow_poplar_leaves", FabricParticleTypes::simple);

    public static void register() {
    }

    private ModParticles() {
    }
}
