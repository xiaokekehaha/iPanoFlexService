package util;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;



public class HBaseUtil {
	static Logger logger = Logger.getLogger(HBaseUtil.class.getName());
	
	private String zkQuorum;
	private int hTablePoolSize;
	private HTablePool TABLE_POOL;
	static Configuration conf = null;
//	static int hTablePoolSize = 10;
//	static final HTablePool TABLE_POOL;
	
	
//	private static HBaseUtil HBASEUTIL = new HBaseUtil();
//	static{
//		conf = HBaseConfiguration.create();
//		conf.set("hbase-zookeeper.quorum", "localhost");
//		TABLE_POOL = new HTablePool(conf, hTablePoolSize);
//	}
	
	public String getZkQuorum() {
		return zkQuorum;
	}

	public void setZkQuorum(String zkQuorum) {
		this.zkQuorum = zkQuorum;
	}

	public int gethTablePoolSize() {
		return hTablePoolSize;
	}

	public void sethTablePoolSize(int hTablePoolSize) {
		this.hTablePoolSize = hTablePoolSize;
	}

	public void init(){
		conf = HBaseConfiguration.create();
		conf.set("hbase-zookeeper.quorum", zkQuorum);
		TABLE_POOL = new HTablePool(conf, hTablePoolSize);
	}
	
	public HBaseUtil(){
		
	}
	
//	private HBaseUtil(){
//		
//	}
//	public static HBaseUtil getHBaseUtil(){
//		return HBASEUTIL;
//	}
	
	/**
	 * 
	 * @param tableName
	 * @param rowKey
	 * @return
	 */
	public Result getResult(String tableName, String rowKey){
		Get get = new Get(Bytes.toBytes(rowKey));
		Result result = null;
		try {
			result = TABLE_POOL.getTable(tableName).get(get);
			
		} catch (IOException e) {
			logger.info("[ERROR]: Class HBaseUtil Method: getResult get Data error from table: " + 
					tableName + " rowKey: " + rowKey);
			
		}
		return result;
	}
	/**
	 * 
	 * @param tableName
	 * @param rowKey
	 * @param family
	 * @param qualifier
	 * @return
	 */
	public Result getResult(String tableName, String rowKey, String family, String qualifier){
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
		
		Result result = null;
		try {
			result = TABLE_POOL.getTable(tableName).get(get);
			
		} catch (IOException e) {
			logger.info("[ERROR]: Class HBaseUtil Method: getResult get Data error from table: " + 
					tableName + " rowKey: " + rowKey);
			
		}
		return result;
	}
	/**
	 * 
	 * @param tableName
	 * @param put
	 * @return
	 */
	public boolean addRecord(String tableName, Put put){
		try {
			TABLE_POOL.getTable(tableName).put(put);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("[ERROR]: Class HBaseUtil Method: addRecord insert data to table: " + 
					tableName + "rowKey: " + put.getRow().toString() + "error");
			return false;
		}
		logger.info("[INFO]: addRecord ok! rowKey: " + put.getRow().toString());
		return true;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param rowKey
	 * @param columnFamily
	 * @param qualifier
	 * @return
	 */
	public boolean removeRecord(String tableName, String rowKey, String columnFamily, 
				String qualifier){
		Delete delete = new Delete(Bytes.toBytes(rowKey));
		delete.deleteColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier));
		try {
			TABLE_POOL.getTable(tableName).delete(delete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("[ERROR]: Class: HBaseUtil Method: removeRecord remorecord error! "
					+ "rowKey: " + rowKey);
			return false;
		}
		logger.info("[INFO]: removeRecord ok! rowKey: " + rowKey);
		return true;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param rowKey
	 * @return
	 */
	public boolean removeRecord(String tableName, String rowKey){
		Delete delete = new Delete(Bytes.toBytes(rowKey));
		try {
			TABLE_POOL.getTable(tableName).delete(delete);
		} catch (IOException e) {
			logger.info("[ERROR]: Class: HBaseUtil Method: removeRecord delete records  "
					+ "error! rowKey: " + rowKey);
			return false;
		}
		logger.info("[INFO]: removeRecord ok! rowKey: " + rowKey);
		return true;
	}
	
	public boolean removeRecord(String tableName, Delete delete){
		try {
			TABLE_POOL.getTable(tableName).delete(delete);
		} catch (IOException e) {
			logger.info("[ERROR]: Class: HBaseUtil Method: removeRecord delete records  "
					+ "error! ");
			return false;
		}
		logger.info("[INFO]: removeRecord ok! ");
		return true;
	}
	
	public boolean removeRecord(String tableName, List<Delete> deletes){
		try {
			TABLE_POOL.getTable(tableName).delete(deletes);
		} catch (IOException e) {
			logger.info("[ERROR]: Class: HBaseUtil Method: removeRecord delete records  "
					+ "error! ");
			return false;
		}
		logger.info("[INFO]: removeRecord ok! ");
		return true;
	}
	/**
	 * 
	 * @param tableName
	 * @param rowKey
	 * @param columnFamily
	 * @param qualifier
	 * @param value
	 * @return
	 */
	public boolean updataRecord(String tableName, String rowKey, 
				String columnFamily, String qualifier, String value){
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		try {
			TABLE_POOL.getTable(tableName).put(put);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("[ERROR]: Class: HBaseUtil Method: updataRecord updata record error! "
					+ "rowKey : " + rowKey);
			return false;
		}
		logger.info("[INFO]: updataRecord ok! rowKey: " + rowKey);
		return true;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param put
	 * @return
	 */
	public boolean updataRecord(String tableName, Put put){
		return addRecord(tableName, put);
	}
}
