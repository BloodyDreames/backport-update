package dev.BloodyDreamsWork.backport.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class PoplarLeavesParticle {

    private static final float GRAVITY = 0.25F;

    private static final float WIND = 2.0F;

    public record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed,
                                       RandomSource random) {
            return new FallingLeavesParticle(level, x, y, z, this.sprites.get(random),
                    GRAVITY, WIND, false, true, 1.0F, 0.0F);
        }
    }

    private PoplarLeavesParticle() {
    }
}
