package dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Point;
import util.HBaseUtil;

public class PointDao {

	static final String TABLENAME = "userinterest";
	static final String COUNTFAMLY = "count";
	private HBaseUtil hBaseUtil;
//	static ApplicationContext context = null;
	
	public PointDao(){
		
	}
	
	public HBaseUtil gethBaseUtil() {
		return hBaseUtil;
	}

	public void sethBaseUtil(HBaseUtil hBaseUtil) {
		this.hBaseUtil = hBaseUtil;
	}


	
	public boolean insertPoint(String id, Point point){
		Put put = new Put(Bytes.toBytes(id));
		int value = 1;
		Result result = getPointValue(id, point);
		if (!result.isEmpty()){
			String orgValue = Bytes.toString(result.getValue(Bytes.toBytes(COUNTFAMLY), Bytes.toBytes(point.toString())));
			value += new Integer(orgValue);
		}
		put.add(Bytes.toBytes(COUNTFAMLY), Bytes.toBytes(point.toString()), Bytes.toBytes(new Integer(value).toString()));
//		HBaseUtil hBaseUtil = (HBaseUtil)context.getBean("hbaseUtil");
		return hBaseUtil.addRecord(TABLENAME, put);
	}
	
	private boolean isexesit(String id, Point point){
//		HBaseUtil hBaseUtil = (HBaseUtil)context.getBean("hbaseUtil");
		
		Result result = hBaseUtil.getResult(TABLENAME, id, COUNTFAMLY, point.toString());
		return !result.isEmpty();
	}
	
	/**
	 * 
	 * @param id
	 * @param point
	 * @return
	 */
	public Result getPointValue(String id, Point point){
//		HBaseUtil hBaseUtil = (HBaseUtil)context.getBean("hbaseUtil");
		Result result = hBaseUtil.getResult(TABLENAME, id, COUNTFAMLY, point.toString());
		return result;
	}
	
	private Result getPoints(String id){
//		HBaseUtil hBaseUtil = (HBaseUtil)context.getBean("hbaseUtil");
		Result result = hBaseUtil.getResult(TABLENAME, id);
		return result;
	}
	/**
	 * 得到一个id下的所有点
	 * @param id
	 * @return
	 */
	public Map<Point, Integer> getPointsMap(String id){
		Map<Point, Integer> map = new HashMap<Point, Integer>();
		Result result = getPoints(id);
		Map<byte[], byte[]> resultMap = result.getFamilyMap(Bytes.toBytes(COUNTFAMLY));
		if (resultMap.isEmpty()){
			return null;
		}
		for (Map.Entry<byte[], byte[]> entry : resultMap.entrySet()){
			String key = Bytes.toString(entry.getKey());
			Integer value = new Integer(Bytes.toString(entry.getValue()));
			Point point = new Point(key);
			map.put(point, value);
		}
		return map;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PointDao pointDao = (PointDao)context.getBean("pointDao");
		pointDao.insertPoint("test1111", new Point(10, 10));
	}
}
