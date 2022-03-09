package com.spring.repository;

import com.spring.model.Speaker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component  // non qualified and de capitalised
//@Repository("speakerRepository")
public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {
    @Override
    public List<Speaker> findAll() {
        List<Speaker> speakers = new ArrayList<>();

        Speaker speaker1 = new Speaker();
        speaker1.setFirstName("ANGELINA");
        speaker1.setLastName("JOLIE");

        Speaker speaker2 = new Speaker();
        speaker2.setFirstName("JULIA");
        speaker2.setLastName("ROBERTS");

        speakers.add(speaker2);

        return speakers;
    }


}