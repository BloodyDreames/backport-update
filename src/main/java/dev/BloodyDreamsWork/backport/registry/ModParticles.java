package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, Backport.MODID);

    public static final RegistryObject<SimpleParticleType> RED_POPLAR_LEAVES =
            PARTICLES.register("red_poplar_leaves", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> ORANGE_POPLAR_LEAVES =
            PARTICLES.register("orange_poplar_leaves", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> YELLOW_POPLAR_LEAVES =
            PARTICLES.register("yellow_poplar_leaves", () -> new SimpleParticleType(false));

    public static void register(IEventBus modEventBus) {
        PARTICLES.register(modEventBus);
    }

    private ModParticles() {
    }
}
