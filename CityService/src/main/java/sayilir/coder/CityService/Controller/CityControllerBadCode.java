package sayilir.coder.CityService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sayilir.coder.CityService.Model.City;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1")
public class CityControllerBadCode {

    private static final List<City> cities = new ArrayList<>();

    public CityControllerBadCode() {
        if (cities.isEmpty()) {
            City city1 = new City("1", "Ankara", new Date());
            City city2 = new City("2", "Gantep", new Date());

            cities.add(city1);
            cities.add(city2);
        }
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities() {


        return new ResponseEntity<>(cities, OK);
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> getCity(@PathVariable("id") String id) {

        City result = getCityById(id);

        return new ResponseEntity<>(result, OK);
    }


    @PostMapping("/cities")
    public ResponseEntity<City> createCity(@RequestBody City newCity) {
        newCity.setCreatedDate(new Date());
        cities.add(newCity);

        return new ResponseEntity<>(newCity, CREATED);
        //200 ler HHTP'de OK anlanmına gerlir fakat
        //GET->200->OK
        //POST->201->ACCEPTED(birseyler olustu )
        //POST->202->Request ler Batch çalıştırır bunun donousu
        //404->Aradgım obj backedn yok
        //listlerde bulunmayan bir obj icin null ve 400 geri verilmez. sadece bos bir list
        // ve 200 geri verileir bunu anlamı aradıgımı bulamadın demektir
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<Void> updateCity(@PathVariable("id") String id, @RequestBody City newCity) {
        City oldCity = getCityById(id);
        oldCity.setName(newCity.getName());
        oldCity.setCreatedDate(new Date());
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable("id") String id) {
        City city = getCityById(id);
        cities.remove(city);
        return new ResponseEntity<>(OK);
    }


    private City getCityById(String id) {
        City result = cities.stream()
                .filter(il -> il.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("City Not found!!! "));
        return result;
    }
}
