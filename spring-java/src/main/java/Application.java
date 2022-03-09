/*
CONFERENCE REGISTRATION APPLICATION

 */


import com.spring.service.SpeakerService;
import com.spring.service.SpeakerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String args[]) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class); // loading spring config file
        // It creates a registry with 2 beans in it.
       // SpeakerService service = new SpeakerServiceImpl();
        SpeakerService service = appContext.getBean("speakerService",SpeakerService.class);  // returns speakerService object
        System.out.println(service);
       System.out.println(service.findAll().get(0).getFirstName());
        SpeakerService service2 = appContext.getBean("speakerService",SpeakerService.class);
        System.out.println(service2);
        //System.out.println(service1.findAll().get(0).getFirstName());
    }
}
