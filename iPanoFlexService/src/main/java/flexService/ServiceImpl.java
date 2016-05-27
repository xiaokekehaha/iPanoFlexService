package flexService;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.PointDao;
import model.Point;

public class ServiceImpl {
	
	public static ApplicationContext context;
	public static Logger logger;
	static{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger =Logger.getLogger(ServiceImpl.class);
	}
	
	public void getInterestPoint(String id, float lon, float lat){
		PointDao pointDao = (PointDao)context.getBean("pointDao");
		Point point = new Point(lon, lat);
		pointDao.insertPoint(id, point);
		logger.info(" - " + " getInterestPoint " + id + " " + lon + "_" + lat);
//		System.out.println("insert interest point id : " + id);
	}
	
}
