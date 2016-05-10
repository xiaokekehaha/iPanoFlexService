package dao;

import java.lang.reflect.Field;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.ConfInfo;
import util.HBaseUtil;

public class ConfInfoDao {
	private HBaseUtil hBaseUtil;
	static final String INFOFAMILY = "info";
	private String TABLENAME;
	private AdminstratorDao adminstratorDao;
	static Logger logger = null;
	
	public AdminstratorDao getAdminstratorDao() {
		return adminstratorDao;
	}

	public void setAdminstratorDao(AdminstratorDao adminstratorDao) {
		this.adminstratorDao = adminstratorDao;
	}

	public HBaseUtil gethBaseUtil() {
		return hBaseUtil;
	}

	public void sethBaseUtil(HBaseUtil hBaseUtil) {
		this.hBaseUtil = hBaseUtil;
	}

	public String getTABLENAME() {
		return TABLENAME;
	}

	public void setTABLENAME(String tABLENAME) {
		TABLENAME = tABLENAME;
	}

//	public ConfInfoDao(String tableName){
//		TABLENAME = tableName;
//	}
	
	public ConfInfoDao(){
		
	}
	
	static{
		logger = Logger.getLogger(ConfInfo.class);
		
	}
	/**
	 * 
	 * @param confInfo
	 * @param put
	 * @return
	 */
	public boolean setHBasePut(ConfInfo confInfo, Put put){
		Field[] fields = ConfInfo.class.getDeclaredFields();
		for (Field field : fields){
			field.setAccessible(true);
			String value;
			try {
				value = (String)field.get(confInfo);
				put.add(Bytes.toBytes(INFOFAMILY),Bytes.toBytes(field.getName()), 
						Bytes.toBytes(value));
			} catch (Exception e) {
				logger.info("[ERROR]: Class: ViewConfDao Method: setHBasePut set HBasePut error");
				return false;
			}
		}
		return true;
	}
	
	public String getFieldById(String id, String fieldName){
//		HBaseUtil hBaseUtil = HBaseUtil.getHBaseUtil();
		Result result = hBaseUtil.getResult(TABLENAME, id);
		String value = Bytes.toString(result.getValue(Bytes.toBytes(INFOFAMILY), Bytes.toBytes(fieldName)));
		return value;
	}
	
	public boolean setFieldById(String id, String fieldName, String value){
		Put put = new Put(Bytes.toBytes(id));
		put.add(Bytes.toBytes(INFOFAMILY), Bytes.toBytes(fieldName), Bytes.toBytes(value));
//		HBaseUtil hBaseUtil = HBaseUtil.getHBaseUtil();
		return hBaseUtil.addRecord(TABLENAME, put);
	}
	
	public boolean setIsAccess(String id, String value, String editor){
		String author = getFieldById(id, "author");
		if (!editor.equals(author)){
//			AdminstratorDao adminstratorDao = AdminstratorDao.getAdminstratorDao();
			String poritory = adminstratorDao.getFieldByName(editor, "priority");
			if (poritory == null || !poritory.equals("0")){
				return false;
			}
		}
		return setFieldById(id, "isaccess", value);
	}

}
