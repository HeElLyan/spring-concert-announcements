package ru.itis.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.HeadQuarter;

import java.util.List;

public interface HQRepository extends JpaRepository<HeadQuarter, Long> {
    List<HeadQuarter> findAllByCity(String city);
//    List<HeadQuarter> findAllByCommentsContains(User user);

    HeadQuarter findByCity(String city);
    HeadQuarter findById(Long id);

}
