package com.spring.service;

import com.spring.model.Speaker;
import com.spring.repository.HibernateSpeakerRepositoryImpl;
import com.spring.repository.SpeakerRepository;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository;

    public SpeakerServiceImpl(){
        System.out.println("SoeakerServiceImpl no args constructor");
    }

    public SpeakerServiceImpl(SpeakerRepository speakerRepository){
        System.out.println("SoeakerServiceImpl repository constructor");
        repository=speakerRepository;
    }


    @Override
    public List<Speaker> findAll() {

        return repository.findAll();
    }

    public void setSpeakerRepository(SpeakerRepository repository) {  // setter injection
        System.out.println("SoeakerServiceImpl setter");
        this.repository = repository;
    }
}
