package dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import model.SceneConf;

public class SceneConfDao {
	static final String SCENEFAMILY = "scenes";
	static Logger logger = Logger.getLogger(SceneConf.class.getName());
	
	/**
	 * 
	 * @param result
	 * @param num
	 * @return
	 */
	public List<SceneConf> getSceneConf(Result result, int num){
		List<SceneConf> sceneConfs = new ArrayList<SceneConf>();
		for (int i = 0; i < num; i++){
			sceneConfs.add(new SceneConf());
		}
		Class<SceneConf> cls = SceneConf.class;
		Map<byte[], byte[]> map = result.getFamilyMap(Bytes.toBytes(SCENEFAMILY));
		if (map.isEmpty()){
			return null;
		}
		for (Map.Entry<byte[], byte[]> entry : map.entrySet()){
			String key = Bytes.toString(entry.getKey());
			int index = key.charAt(key.length() - 1) - '1';
			String name = key.substring(0, key.length() - 1);
			try {
				Field field = cls.getDeclaredField(name);
				field.setAccessible(true);
				String type = field.getType().getSimpleName();
				if (type.equals("String")){
					String value = Bytes.toString(entry.getValue());
					field.set(sceneConfs.get(index), value);
				}
				if (type.equals("float")){
					float value = new Float(Bytes.toString(entry.getValue()));
					field.set(sceneConfs.get(index), value);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("[ERROR]: Class: SceneConfDao Method: getSceneConf set " + 
						name + " error!");
				e.printStackTrace();
			}
		}
		return sceneConfs;
	}
	
	/**
	 * 
	 * @param sceneConfs
	 * @param put
	 * @return
	 */
	public boolean setHBasePut(List<SceneConf> sceneConfs, Put put){
		for (int i = 0; i < sceneConfs.size(); i++){
			if (!setHBasePut(sceneConfs.get(i), i, put)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param sceneConf
	 * @param sceneId
	 * @param put
	 * @return
	 */
	public boolean setHBasePut(SceneConf sceneConf, int sceneId, Put put){
		Field[] fields = sceneConf.getClass().getDeclaredFields();
		for (Field field : fields){
			field.setAccessible(true);
			String qualifier = field.getName() + (sceneId + 1);
			String value = null;
			try {
				if ("String".equals(field.getType().getSimpleName())){
					value = (String)field.get(sceneConf);
				}
				if ("float".equals(field.getType().getSimpleName())){
					value = ((Float)field.get(sceneConf)).toString();
				}
				put.add(Bytes.toBytes(SCENEFAMILY), Bytes.toBytes(qualifier), 
						Bytes.toBytes(value));
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("[ERROR]: Class: SceneConfDao Method: setHBasePut set"
						+ " HBase put error!");
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
		delete.addFamily(Bytes.toBytes(SCENEFAMILY));
		return true;
	}
	/**
	 * 
	 * @param rowKey
	 * @param sceneId
	 * @return
	 */
	public List<Delete> setHBaseDelete(String rowKey, int sceneId){
		List<Delete> deletes = new ArrayList<Delete>();
		Field[] fields = SceneConf.class.getDeclaredFields();
		for (Field field : fields){
			String qualifier = field.getName() + (sceneId + 1);
			Delete delete = new Delete(Bytes.toBytes(rowKey));
			delete.addColumn(Bytes.toBytes(SCENEFAMILY), Bytes.toBytes(qualifier));
			deletes.add(delete);
		}
		return deletes;
	}
}
