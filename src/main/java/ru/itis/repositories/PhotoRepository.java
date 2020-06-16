package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findById(Long id);
    Photo findByPath(String path);
}
