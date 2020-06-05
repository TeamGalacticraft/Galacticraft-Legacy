package micdoodle8.mods.galacticraft.core.client.fx;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleSmokeUnlaunchedLarge extends ParticleSmokeUnlaunched
{
    public ParticleSmokeUnlaunchedLarge(World world, Vector3 position, Vector3 motion, IAnimatedSprite sprite)
    {
        super(world, position, motion, 2.5F, sprite);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType>
    {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleSmokeUnlaunchedLarge(worldIn, new Vector3(x, y, z), new Vector3(xSpeed, ySpeed, zSpeed), this.spriteSet);
        }
    }
}