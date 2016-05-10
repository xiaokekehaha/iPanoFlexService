package model;

public class ViewConf {
	private int scenesNum;
	private int sphereRadius;
	private int segmentsWidth;
	private int segmentsHeight;
	public int getScenesNum() {
		return scenesNum;
	}
	public void setScenesNum(int scenesNum) {
		this.scenesNum = scenesNum;
	}
	public int getSphereRadius() {
		return sphereRadius;
	}
	public void setSphereRadius(int sphereRadius) {
		this.sphereRadius = sphereRadius;
	}
	public int getSegmentsWidth() {
		return segmentsWidth;
	}
	public void setSegmentsWidth(int segmentsWidth) {
		this.segmentsWidth = segmentsWidth;
	}
	public int getSegmentsHeight() {
		return segmentsHeight;
	}
	public void setSegmentsHeight(int segmentsHeight) {
		this.segmentsHeight = segmentsHeight;
	}
	@Override
	public String toString() {
		return "ViewConf [scenesNum=" + scenesNum + ", sphereRadius=" + sphereRadius + ", segmentsWidth="
				+ segmentsWidth + ", segmentsHeight=" + segmentsHeight + "]";
	}

}
