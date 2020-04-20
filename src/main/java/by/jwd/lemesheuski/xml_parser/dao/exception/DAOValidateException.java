package by.jwd.lemesheuski.xml_parser.dao.exception;

public class DAOValidateException extends DAOException {
    public DAOValidateException() {
        super();
    }

    public DAOValidateException(String message) {
        super(message);
    }

    public DAOValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOValidateException(Throwable cause) {
        super(cause);
    }
}
