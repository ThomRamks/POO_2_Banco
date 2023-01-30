package validator;

import banco.Banco;
import excecoes.ValidadorExcecao;
import interfaces.IValidador;

public class LoginValidador implements IValidador<String> {
    @Override
    public void valida(String login) throws ValidadorExcecao {
        if (login == null || login.isBlank() || !Banco.getInstance().contemLogin(login)) {
            throw new ValidadorExcecao("Usuario não encontrado!");
        }
    }
}
