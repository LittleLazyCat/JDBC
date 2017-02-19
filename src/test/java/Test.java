import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Alex.dao.FileWriterService;

public class Test {

	public static void main(String[] args) {
		//加载spring的配置文件，创建容器
	    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
	    //通过Bean的id来对FileWriterService进行注入
	    FileWriterService fileWriterService = applicationContext.getBean("fileWriterService",FileWriterService.class);
	    //调用业务逻辑
	    fileWriterService.write("Hello World");
	    //关闭容器
	    ((ConfigurableApplicationContext)applicationContext).close();

	}

}
