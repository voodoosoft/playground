package de.voodoosoft.letrun.shared.scene.sector;

import de.voodoosoft.gameroots.shared.geom.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;



public class SectorGraph {
	private final SectorNode rootNode;
	private int width, height;
	private final Map<Integer, SectorNode> nodeMap;
	private final List<SectorEdge> edges;

	public SectorGraph(SectorNode rootNode) {
		nodeMap = new HashMap<>();
		edges = new ArrayList<>();

		this.rootNode = rootNode;
		addNode(rootNode);
	}

	public void addNode(SectorNode node) {
		if (!nodeMap.containsKey(node.getId())) {
			nodeMap.put(node.getId(), node);
			for (SectorEdge sectorEdge : node.getEdges()) {
				if (!edges.contains(sectorEdge)) {
					edges.add(sectorEdge);
				}
			}
		}
	}

	public void forEachNode(Consumer<SectorNode> consumer) {
		for (SectorNode sectorNode : nodeMap.values()) {
			consumer.accept(sectorNode);
		}
	}

	public void forEachEdge(Consumer<SectorEdge> consumer) {
		for (SectorEdge edge : edges) {
			consumer.accept(edge);
		}
	}

	public SectorNode getNode(Integer nodeId) {
		return nodeMap.get(nodeId);
	}

	public SectorNode getRootNode() {
		return rootNode;
	}

	public void layout(SectorGraphLayouter layouter, Vector rootLocation) {
		Vector dimension = layouter.layout(this, rootLocation);
		width = (int)dimension.getX();
		height = (int)dimension.getY();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
