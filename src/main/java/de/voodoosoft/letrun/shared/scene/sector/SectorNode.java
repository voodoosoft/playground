package de.voodoosoft.letrun.shared.scene.sector;

import de.voodoosoft.gameroots.shared.geom.Vector;
import de.voodoosoft.letrun.shared.scene.enums.SectorState;

import java.util.ArrayList;
import java.util.List;



public class SectorNode {
	private Integer templateId;
	private Integer id;
	private Vector location;
	private int level;
	private boolean enabled;
	private SectorState state;
	private boolean highlight;
	private final List<SectorEdge> edges;
	private String description;

	public SectorNode(Integer id, Integer level) {
		this.id = id;
		this.templateId = id;
		this.level = level;

		edges = new ArrayList<>();
	}

	public void setState(SectorState state) {
		this.state = state;
	}

	public SectorState getState() {
		return state;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setLocation(Vector location) {
		this.location = location;
	}

	public Vector getLocation() {
		return location;
	}

	public int getLevel() {
		return level;
	}

	public List<SectorEdge> getEdges() {
		return edges;
	}

	public void addEdge(SectorEdge edge) {
		edges.add(edge);
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(" -> ");
		for (SectorEdge edge : edges) {
			if (edge.getNode1() != this) {
				sb.append(edge.getNode1().getId());
				sb.append(" ");
			}
			if (edge.getNode2() != this) {
				sb.append(edge.getNode2().getId());
				sb.append(" ");
			}
		}

		return sb.toString();
	}
}
