/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.entities;

import micdoodle8.mods.galacticraft.api.vector.Vector3;

/**
 * To be combined with IBubbleProvider in the next major update
 */
public interface IBubbleProviderColored extends IBubbleProvider
{

    Vector3 getColor();
}
