package micdoodle8.mods.galacticraft.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import micdoodle8.mods.galacticraft.annotations.ForRemoval;
import micdoodle8.mods.galacticraft.annotations.ReplaceWith;
import micdoodle8.mods.galacticraft.core.Constants;

@Deprecated
@ForRemoval(deadline = "4.1.0")
@ReplaceWith("Use Your Own Log")
public class GCLog
{

    private static Logger log = LogManager.getFormatterLogger(Constants.MOD_NAME_SIMPLE);

    public static void info(String message)
    {
        log.info(message);

    }

    public static void error(String message)
    {
        log.error(message);
    }
    
    public static void error(Exception e, String message)
    {
        log.error(message, e);
    }

    public static void debug(String message)
    {
        if (ConfigManagerCore.enableDebug)
        {
            log.debug(message);
        }
    }

    public static void exception(Exception e)
    {
        log.catching(e);
    }
    
    public static void severe(String message)
    {
        error(message);
    }
}
