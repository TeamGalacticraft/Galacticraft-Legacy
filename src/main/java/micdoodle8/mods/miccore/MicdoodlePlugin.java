package micdoodle8.mods.miccore;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.IFMLCallHook;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions(value =
{"micdoodle8.mods.miccore"})
public class MicdoodlePlugin implements IFMLLoadingPlugin, IFMLCallHook
{

    public static boolean hasRegistered = false;
    public static final String mcVersion = MinecraftForge.MC_VERSION;
    public static File mcDir;
    public static File canonicalConfigDir;

    @Override
    public String[] getASMTransformerClass()
    {
        final String[] asmStrings = new String[]
        {"micdoodle8.mods.miccore.MicdoodleTransformer"};

        if (!MicdoodlePlugin.hasRegistered)
        {
            final List<String> asm = Arrays.asList(asmStrings);

            for (final String s : asm)
            {
                try
                {
                    final Class<?> c = Class.forName(s);

                    if (c != null)
                    {
                        System.out.println("Successfully Registered Transformer");
                    }
                } catch (final Exception ex)
                {
                    System.out.println("Error while running transformer " + s);
                    return null;
                }
            }

            MicdoodlePlugin.hasRegistered = true;
        }

        return asmStrings;
    }

    @Override
    public String getModContainerClass()
    {
        return "micdoodle8.mods.miccore.MicdoodleModContainer";
    }

    @Override
    public String getSetupClass()
    {
        return "micdoodle8.mods.miccore.MicdoodlePlugin";
    }

    @Override
    public Void call() throws Exception
    {
        return null;
    }

    private static Constructor<?> sleepCancelledConstructor;
    private static Constructor<?> orientCameraConstructor;
    private static String eventContainerClass = "micdoodle8.mods.galacticraft.core.event.EventHandlerGC";

    public static void onSleepCancelled()
    {
        try
        {
            if (MicdoodlePlugin.sleepCancelledConstructor == null)
            {
                MicdoodlePlugin.sleepCancelledConstructor = Class.forName(MicdoodlePlugin.eventContainerClass + "$SleepCancelledEvent").getConstructor();
            }

            MinecraftForge.EVENT_BUS.post((Event) MicdoodlePlugin.sleepCancelledConstructor.newInstance());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void orientCamera()
    {
        try
        {
            if (MicdoodlePlugin.orientCameraConstructor == null)
            {
                MicdoodlePlugin.orientCameraConstructor = Class.forName(MicdoodlePlugin.eventContainerClass + "$OrientCameraEvent").getConstructor();
            }

            MinecraftForge.EVENT_BUS.post((Event) MicdoodlePlugin.orientCameraConstructor.newInstance());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getAccessTransformerClass()
    {
        boolean deobfuscated = true;

        try
        {
            deobfuscated = Launch.classLoader.getClassBytes("net.minecraft.world.World") != null;
        } catch (final Exception e)
        {
        }

        if (deobfuscated)
        {
            return "micdoodle8.mods.miccore.MicdoodleAccessTransformerDeObf";
        }
        return "micdoodle8.mods.miccore.MicdoodleAccessTransformer";
    }

    @Override
    public void injectData(Map<String, Object> data)
    {

    }
}
