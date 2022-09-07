package sayilir.coder.CityService.Exeception;

public class CityAlreadyExistException extends RuntimeException {
    public CityAlreadyExistException(String message) {
        super(message);
    }
}
