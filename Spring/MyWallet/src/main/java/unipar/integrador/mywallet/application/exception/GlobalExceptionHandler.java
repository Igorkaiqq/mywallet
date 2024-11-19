package unipar.integrador.mywallet.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> erros.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(CamposDuplicadosUsuarioException.class)
    public ResponseEntity<Map<String, Object>> handleCamposDuplicadosUsuarioException(CamposDuplicadosUsuarioException ex) {
        Map<String, Object> response = new HashMap<>();
        List<String> messages = new ArrayList<>();

        String[] campos = ex.getMessage().split(", ");
        for (String campo : campos) {
            messages.add("Erro ao salvar usuário, o campo " + campo + " já está em uso em uma conta existente.");
        }

        response.put("messages", messages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
