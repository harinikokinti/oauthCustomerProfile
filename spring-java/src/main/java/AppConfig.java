/*
APPLICATION CONFIGURATION FILE

@Configuration at class level  replaces applicationContext.xml
@Bean at method level  ( Spring Beans defined by @Bean )

SETTER INJECTION:
It is like a method call, simply calling a setter of a Bean

CONSTRUCTOR INJECTION

 */

import com.spring.repository.HibernateSpeakerRepositoryImpl;
import com.spring.repository.SpeakerRepository;
import com.spring.service.SpeakerService;
import com.spring.service.SpeakerServiceImpl;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan({"com.spring"})   // This is where we want you to start scanning for beans to autowire. it searches for the cmponenets by its type 
public class AppConfig {

    /*
    @Bean(name = "speakerService")
    @Scope(value= BeanDefinition.SCOPE_PROTOTYPE)
    public SpeakerService getSpeakerService() {
        //return new SpeakerServiceImpl();
        //SpeakerServiceImpl service = new SpeakerServiceImpl(getSpeakerRepository());  // for constructor injection
        SpeakerServiceImpl service = new SpeakerServiceImpl();
        //service.setRepository(getSpeakerRepository());   //  for setter injection
        return service;
    }

    @Bean(name = "speakerRepository")
    public SpeakerRepository getSpeakerRepository() {
        return new HibernateSpeakerRepositoryImpl();
    }  */
}
