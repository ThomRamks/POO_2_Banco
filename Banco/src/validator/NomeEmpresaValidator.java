package validator;

import exceptions.ValidatorException;
import interfaces.IValidator;

public class NomeEmpresaValidator implements IValidator<String> {
    @Override
    public void valida(String nome) throws ValidatorException {
        if (nome == null || nome.isBlank()) {
            throw new ValidatorException("Nome inválido.");
        }
    }
}
