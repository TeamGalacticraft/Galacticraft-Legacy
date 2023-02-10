/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.util.list;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialObject;

public class CelestialList<T extends CelestialObject> extends ArrayList<T>
{

    private static final long serialVersionUID = 1L;
    private Map<String, T>    celestialListMap;

    public static <E extends CelestialObject> CelestialList<E> create()
    {
        return new CelestialList<>();
    }

    public CelestialList()
    {
        super();
        this.celestialListMap = new HashMap<>();
    }

    @Override
    public boolean add(T e)
    {
        if (this.contains(e))
        {
            return false;
        }
        this.celestialListMap.put(e.getName(), e);
        return super.add(e);
    }

    public ImmutableCelestialList<T> toImmutableList()
    {
        return new ImmutableCelestialList<>(this);
    }
    
    public Map<String, T> getRegistered()
    {
        return ImmutableMap.copyOf(this.celestialListMap);
    }
}
