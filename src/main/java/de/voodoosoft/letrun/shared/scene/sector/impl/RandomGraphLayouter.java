package de.voodoosoft.letrun.shared.scene.sector.impl;

import de.voodoosoft.gameroots.shared.geom.Vector;
import de.voodoosoft.letrun.shared.scene.sector.SectorGraph;
import de.voodoosoft.letrun.shared.scene.sector.SectorGraphLayouter;
import de.voodoosoft.letrun.shared.scene.sector.SectorNode;

import java.util.Random;



public class RandomGraphLayouter implements SectorGraphLayouter {
	private Random rnd;
	private int width;
	private int height;
	private int maxWidth;
	private int maxHeight;
	private Vector rootLocation;

	public RandomGraphLayouter() {
		rnd = new Random();
		maxWidth = 500;
		maxHeight = 500;
	}

	@Override
	public Vector layout(SectorGraph graph, Vector rootLocation) {
		this.rootLocation = rootLocation;
		width = 0;
		height = 0;
		graph.forEachNode(node -> placeNode(node));

		return new Vector(width, height);
	}

	private void placeNode(SectorNode node) {
		int x = (int)(rootLocation.getX() + rnd.nextInt(maxWidth));
		int y = (int)(rootLocation.getY() +  rnd.nextInt(maxHeight));

		if (x > width) {
			width = x;
		}
		if (y > height) {
			height = y;
		}

		node.setLocation(new Vector(x, y));
	}
}
