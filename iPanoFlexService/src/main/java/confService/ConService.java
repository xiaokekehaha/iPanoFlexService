package confService;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ConfDao;

import model.Conf;

@Controller
public class ConService {

	static ApplicationContext ctx;
	static Logger logger;
	
	static{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger = Logger.getLogger(ConService.class);
	}
	/**
	 * 获得配置信息　没有发布
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getconf/{id}", method = RequestMethod.GET)
	public @ResponseBody Conf getConf(@PathVariable String id){
		ConfDao confDao = (ConfDao)ctx.getBean("confDao");
		Conf conf = new Conf();
		conf = confDao.getConfById(id);
		logger.info(" - " + " getconf " + " get unsys conf success ");
		return conf;
	}
}
