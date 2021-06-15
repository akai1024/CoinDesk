package kai.sample.common;

import kai.sample.controller.dto.BasicReponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BasicReponse defaultExceptions(HttpServletRequest request, Exception e) {
        try {
            logger.error("捕獲例外, request uri:{}, method:{}", request.getRequestURI(), request.getMethod());
            logger.error(e.getMessage(), e);
        } catch (Exception exception) {
            logger.error(e.getMessage(), exception);
        }
        return new BasicReponse(ResponseCode.SYSTEM_ERROR, e.getMessage());
    }

}
