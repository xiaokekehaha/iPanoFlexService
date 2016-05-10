package model;

public class ConfInfo {
	
	private String author;
	private String isSysn; // 是否同步
	private String isaccess; // 是否被放入回收站
	public String getIsaccess() {
		return isaccess;
	}
	public void setIsaccess(String isaccess) {
		this.isaccess = isaccess;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsSysn() {
		return isSysn;
	}
	public void setIsSysn(String isSysn) {
		this.isSysn = isSysn;
	}

}
