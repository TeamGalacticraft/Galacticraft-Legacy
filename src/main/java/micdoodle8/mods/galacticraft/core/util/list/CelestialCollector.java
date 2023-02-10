/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.util.list;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialObject;

public class CelestialCollector<T extends CelestialObject> implements Collector<T, CelestialList<T>, ImmutableCelestialList<T>>
{

    public static <T extends CelestialObject> CelestialCollector<T> toImmutableList()
    {
        return new CelestialCollector<>();
    }

    @Override
    public Supplier<CelestialList<T>> supplier()
    {
        return CelestialList::new;
    }

    @Override
    public BiConsumer<CelestialList<T>, T> accumulator()
    {
        return (list, object) -> list.add(object);
    }

    @Override
    public BinaryOperator<CelestialList<T>> combiner()
    {
        return (l1, l2) ->
        {
            l1.addAll(l2);
            return l1;
        };
    }

    @Override
    public Function<CelestialList<T>, ImmutableCelestialList<T>> finisher()
    {
        return ImmutableCelestialList::unmodifiable;
    }

    @Override
    public Set<Characteristics> characteristics()
    {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
