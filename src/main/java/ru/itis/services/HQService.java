package ru.itis.services;

import ru.itis.forms.ConcertForm;
import ru.itis.models.HeadQuarter;
import ru.itis.models.User;

import java.util.List;

public interface HQService {

    HeadQuarter findByCity(String city);
    HeadQuarter findById(Long id);

    List<HeadQuarter> findAllByCity(String city);
    List<HeadQuarter> findAll();

    List<HeadQuarter> recommend(User user);

    boolean add(ConcertForm concertForm);
    boolean delete(Long id);
}
