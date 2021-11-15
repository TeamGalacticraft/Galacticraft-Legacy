package micdoodle8.mods.miccore;

import net.minecraftforge.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

public class MicdoodleAccessTransformerDeObf extends AccessTransformer
{

    public MicdoodleAccessTransformerDeObf() throws IOException
    {
        super("META-INF/accesstransformer.deobf");
    }
}
