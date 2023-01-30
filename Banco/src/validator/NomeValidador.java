package validator;

import excecoes.ValidadorExcecao;
import interfaces.IValidador;
import util.formata.FormataTexto;

public class NomeValidador implements IValidador<String> {
    @Override
    public void valida(String nome) throws ValidadorExcecao {
        if (nome == null || !FormataTexto.somenteLetras(nome) || nome.isBlank()) {
            throw new ValidadorExcecao("Nome inválido.");
        }
    }
}
