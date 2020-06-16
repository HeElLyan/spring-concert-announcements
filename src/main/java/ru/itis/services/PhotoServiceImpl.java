package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Photo;
import ru.itis.repositories.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo getPhoto(Long id) {
        return photoRepository.findById(id);
    }

    @Override
    public Photo findByPath(String path) {
        return photoRepository.findByPath(path);
    }

    @Override
    public void add(String filePath) {
        Photo photo = Photo.builder()
                .path(filePath)
                .build();
        photoRepository.save(photo);
    }
}
