package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import model.Conf;
import model.ConfInfo;
import model.SceneConf;
import model.ViewConf;
import util.HBaseUtil;

public class ConfDao {
	static Logger logger = Logger.getLogger(ConfDao.class.getName());
	
//	private ConfInfoDao confInfoDao;
//	private AdminstratorDao adminstratorDao;
//	public AdminstratorDao getAdminstratorDao() {
//		return adminstratorDao;
//	}
//
//	public void setAdminstratorDao(AdminstratorDao adminstratorDao) {
//		this.adminstratorDao = adminstratorDao;
//	}

	public String getTABLENAME() {
		return TABLENAME;
	}

	public void setTABLENAME(String tABLENAME) {
		TABLENAME = tABLENAME;
	}

	public HBaseUtil gethBaseUtil() {
		return hBaseUtil;
	}

	public void sethBaseUtil(HBaseUtil hBaseUtil) {
		this.hBaseUtil = hBaseUtil;
	}
	public static String INFOFAMILY = "info"; 
	private String TABLENAME;
	
	private HBaseUtil hBaseUtil;
	
	public ConfDao(){
		
	}

//	public ConfDao(String tableName){
//		TABLENAME = tableName;
//	}
	
	/**
	 * 从HBase 中获取完整的Conf
	 * @param id : HBase 中的rawkey
	 * @return
	 */
	public Conf getConfById(String id){
		Conf conf = new Conf();
//		HBaseUtil hbaseUtil = HBaseUtil.getHBaseUtil();
		Result result = hBaseUtil.getResult(TABLENAME, id);
		if (result.isEmpty()){
			logger.info(id + " 没有配置信息");
			return null;
		}
		ViewConfDao viewConfDao = new ViewConfDao();
		SceneConfDao sceneConfDao = new SceneConfDao();
		
		ViewConf viewConf = viewConfDao.getViewConf(result);
		List<SceneConf> sceneConfs = sceneConfDao.getSceneConf(result, viewConf.getScenesNum());
		
//		String author = Bytes.toString(result.getValue(Bytes.toBytes("author"), Bytes.toBytes("author")));
		conf.setScenesConf(sceneConfs);
		conf.setViewConf(viewConf);
//		conf.setAuthor(author);
		logger.info(id + " 获取配置信息成功");
		
		return conf;
	}
	
//	public ConfInfoDao getConfInfoDao() {
//		return confInfoDao;
//	}
//
//	public void setConfInfoDao(ConfInfoDao confInfoDao) {
//		this.confInfoDao = confInfoDao;
//	}

	/**
	 * 插入conf对象
	 * @param id rowkey
	 * @param conf
	 * @return
	 */
//	public boolean insertConf(String id, Conf conf){
//		ViewConf viewConf = conf.getViewConf();
//		List<SceneConf> sceneConfs = conf.getScenesConf();
//		ConfInfo confInfo = conf.getConfInfo();
//		Put put = new Put(Bytes.toBytes(id));
//		ViewConfDao viewConfDao = new ViewConfDao();
//		SceneConfDao sceneConfDao = new SceneConfDao();
////		ConfInfoDao confInfoDao = new ConfInfoDao(TABLENAME);
//		if (!viewConfDao.setHBasePut(viewConf, put)){
//			logger.info("设置viewConf put 出错");
//			return false;
//		}
//		if (!sceneConfDao.setHBasePut(sceneConfs, put)){
//			logger.info("设置sceneConf put 出错");
//			return false;
//		}
//		if (!confInfoDao.setHBasePut(confInfo, put)){
//			logger.info("设置confinfo put 出错");
//		}
////		HBaseUtil hbaseUtil = HBaseUtil.getHBaseUtil();
//		hBaseUtil.addRecord(TABLENAME, put);
//		logger.info(id + " 插入成功");
//		return true;
//	}
	/**
	 * 
	 * @param id
	 * @param sceneConf
	 * @return
	 */
//	public boolean insertConf(String id, SceneConf sceneConf){
//		Conf conf = getConfById(id);
//		int scenesNum = conf.getViewConf().getScenesNum() + 1;
//		conf.getViewConf().setScenesNum(scenesNum);
//		List<SceneConf> sceneConfs = conf.getScenesConf();
//		sceneConfs.add(sceneConf);
//		conf.setScenesConf(sceneConfs);
//		return insertConf(id, conf);
//	}
	
	/**
	 * 
	 * @param id
	 * @param sceneConfs
	 * @return
//	 */
//	public boolean insertConf(String id, List<SceneConf> sceneConfs){
//		Conf conf = getConfById(id);
//		int scenesNum = conf.getViewConf().getScenesNum() + sceneConfs.size();
//		conf.getViewConf().setScenesNum(scenesNum);
//		List<SceneConf> sceneConfs2 = conf.getScenesConf();
//		sceneConfs2.addAll(sceneConfs);
//		conf.setScenesConf(sceneConfs2);
//		
//		return insertConf(id, conf);
//	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
//	public boolean removeConf(String id, String editor){
//		if (isEdit(id, editor)){
////			HBaseUtil hbaseUtil = HBaseUtil.getHBaseUtil();
//			return hBaseUtil.removeRecord(TABLENAME, id);
//		}
//		return false;
//		
//	}
	

