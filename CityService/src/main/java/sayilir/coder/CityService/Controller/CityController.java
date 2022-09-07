package sayilir.coder.CityService.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sayilir.coder.CityService.Exeception.CityAlreadyExistException;
import sayilir.coder.CityService.Exeception.CityNotFoundException;
import sayilir.coder.CityService.Model.City;
import sayilir.coder.CityService.Service.CityService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v2")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities(@RequestParam(required = false) String name) {

        return new ResponseEntity<>(cityService.getCities(name), OK);
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> getCity(@PathVariable("id") String id) {

        return new ResponseEntity<>(cityService.getCityById(id), OK);
    }


   /* @GetMapping("/cities/{id}")
    public ResponseEntity<City> getCityBADCODEFOREXEPTION(@PathVariable("id") String id) {
        try{
        return new ResponseEntity<>(cityService.getCityById(id), OK);
        }
        catch (CityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }*/


    @PostMapping("/cities")
    public ResponseEntity<City> createCity(@RequestBody City newCity) {

        return new ResponseEntity<City>(cityService.createCity(newCity), CREATED);
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
        cityService.updateCity(id, newCity);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable("id") String id) {

        cityService.deleteCity(id);
        return new ResponseEntity<>(OK);
    }


    private City getCityById(String id) {
        return cityService.getCityById(id);


    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleNotFoundExeption(CityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(CityAlreadyExistException.class)
    public ResponseEntity<String> handleCityAllReadyExistExeption(CityAlreadyExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
