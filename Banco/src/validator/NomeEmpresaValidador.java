package validator;

import excecoes.ValidadorExcecao;
import interfaces.IValidador;

public class NomeEmpresaValidador implements IValidador<String> {
    @Override
    public void valida(String nome) throws ValidadorExcecao {
        if (nome == null || nome.isBlank()) {
            throw new ValidadorExcecao("Nome inválido.");
        }
    }
}
