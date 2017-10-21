package de.voodoosoft.letrun.playground;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.voodoosoft.gameroots.shared.geom.Vector;
import de.voodoosoft.letrun.frontend.view.widget.SectorGraphWidget;
import de.voodoosoft.letrun.shared.scene.sector.SectorEdge;
import de.voodoosoft.letrun.shared.scene.sector.SectorGraph;
import de.voodoosoft.letrun.shared.scene.sector.SectorNode;
import de.voodoosoft.letrun.shared.scene.sector.impl.RandomGraphLayouter;
import de.voodoosoft.letrun.shared.scene.sector.impl.SimpleGraphLayouter;



public class LayoutSample extends ApplicationAdapter {
	private final Vector2 WINDOW_SIZE = new Vector2(1024, 768);
	private Skin skin;

	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Viewport viewport;
	private SectorGraph sectorGraph;
	private SectorGraphWidget graphWidget;
	private Table formTable;
	private Stage stage;

	@Override
	public void create() {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		viewport = new ScalingViewport(Scaling.fill, WINDOW_SIZE.x, WINDOW_SIZE.y, cam);

//		sectorGraph = createSampleGraph1();
		sectorGraph = createSampleGraph2();

		skin = new Skin(Gdx.files.internal("uiskin.json"));
		graphWidget = new SectorGraphWidget();
		graphWidget.setEnabledColor(Color.GREEN);
		graphWidget.setDisabledColor(Color.RED);
		graphWidget.setSectorGraph(sectorGraph, (node) -> createButton(node));

		formTable = new Table();
		formTable.add(graphWidget);

		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		stage.addActor(formTable);
	}

	private SectorGraph createSampleGraph1() {
		SectorNode rootNode = new SectorNode(0, 0);
		SectorGraph sectorGraph = new SectorGraph(rootNode);

		SectorNode node100 = new SectorNode(100, 1);
		addEdge(rootNode, node100);
		sectorGraph.addNode(node100);

		SectorNode node101 = new SectorNode(101, 2);
		addEdge(node100, node101);
		sectorGraph.addNode(node101);

		SectorNode node102 = new SectorNode(102, 2);
		addEdge(node100, node102);
		sectorGraph.addNode(node102);

		SectorNode node103 = new SectorNode(103, 3);
		addEdge(node101, node103);
		addEdge(node100, node103);
		addEdge(node102, node103);
		sectorGraph.addNode(node103);

		SectorNode node104 = new SectorNode(104, 3);
		addEdge(node101, node104);
		sectorGraph.addNode(node104);

		SectorNode node105 = new SectorNode(105, 1);
		addEdge(node105, node104);
		addEdge(node100, node105);
		sectorGraph.addNode(node105);

		SimpleGraphLayouter layouter = new SimpleGraphLayouter();
		layouter.setNodeGap(100);
		sectorGraph.layout(layouter, new Vector(500, 500));

		return sectorGraph;
	}

	private SectorGraph createSampleGraph2() {
		SectorNode rootNode = new SectorNode(0, 0);
		SectorGraph sectorGraph = new SectorGraph(rootNode);

		SectorNode node100 = new SectorNode(100, 1);
		addEdge(rootNode, node100);
		sectorGraph.addNode(node100);

		SectorNode node101 = new SectorNode(101, 2);
		addEdge(node100, node101);
		sectorGraph.addNode(node101);

		SectorNode node102 = new SectorNode(102, 2);
		addEdge(node100, node102);
		sectorGraph.addNode(node102);

		SectorNode node103 = new SectorNode(103, 3);
		addEdge(node101, node103);
		addEdge(node100, node103);
		addEdge(node102, node103);
		sectorGraph.addNode(node103);

		SectorNode node104 = new SectorNode(104, 3);
		addEdge(node101, node104);
		sectorGraph.addNode(node104);

		SectorNode node105 = new SectorNode(105, 1);
		addEdge(node105, node104);
		addEdge(node100, node105);
		sectorGraph.addNode(node105);

		SectorNode node106 = new SectorNode(106, 5);
		addEdge(node105, node106);
		sectorGraph.addNode(node106);

		SectorNode node107 = new SectorNode(107, 5);
		addEdge(node105, node107);
		sectorGraph.addNode(node107);

		SectorNode node108 = new SectorNode(108, 5);
		addEdge(node105, node108);
		sectorGraph.addNode(node108);

		SectorNode node109 = new SectorNode(109, 5);
		addEdge(node108, node109);
		sectorGraph.addNode(node109);

		RandomGraphLayouter layouter = new RandomGraphLayouter();
//		SimpleGraphLayouter layouter = new SimpleGraphLayouter();
//		layouter.setNodeGap(100);
		sectorGraph.layout(layouter, new Vector(500, 500));

		return sectorGraph;
	}

	private TextButton createButton(SectorNode node) {
		TextButton textButton = new TextButton("" + node.getId(), skin);
		textButton.setPosition(node.getLocation().getX(), node.getLocation().getY());
		return textButton;
	}

	private void addEdge(SectorNode node1, SectorNode node2) {
		SectorEdge sectorEdge = new SectorEdge(node1, node2);
		node1.addEdge(sectorEdge);
		node2.addEdge(sectorEdge);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}
}
