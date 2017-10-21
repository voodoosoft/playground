package de.voodoosoft.letrun.shared.scene.sector.impl;

import de.voodoosoft.gameroots.shared.geom.Vector;
import de.voodoosoft.letrun.shared.scene.sector.SectorEdge;
import de.voodoosoft.letrun.shared.scene.sector.SectorGraph;
import de.voodoosoft.letrun.shared.scene.sector.SectorGraphLayouter;
import de.voodoosoft.letrun.shared.scene.sector.SectorNode;

import java.util.HashSet;
import java.util.Set;



public class SimpleGraphLayouter implements SectorGraphLayouter {
	private Set<SectorEdge> walkedEdges;
	private int nodeGap;
	private int width;
	private int height;
	private int maxWidth;
	private int maxHeight;
	private Vector offset;

	public SimpleGraphLayouter() {
		nodeGap = 100;
		maxWidth = 500;
		maxHeight = 500;
	}

	public void setNodeGap(int nodeGap) {
		this.nodeGap = nodeGap;
	}

	@Override
	public Vector layout(SectorGraph graph, Vector rootLocation) {
		walkedEdges = new HashSet<>();
		width = 0;
		height = 0;
		offset = rootLocation;
		doLayout(graph.getRootNode(), rootLocation);

		return new Vector(width, height);
	}

	private void doLayout(SectorNode node, Vector location) {
		node.setLocation(location);

		for (int i = 0; i < node.getEdges().size(); i++) {
			SectorEdge edge = node.getEdges().get(i);
			if (!walkedEdges.contains(edge)) {
				walkedEdges.add(edge);
				SectorNode neighbor1 = edge.getNode1();
				SectorNode neighbor2 = edge.getNode2();
				SectorNode neighbor = neighbor1 != node ? neighbor1 : neighbor2;
				if (neighbor.getLocation() == null) {
					int x = (int) (offset.getX() + (i * nodeGap));
					int y = (int) (offset.getY() + (neighbor.getLevel() * nodeGap));
					Vector neighborLoc = new Vector(x, y);

					if (x > width) {
						width = x;
					}
					if (y > height) {
						height = y;
					}

					doLayout(neighbor, neighborLoc);
				}
			}
		}
	}
}
