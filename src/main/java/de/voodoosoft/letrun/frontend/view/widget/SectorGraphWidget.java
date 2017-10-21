package de.voodoosoft.letrun.frontend.view.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import de.voodoosoft.gameroots.shared.geom.Vector;
import de.voodoosoft.letrun.shared.scene.sector.SectorEdge;
import de.voodoosoft.letrun.shared.scene.sector.SectorGraph;
import de.voodoosoft.letrun.shared.scene.sector.SectorNode;

import java.util.*;
import java.util.function.Function;



public class SectorGraphWidget extends WidgetGroup {
	private SectorGraph sectorGraph;
	private ShapeRenderer edgeRenderer;
	private boolean init;
	private final Map<Integer, TextButton> nodeButtons;
	private Color enabledColor;
	private Color disabledColor;

	private Set<SectorEdge> walkedEdges;

	public SectorGraphWidget() {
		nodeButtons = new HashMap<>();
	}

	public void setEnabledColor(Color enabledColor) {
		this.enabledColor = enabledColor;
	}

	public void setDisabledColor(Color disabledColor) {
		this.disabledColor = disabledColor;
	}

	public void setSectorGraph(SectorGraph sectorGraph, Function<SectorNode, TextButton> buttonCreator) {
		this.sectorGraph = sectorGraph;
		walkedEdges = new HashSet<>();
		createNodeButtons(sectorGraph.getRootNode(), buttonCreator);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (sectorGraph == null) {
			return;
		}

		if (!init) {
			edgeRenderer = new ShapeRenderer();
			edgeRenderer.setAutoShapeType(true);
			init = true;
		}

		batch.end();

		edgeRenderer.begin();
		edgeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		edgeRenderer.setTransformMatrix(batch.getTransformMatrix());
		edgeRenderer.translate(getX(), getY(), 0);
		sectorGraph.forEachEdge(edge -> drawEdge(edge));
		edgeRenderer.end();

		batch.begin();

		super.draw(batch, 1);
	}

	private void createNodeButtons(SectorNode node, Function<SectorNode, TextButton> buttonCreator) {
		TextButton nodeButton = nodeButtons.get(node.getId());
		if (nodeButton == null) {
			nodeButton = buttonCreator.apply(node);
			Vector nodeLoc = node.getLocation();
			nodeButton.setX(nodeLoc.getX() - (nodeButton.getWidth() / 2));
			nodeButton.setY(nodeLoc.getY() - (nodeButton.getHeight() / 2));
			addActor(nodeButton);
			nodeButtons.put(node.getId(), nodeButton);
		}

		for (int i = 0; i < node.getEdges().size(); i++) {
			SectorEdge edge = node.getEdges().get(i);
			if (!walkedEdges.contains(edge)) {
				walkedEdges.add(edge);
				SectorNode neighbor = edge.getNode1() != node ? edge.getNode1() : edge.getNode2();
				createNodeButtons(neighbor, buttonCreator);
			}
		}
	}

	private void drawEdge(SectorEdge edge) {
		Vector node1Loc = edge.getNode1().getLocation();
		Vector node2Loc = edge.getNode2().getLocation();

		edgeRenderer.setColor(edge.isEnabled() ? enabledColor : disabledColor);
		edgeRenderer.line(node1Loc.getX(), node1Loc.getY(), node2Loc.getX(), node2Loc.getY());
	}

	@Override
	public float getPrefHeight() {
		return sectorGraph != null ? sectorGraph.getWidth() + 50 : 0;
	}

	@Override
	public float getPrefWidth() {
		return sectorGraph != null ? sectorGraph.getHeight() + 50 : 0;
	}
}
