package interfaces;

import exceptions.ValidatorException;

public interface Validator<T> {
    void valida(T objeto) throws ValidatorException;
}
