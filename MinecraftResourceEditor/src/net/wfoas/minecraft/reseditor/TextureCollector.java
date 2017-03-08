package net.wfoas.minecraft.reseditor;

public class TextureCollector {
	public static enum TexMode {
		ALL, SIDES, SINGLE;
	}

	String up_normal_all, west_side, east, north_front, south, down_bottom;
	TexMode tm;

	public TextureCollector(String up_normal_all, String west_side, String east, String north_front, String south,
			String down_bottom, TexMode tm) {
		this.up_normal_all = up_normal_all;
		this.west_side = west_side;
		this.east = east;
		this.north_front = north_front;
		this.south = south;
		this.down_bottom = down_bottom;
		this.tm = tm;
	}

	public String getUp_normal_all() {
		return up_normal_all;
	}

	public String getWest_side() {
		return west_side;
	}

	public String getEast() {
		return east;
	}

	public String getNorth_front() {
		return north_front;
	}

	public String getSouth() {
		return south;
	}

	public String getDown_bottom() {
		return down_bottom;
	}

	public TexMode getTm() {
		return tm;
	}

}
