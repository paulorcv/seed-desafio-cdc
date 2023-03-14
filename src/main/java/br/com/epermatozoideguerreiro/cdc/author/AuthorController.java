package br.com.epermatozoideguerreiro.cdc.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@Validated
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorAlreadyExistsValidator authorAlreadyExistsValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(authorAlreadyExistsValidator);
    }

    @PostMapping(value = "/api/author")
    @Transactional
    public void create(@Valid @RequestBody NewAuthorRequest request) throws AuthorAlreadyExistsException {
        authorRepository.save(request.toModel());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(AuthorAlreadyExistsException.class)
    public Map<String, String> handleAuthorAlreadyExistsException(
            AuthorAlreadyExistsException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Cadastro de autor", ex.getMessage());
        return errors;
    }
}
