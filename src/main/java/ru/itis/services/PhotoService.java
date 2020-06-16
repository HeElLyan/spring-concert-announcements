package ru.itis.services;

import ru.itis.models.Photo;

public interface PhotoService {

    Photo getPhoto(Long id);
    Photo findByPath(String path);

    void add(String filePath);
}
