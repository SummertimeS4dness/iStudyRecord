package mvc.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Global exception handler class.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle exceptions.
     *
     * @param ex exception
     * @throws Exception the exception
     */
    @ExceptionHandler(Exception.class)
    public void handleIOException(Exception ex) throws Exception {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        ex.printStackTrace(printWriter);
        logger.error(result.toString());
    }
}
