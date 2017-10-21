package de.voodoosoft.letrun.shared.scene.sector;

import de.voodoosoft.gameroots.shared.geom.Vector;



public interface SectorGraphLayouter {
	Vector layout(SectorGraph graph, Vector rootLocation);
}
