package sayilir.coder.CityService.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sayilir.coder.CityService.Exeception.CityAlreadyExistException;
import sayilir.coder.CityService.Exeception.CityNotFoundException;
import sayilir.coder.CityService.Model.City;
import sayilir.coder.CityService.Repository.CityRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;


    public List<City> getCities(String name) {
        if (name == null) {
            return cityRepository.findAll();
        } else {
            return getCityByName(name);
        }
    }

    public City createCity(City newCity) {
        Optional<City> cityByName = cityRepository.findByName(newCity.getName());
        if (cityByName.isPresent()) {
            throw new CityAlreadyExistException("City Already Exist!!! with name: " + newCity.getName());
        }

        return cityRepository.save(newCity);
    }

    public void deleteCity(String id) {
        cityRepository.deleteById(id);
    }

    public City getCityById(String id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City Not found!!! with id: " + id));

    }

    public void updateCity(String id, City newCity) {
        City oldCity = getCityById(id);
        oldCity.setName(newCity.getName());
        oldCity.setCreatedDate(new Date());
        cityRepository.save(oldCity);

    }

    public List<City> getCityByName(String name) {
        return cityRepository.findAllByName(name);
    }
}
