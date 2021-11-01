package micdoodle8.mods.miccore;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;

import java.io.File;

public class ConfigManagerMicCore
{

    public static boolean loaded;

    static Configuration configuration;

    public static boolean enableSmallMoons;
    public static boolean enableDebug;

    public static void init()
    {
        if (!ConfigManagerMicCore.loaded)
        {
            ConfigManagerMicCore.configuration = new Configuration(new File(MicdoodlePlugin.canonicalConfigDir, "Galacticraft/miccore.conf"));
        }

        ConfigManagerMicCore.configuration.load();
        ConfigManagerMicCore.syncConfig();
    }

    public static void syncConfig()
    {
        try
        {
            ConfigManagerMicCore.enableSmallMoons = ConfigManagerMicCore.configuration
                .get(Configuration.CATEGORY_GENERAL, "Enable Small Moons", true, "This will cause some dimensions to appear round, disable if render transformations cause a conflict.")
                .getBoolean(true);
            ConfigManagerMicCore.enableDebug = ConfigManagerMicCore.configuration
                .get(Configuration.CATEGORY_GENERAL, "Enable Debug messages", false, "Enable debug messages during Galacticraft bytecode injection at startup.").getBoolean(false);
        } catch (final Exception e)
        {
            FMLLog.severe("Problem loading core config (\"miccore.conf\")");
        } finally
        {
            if (ConfigManagerMicCore.configuration.hasChanged())
            {
                ConfigManagerMicCore.configuration.save();
            }

            ConfigManagerMicCore.loaded = true;
        }
    }
}