	/**
	 * 
	 * @param id
	 * @param sceneId
	 * @return
	 */
//	public String removeSceneConf(String id, int sceneId, String editor){
//
//		Conf conf = getConfById(id);
//		int num = conf.getViewConf().getScenesNum();
//		
//		if (num - 1 < sceneId){
//			logger.info(id + " 没有 " + sceneId + "个场景");
//			return null;
//		}
//		List<SceneConf> sceneConfs = conf.getScenesConf();
//		SceneConf sceneConf = sceneConfs.remove(sceneId);
//		String url = sceneConf.getUrl();
//		if (num == 1){
//			removeConf(id, editor);
//			
//			return url;
//		}
//		
//		conf.getViewConf().setScenesNum(num-1);
//		conf.setScenesConf(sceneConfs);
//		insertConf(id, conf);
//		return url;
//		
//	}
	

	/**
	 * 
	 * @param id
	 * @param conf
	 * @return
//	 */
//	public boolean updataConf(String id, Conf conf){
//		return insertConf(id, conf);
//	}
//	
//	/**
//	 * 
//	 * @param id
//	 * @param sceneId
//	 * @param sceneConf
//	 * @return
//	 */
//	public boolean updataConf(String id, int sceneId, SceneConf sceneConf){
//		Conf conf = getConfById(id);
//		if (sceneId > conf.getViewConf().getScenesNum() - 1){
//			logger.info("更新出错 " + sceneId);
//			return false;
//		}
//		
//		List<SceneConf> sceneConfs = conf.getScenesConf();
//		sceneConfs.set(sceneId, sceneConf);
//		conf.setScenesConf(sceneConfs);
//		return insertConf(id, conf);
//
//	}
//	
//	/**
//	 * 判断ｅｄｉｔｏｒ是否对ｃｏｎｆ为ｉｄ的记录有修改权限
//	 * @param id
//	 * @param editor
//	 * @return
//	 */
//	public boolean isEdit(String id, String editor){
//		String author = getAuthorById(id);
//		if (!editor.equals(author)){
////			AdminstratorDao adminstratorDao = AdminstratorDao.getAdminstratorDao();
//			String poritory = adminstratorDao.getFieldByName(editor, "priority");
//			if (poritory == null || !poritory.equals("0")){
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private String getAuthorById(String id){
////		HBaseUtil hbaseUtil = HBaseUtil.getHBaseUtil();
//		Result result = hBaseUtil.getResult(TABLENAME, id);
//		String value = Bytes.toString(result.getValue(Bytes.toBytes(INFOFAMILY), Bytes.toBytes("author")));
//		return value;
//		
//	}
	public static void main(String[] args){
//		Conf conf = new Conf();
//		ViewConf viewConf = new ViewConf();
//		viewConf.setScenesNum(2);
//		viewConf.setSphereRadius(1000);
//		viewConf.setSegmentsHeight(50);
//		viewConf.setSegmentsWidth(50);
//		
//		conf.setViewConf(viewConf);
//		
//		SceneConf sceneConf = new SceneConf();
//		sceneConf.setLat(0);
//		sceneConf.setLon(0);
//		sceneConf.setMaxLat(10);
//		sceneConf.setMinLat(-10);
//		sceneConf.setNear(1);
//		sceneConf.setFar(1000);
//		sceneConf.setFov(76);
//		sceneConf.setMaxFov(106);
//		sceneConf.setMinFov(46);
//		sceneConf.setViewPortH(400);
//		sceneConf.setViewPortW(700);
//		sceneConf.setUrl("/home/xzj/we2.jpg");
//		
//		
//		
//		SceneConf sceneConf2 = new SceneConf();
//		sceneConf2.setLat(0);
//		sceneConf2.setLon(0);
//		sceneConf2.setMaxLat(10);
//		sceneConf2.setMinLat(-10);
//		sceneConf2.setNear(1);
//		sceneConf2.setFar(1000);
//		sceneConf2.setFov(76);
//		sceneConf2.setMaxFov(106);
//		sceneConf2.setMinFov(46);
//		sceneConf2.setViewPortH(400);
//		sceneConf2.setViewPortW(700);
//		sceneConf2.setUrl("/home/xzj/other2.jpg");
//		
//		List<SceneConf> sceneConfs = new ArrayList<SceneConf>();
//		sceneConfs.add(sceneConf);
//		sceneConfs.add(sceneConf2);
//		
//		conf.setScenesConf(sceneConfs);
//		
//		ConfInfo confInfo = new ConfInfo();
//		confInfo.setAuthor("xx");
//		confInfo.setIsSysn("false");
//		
//		conf.setConfInfo(confInfo);
//		
//		ConfDao confdao = new ConfDao("offlineconf");
////		confdao.insertConf("111111", conf);
//		System.out.println(confdao.getAuthorById("cnn04"));
//		System.out.println(confdao.isEdit("cnn04", "xzj"));
//		System.out.println(confdao.isEdit("cnn04", "xiaoxiao"));
//		System.out.println(confdao.isEdit("cnn04", "llllllll"));
//		System.out.println(confdao.isEdit("cnn04", "kk"));
//		Conf conf1 = confdao.getConfById("111111");
//		System.out.println(conf1.toJson());
//		
//		System.out.println(new ConfInfoDao().getFieldById("111111", "isSysn"));
//		
//		new ConfInfoDao().setFieldById("111111", "isSysn", "true");
//		
//		System.out.println(new ConfInfoDao().getFieldById("111111", "isSysn"));
	}
}
