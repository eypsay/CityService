package sayilir.coder.CityService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sayilir.coder.CityService.Model.City;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    List<City> findAllByName(String name);
    Optional<City> findByName(String name);
}
