package model;

public class ProductInfo {

	private Point point1;
	private Point point2;
	private String content;
	private int count;
	
	public ProductInfo(Point point1, Point point2, String content){
		this.content = content;
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public ProductInfo(Point point1, Point point2){
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public ProductInfo(){
		
	}
	
	public void setPoint(Point point1, Point point2){
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public int getCount(){
		return count;
	}
	public String getMBR(){
		String mbr = new String();
		float lat1 = point1.getLat();
		float lat2 = point2.getLat();
		float lon1 = point1.getLon();
		float lon2 = point2.getLon();
		
		if (lon1 > lon2){
			mbr = lon2 + "_" + lon1;
		}
		else{
			mbr = lon1 + "_" + lon2;
		}
		mbr += "_";
		if (lat1 > lat2){
			mbr = mbr + lat2 + "_" + lat1;
		}else {
			mbr = mbr + lat1 + "_" + lat2;
		}
		return mbr;
	}
	

}
