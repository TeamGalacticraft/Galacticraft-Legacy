package micdoodle8.mods.galacticraft.planets.asteroids.client;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FluidTexturesGC
{
    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new FluidTexturesGC());
    }
}
