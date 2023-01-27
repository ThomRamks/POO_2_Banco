package validator;

import exceptions.ValidatorException;
import interfaces.IValidator;
import util.formata.FormataTexto;

public class SenhaValidator  implements IValidator<String> {
    @Override
    public void valida(String senha) throws ValidatorException {
        if (senha == null || senha.length() < 8 || senha.length() > 32 || FormataTexto.temEspaco(senha)) {
            throw new ValidatorException("A senha deve estar entre 8 e 32 caracteres e não pode haver espaço.");
        }
    }
}
