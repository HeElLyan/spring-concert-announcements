package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.ModulInfo;

import java.util.List;

public interface PlaneRepository extends JpaRepository<ModulInfo, Long> {
//    List<ModulInfo> findAll();
}
