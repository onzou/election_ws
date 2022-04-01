package diagne.election_management_ws.Model.Exceptions;

import diagne.election_management_ws.Entities.Elector.ElectorException;
import diagne.election_management_ws.Model.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
@ControllerAdvice()
public class RestExceptionHandler
{
    @ExceptionHandler(BadAuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(BadAuthenticationException e)
    {
        return ResponseEntity.badRequest()
                .body(new ResponseMessage(e.getMessage()));
    }

    @ExceptionHandler(ElectorException.class)
    public ResponseEntity<Object> handleElectorException(ElectorException e)
    {
        return ResponseEntity.badRequest()
                             .body(new ResponseMessage(e.getMessage()));
    }
}
