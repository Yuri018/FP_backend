package de.aittr.team24_FP_backend.exception_handling;

import de.aittr.team24_FP_backend.exception_handling.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonAdvice {
    @ExceptionHandler(UserLoginNotFoundException.class)
    public ResponseEntity<Response> handleException(UserLoginNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Response> handleException(UserAlreadyExistsException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RestaurantsNotFoundException.class)
    public ResponseEntity<Response> handleException(RestaurantsNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RestaurantValidationException.class)
    public ResponseEntity<Response> handleException(RestaurantValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RestaurantUpdateException.class)
    public ResponseEntity<Response> handleException(RestaurantUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Response> handleException(CityNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ShopsNotFoundException.class)
    public ResponseEntity<Response> handleException(ShopsNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ShopUpdateException.class)
    public ResponseEntity<Response> handleException(ShopUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ShopValidationException.class)
    public ResponseEntity<Response> handleException(ShopValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ChildrenValidationException.class)
    public ResponseEntity<Response> handleException(ChildrenValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ChildrenNotFoundException.class)
    public ResponseEntity<Response> handleException(ChildrenNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ChildrenUpdateException.class)
    public ResponseEntity<Response> handleException(ChildrenUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DoctorValidationException.class)
    public ResponseEntity<Response> handleException(DoctorValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<Response> handleException(DoctorNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DoctorUpdateException.class)
    public ResponseEntity<Response> handleException(DoctorUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HairBeautyValidationException.class)
    public ResponseEntity<Response> handleException(HairBeautyValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HairBeautyNotFoundException.class)
    public ResponseEntity<Response> handleException(HairBeautyNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HairBeautyUpdateException.class)
    public ResponseEntity<Response> handleException(HairBeautyUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LegalServiceValidationException.class)
    public ResponseEntity<Response> handleException(LegalServiceValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LegalServiceNotFoundException.class)
    public ResponseEntity<Response> handleException(LegalServiceNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LegalServiceUpdateException.class)
    public ResponseEntity<Response> handleException(LegalServiceUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PharmaciesValidationException.class)
    public ResponseEntity<Response> handleException(PharmaciesValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PharmaciesNotFoundException.class)
    public ResponseEntity<Response> handleException(PharmaciesNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PharmaciesUpdateException.class)
    public ResponseEntity<Response> handleException(PharmaciesUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TranslatorValidationException.class)
    public ResponseEntity<Response> handleException(TranslatorValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TranslatorNotFoundException.class)
    public ResponseEntity<Response> handleException(TranslatorNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TranslatorUpdateException.class)
    public ResponseEntity<Response> handleException(TranslatorUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserLoginValidationException.class)
    public ResponseEntity<Response> handleException(UserLoginValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
