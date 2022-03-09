import com.spring.service.SpeakerService;
import com.spring.service.SpeakerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String args[]) {
        // We bootstrap our application and load the xml file
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml"); // here appcontext gives the objects we need
       // SpeakerService service = new SpeakerServiceImpl();
        SpeakerService service = appContext.getBean("speakerService", SpeakerService.class);

        System.out.println(service.findAll().get(0).getFirstName());
    }
}
