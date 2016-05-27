package confService;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.ConfDao;

public class ServiceImpl extends HttpServlet{

	public static ApplicationContext context;
	public static Logger logger = null;
	static{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger = Logger.getLogger(ServiceImpl.class);
	}
	
	private String getConf(String id){
		ConfDao confDao = (ConfDao)context.getBean("confDao");
		return confDao.getConfById(id).toJson();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String confjson = getConf(id);
		logger.info(" " + " getconf " + id);
		OutputStream outputStream = resp.getOutputStream();
		outputStream.write(confjson.getBytes());
	}
}
