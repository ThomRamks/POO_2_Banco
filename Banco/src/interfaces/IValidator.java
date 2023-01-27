package interfaces;

import exceptions.ValidatorException;

public interface IValidator<T> {
    void valida(T objeto) throws ValidatorException;
}
