package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.ModulInfo;
import ru.itis.repositories.PlaneRepository;

import java.util.List;
@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    @Override
    public List<ModulInfo> getInfo() {
        return planeRepository.findAll();
    }
}
