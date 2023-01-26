package interfaces;

import exceptions.ValidatorException;

public interface IValidator<T> {

    void valida(ICliente cliente, T objeto2) throws ValidatorException;

}
