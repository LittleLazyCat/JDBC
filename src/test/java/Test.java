import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Alex.dao.FileWriterService;

public class Test {

	public static void main(String[] args) {
		//����spring�������ļ�����������
	    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
	    //ͨ��Bean��id����FileWriterService����ע��
	    FileWriterService fileWriterService = applicationContext.getBean("fileWriterService",FileWriterService.class);
	    //����ҵ���߼�
	    fileWriterService.write("Hello World");
	    //�ر�����
	    ((ConfigurableApplicationContext)applicationContext).close();

	}

}
