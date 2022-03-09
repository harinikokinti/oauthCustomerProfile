package com.spring.service;

import com.spring.model.Speaker;
import com.spring.repository.HibernateSpeakerRepositoryImpl;
import com.spring.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

  //  private SpeakerRepository repository = new HibernateSpeakerRepositoryImpl();  // this is a hardcoded instance


    private SpeakerRepository repository;

    public SpeakerServiceImpl() {
        System.out.println("SpeakerServiceImpl no args constructor");
    }

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {  //  constructor injection
        System.out.println("SpeakerServiceImpl repsitory constructor");
        repository = speakerRepository;
    }

    @PostConstruct
    private void initialize() {
        System.out.println("We are called after the constructors");
    }

    @Override
    public List<Speaker> findAll() {
        return repository.findAll();
    }

    @Autowired  // It injects Speaker repository bean into the setter
    public void setRepository(SpeakerRepository repository) {    // create a setter to wire up the configuration , setter injection
        System.out.println("SpeakerServiceImpl setter");
        this.repository = repository;
    }
}
