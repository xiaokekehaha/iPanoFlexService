package dao;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import model.ViewConf;

public class ViewConfDao {
	static final String VIEWFAMILY = "view";

	static Logger logger = Logger.getLogger(ViewConfDao.class.getName());
	
	/**
	 * 从HBase返回的结果中解析出ViewConf实例
	 * @param result
	 * @return
	 */
	public ViewConf getViewConf(Result result){
		ViewConf viewConf = new ViewConf();
		Class<ViewConf> viewConfClass = ViewConf.class;
		Map<byte[], byte[]> map = result.getFamilyMap(Bytes.toBytes(VIEWFAMILY));
		if (map.isEmpty()){
			return null;
		}
		for (Map.Entry<byte[], byte[]> entry : map.entrySet()){
			String key = Bytes.toString(entry.getKey());
			int value = new Integer(Bytes.toString(entry.getValue()));
			try {
				Field field = viewConfClass.getDeclaredField(key);
				field.setAccessible(true);
				field.set(viewConf, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("[ERROR]: Class: ViewConfDao method: getViewConf set " 
						+ key + " error!");
				e.printStackTrace();
			}
		}
		return viewConf;
	}
	
	/**
	 * 
	 * @param viewConf
	 * @param put
	 * @return
	 */
	public boolean setHBasePut(ViewConf viewConf, Put put){
		Field[] fields = ViewConf.class.getDeclaredFields();
		for (Field field : fields){
			field.setAccessible(true);
			String value;
			try {
				value = ((Integer)field.get(viewConf)).toString();
				put.add(Bytes.toBytes(VIEWFAMILY),Bytes.toBytes(field.getName()), 
						Bytes.toBytes(value));
			} catch (Exception e) {
				logger.info("[ERROR]: Class: ViewConfDao Method: setHBasePut set HBasePut error");
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @param delete
	 * @return
	 */
	
	public boolean setHBaseDelete(Delete delete){
		delete.addFamily(Bytes.toBytes(VIEWFAMILY));
		
		return true;
	}
	
}