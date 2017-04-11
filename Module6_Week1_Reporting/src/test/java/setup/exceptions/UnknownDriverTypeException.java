package setup.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnknownDriverTypeException extends RuntimeException {

    private static final long serialVersionUID = -4330888418140567546L;
    private static Logger logger = LogManager.getRootLogger();

    public UnknownDriverTypeException(String msg) {
        logger.error(msg);
    }
}