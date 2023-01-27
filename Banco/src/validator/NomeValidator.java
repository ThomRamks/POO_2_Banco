package validator;

import exceptions.ValidatorException;
import interfaces.IValidator;
import util.formata.FormataTexto;

public class NomeValidator implements IValidator<String> {
    @Override
    public void valida(String nome) throws ValidatorException {
        if (nome == null || !FormataTexto.somenteLetras(nome) || nome.isBlank()) {
            throw new ValidatorException("Nome inválido.");
        }
    }
}
