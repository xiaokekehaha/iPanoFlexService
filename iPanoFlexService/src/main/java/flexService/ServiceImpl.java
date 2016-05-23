package flexService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.PointDao;
import model.Point;

public class ServiceImpl {
	
	public static ApplicationContext context;
	static{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public void getInterestPoint(String id, float lon, float lat){
		PointDao pointDao = (PointDao)context.getBean("pointDao");
		Point point = new Point(lon, lat);
		pointDao.insertPoint(id, point);
	}
	
}
