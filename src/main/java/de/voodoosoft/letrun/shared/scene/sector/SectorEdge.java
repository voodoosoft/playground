package de.voodoosoft.letrun.shared.scene.sector;

public class SectorEdge {
	public SectorEdge(SectorNode node1, SectorNode node2) {
		this.node1 = node1;
		this.node2 = node2;
	}

	public SectorNode getNode1() {
		return node1;
	}

	public SectorNode getNode2() {
		return node2;
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
		sb.append(node1.getId());
		sb.append(" <-> ");
		sb.append(node2.getId());

		return sb.toString();
	}

	private SectorNode node1;
	private SectorNode node2;
	private boolean enabled;
	private boolean unidirectional;
}
