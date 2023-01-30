package validator;

import banco.Banco;
import excecoes.ValidadorExcecao;
import interfaces.IValidador;

public class SenhaLoginValidador implements IValidador<String> {
    @Override
    public void valida(String senhaLogin) throws ValidadorExcecao {
        if (senhaLogin == null || senhaLogin.isBlank() || !Banco.getInstance().checarSenha(senhaLogin)) {
            throw new ValidadorExcecao("Senha Inválida!");
        }
    }
}
