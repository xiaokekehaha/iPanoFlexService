package model;

public class SceneConf {
	private float lat;
	private float lon;
	private float maxLat;
	private float minLat;
	private float fov;
	private float near;
	private float far;
	private float maxFov;
	private float minFov;
	private float viewPortH;
	private float viewPortW;
	private String url;
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public float getMaxLat() {
		return maxLat;
	}
	public void setMaxLat(float maxLat) {
		this.maxLat = maxLat;
	}
	public float getMinLat() {
		return minLat;
	}
	public void setMinLat(float minLat) {
		this.minLat = minLat;
	}
	public float getFov() {
		return fov;
	}
	public void setFov(float fov) {
		this.fov = fov;
	}
	public float getNear() {
		return near;
	}
	public void setNear(float near) {
		this.near = near;
	}
	public float getFar() {
		return far;
	}
	public void setFar(float far) {
		this.far = far;
	}
	public float getMaxFov() {
		return maxFov;
	}
	public void setMaxFov(float maxFov) {
		this.maxFov = maxFov;
	}
	public float getMinFov() {
		return minFov;
	}
	public void setMinFov(float minFov) {
		this.minFov = minFov;
	}
	public float getViewPortH() {
		return viewPortH;
	}
	public void setViewPortH(float viewPortH) {
		this.viewPortH = viewPortH;
	}
	public float getViewPortW() {
		return viewPortW;
	}
	public void setViewPortW(float viewPortW) {
		this.viewPortW = viewPortW;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "SceneConf [lat=" + lat + ", lon=" + lon + ", maxLat=" + maxLat + ", minLat=" + minLat + ", fov=" + fov
				+ ", near=" + near + ", far=" + far + ", maxFov=" + maxFov + ", minFov=" + minFov + ", viewPortH="
				+ viewPortH + ", viewPortW=" + viewPortW + ", url=" + url + "]";
	}

	
}
