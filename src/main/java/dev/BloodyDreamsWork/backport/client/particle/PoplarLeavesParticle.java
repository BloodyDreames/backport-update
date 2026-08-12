package dev.BloodyDreamsWork.backport.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PoplarLeavesParticle extends TextureSheetParticle {
    private static final int SPRITE_COUNT = 4;
    private static final int INITIAL_LIFETIME = 300;
    private static final float ACCELERATION_SCALE = 0.0025F;
    private static final float WIND_BIG = 2.0F;

    private final float particleRandom;
    private final float spinAcceleration;
    private float rotSpeed;

    protected PoplarLeavesParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        super(level, x, y, z);
        this.setSprite(spriteSet.get(this.random.nextInt(SPRITE_COUNT), SPRITE_COUNT));
        this.rotSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.particleRandom = this.random.nextFloat();
        this.spinAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
        this.lifetime = INITIAL_LIFETIME;
        this.gravity = 7.5E-4F;
        float size = this.random.nextBoolean() ? 0.05F : 0.075F;
        this.quadSize = size;
        this.setSize(size, size);
        this.friction = 1.0F;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
            return;
        }

        float elapsed = INITIAL_LIFETIME - this.lifetime;
        float progress = Math.min(elapsed / INITIAL_LIFETIME, 1.0F);
        double angle = Math.toRadians(this.particleRandom * 60.0F);
        double drift = Math.pow(progress, 1.25) * WIND_BIG;

        this.xd += Math.cos(angle) * drift * ACCELERATION_SCALE;
        this.zd += Math.sin(angle) * drift * ACCELERATION_SCALE;
        this.yd -= this.gravity;

        this.rotSpeed += this.spinAcceleration / 20.0F;
        this.oRoll = this.roll;
        this.roll += this.rotSpeed / 20.0F;

        this.move(this.xd, this.yd, this.zd);

        if (this.onGround || this.lifetime < INITIAL_LIFETIME - 1 && (this.xd == 0.0 || this.zd == 0.0)) {
            this.remove();
            return;
        }

        this.xd *= this.friction;
        this.yd *= this.friction;
        this.zd *= this.friction;
    }

    @Environment(EnvType.CLIENT)
    public record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new PoplarLeavesParticle(level, x, y, z, this.sprites);
        }
    }
}
