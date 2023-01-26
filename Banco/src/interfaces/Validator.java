package interfaces;

import valida.ValidatorException;

public interface Validator<T> {
    String valida(T objeto) throws ValidatorException;
}
