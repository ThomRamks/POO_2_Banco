package validator;

import banco.Banco;
import exceptions.ValidatorException;
import interfaces.IValidator;

public class CriarLoginValidator implements IValidator<String> {
    @Override
    public void valida(String login) throws ValidatorException {
        if (Banco.getInstance().contemLogin(login)) {
            throw new ValidatorException("Este documento já foi cadastrado previamente!");
        }
    }
}
