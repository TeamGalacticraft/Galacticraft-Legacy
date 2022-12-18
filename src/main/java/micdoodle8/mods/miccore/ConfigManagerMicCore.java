/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.miccore;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

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
            MicdoodlePlugin.miccoreLogger.error("Problem loading core config (\"miccore.conf\")");
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
