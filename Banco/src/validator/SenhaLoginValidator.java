package validator;

import banco.Banco;
import exceptions.ValidatorException;
import interfaces.ICliente;
import interfaces.IValidator;

public class SenhaLoginValidator implements IValidator<String> {
    @Override
    public void valida(String senhaLogin) throws ValidatorException {
        if (senhaLogin == null || senhaLogin.isBlank() || !Banco.getInstance().checarSenha(senhaLogin)) {
            throw new ValidatorException("Senha Inválida!");
        }
    }
}
