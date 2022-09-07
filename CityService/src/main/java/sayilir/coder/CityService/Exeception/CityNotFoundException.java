package sayilir.coder.CityService.Exeception;

import lombok.AllArgsConstructor;


public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String message) {
        super(message);
    }
}
