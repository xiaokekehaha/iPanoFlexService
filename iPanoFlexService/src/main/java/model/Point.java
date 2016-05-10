package model;

public class Point {
	private float lon;
	private float lat;
	public Point(){
		
	}
	public Point(float lon, float lat){
		this.lon = lon;
		this.lat = lat;
	}
	/**
	 * 
	 * @param locationStr: format : lon_lat
	 */
	public Point(String locationStr){
		String[] strings = locationStr.split("_");
		this.lon = new Float(strings[0]);
		this.lat = new Float(strings[1]);
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return lon + "_" + lat;
	}
	

}
