package validator;

import excecoes.ValidadorExcecao;
import interfaces.IValidador;
import util.formata.FormataTexto;

public class CriarSenhaValidador implements IValidador<String> {
    @Override
    public void valida(String senha) throws ValidadorExcecao {
        if (senha == null || senha.length() < 8 || senha.length() > 32 || FormataTexto.temEspaco(senha)) {
            throw new ValidadorExcecao("A senha deve estar entre 8 e 32 caracteres e não pode haver espaço.");
        }
    }
}
