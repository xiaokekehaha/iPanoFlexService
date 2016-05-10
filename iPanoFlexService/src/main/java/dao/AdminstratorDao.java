package dao;

import java.lang.reflect.Field;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Adminstrator;
import util.HBaseUtil;

public class AdminstratorDao {
	static final String TABLENAME = "admintable";
	static final String INFOFAMILY = "info";
	public static AdminstratorDao adminstratorDao = new AdminstratorDao();
	private HBaseUtil hBaseUtil;
	static Logger logger = null;
	static{
		logger = Logger.getLogger(Adminstrator.class);
		
	}
	
	public AdminstratorDao(){
		
	}
	
public HBaseUtil gethBaseUtil() {
		return hBaseUtil;
	}

	public void sethBaseUtil(HBaseUtil hBaseUtil) {
		this.hBaseUtil = hBaseUtil;
	}

	//	public static AdminstratorDao getAdminstratorDao(){
//		return adminstratorDao;
//	}
	/**
	 * 插入用户
	 * @param adminstrator
	 * @return
	 */
	public boolean insertAdminstratorDao(Adminstrator adminstrator){
		// 插入前首先检查是否存在用户
		if (isexesit(adminstrator.getName())){
			logger.error("用户名: " + adminstrator.getName() + " 存在");
			return false;
		}
		Put put = new Put(Bytes.toBytes(adminstrator.getName()));
		Field[] fields = Adminstrator.class.getDeclaredFields();
		
		for (Field field : fields){
			field.setAccessible(true);
			try {
				String value = (String)field.get(adminstrator);
				put.add(Bytes.toBytes(INFOFAMILY),Bytes.toBytes(field.getName()), Bytes.toBytes(value));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			} 
		}
//		HBaseUtil hBaseUtil = HBaseUtil.getHBaseUtil();
		hBaseUtil.addRecord(TABLENAME, put);
		return true;
	}
	/**
	 * 得到某一成员变量的值
	 * @param name
	 * @param fieldName
	 * @return
	 */
	public String getFieldByName(String name, String fieldName){
//		HBaseUtil hBaseUtil = HBaseUtil.getHBaseUtil();
		Result result = hBaseUtil.getResult(TABLENAME, name);
		String value = Bytes.toString(result.getValue(Bytes.toBytes(INFOFAMILY), Bytes.toBytes(fieldName)));
		return value;
	}
	
	/**
	 * 修改密码
	 * @param name
	 * @param editorName
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassword(String name, String editorName, String newPassword){
		if (!name.equals(editorName)){
			// 查看是否有权限操作
			if (!getFieldByName(editorName, "priority").equals("0")){
				logger.error("用户: " + editorName + "没有权限修改用户: " + name + " 的密码");
				return false;
			}
		}
		Put put = new Put(Bytes.toBytes(name));
		put.add(Bytes.toBytes(INFOFAMILY), Bytes.toBytes("password"), Bytes.toBytes(newPassword));
//		HBaseUtil hBaseUtil = HBaseUtil.getHBaseUtil();
		return hBaseUtil.addRecord(TABLENAME, put);
		
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	private boolean isexesit(String name){
//		HBaseUtil hBaseUtil = HBaseUtil.getHBaseUtil();
		Result result = hBaseUtil.getResult(TABLENAME, name);
		return !result.isEmpty();
	}
	
	public static void main(String[] args) {
//		Adminstrator adminstrator = new Adminstrator("hh", "11", "2");
//		AdminstratorDao adminstratorDao = AdminstratorDao.getAdminstratorDao();
//		adminstratorDao.insertAdminstratorDao(adminstrator);
//		String password = adminstratorDao.getFieldByName("xzj", "password");
//		System.out.println(password);
		
//		adminstratorDao.updatePassword("xx", "xzj", "lll");
	}

}
