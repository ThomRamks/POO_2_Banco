package validator;

import banco.Banco;
import exceptions.ValidatorException;
import interfaces.IValidator;

public class LoginValidator implements IValidator<String> {
    @Override
    public void valida(String login) throws ValidatorException {
        if (login == null || login.isBlank() || !Banco.getInstance().contemLogin(login)) {
            throw new ValidatorException("Usuario não encontrado!");
        }
    }
}
