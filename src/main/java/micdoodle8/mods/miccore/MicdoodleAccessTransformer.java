package micdoodle8.mods.miccore;

import net.minecraftforge.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

public class MicdoodleAccessTransformer extends AccessTransformer
{

    public MicdoodleAccessTransformer() throws IOException
    {
        super("assets/micdoodlecore_at.cfg");
    }
}
