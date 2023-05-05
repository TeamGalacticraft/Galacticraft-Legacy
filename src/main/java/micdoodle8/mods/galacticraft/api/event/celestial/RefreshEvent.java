package micdoodle8.mods.galacticraft.api.event.celestial;

import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.core.util.list.CelestialList;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Map;

public class RefreshEvent extends Event {

	public Map<Planet, CelestialList<Moon>> moonList;
	public Map<CelestialBody, CelestialList<Satellite>> satelliteList;
	public Map<SolarSystem, CelestialList<Planet>> solarSystemList;

	public RefreshEvent(Map<Planet, CelestialList<Moon>> moonList,
						Map<CelestialBody, CelestialList<Satellite>> satelliteList,
						Map<SolarSystem, CelestialList<Planet>> solarSystemList) {
		this.moonList = moonList;
		this.satelliteList = satelliteList;
		this.solarSystemList = solarSystemList;
	}
}
