package validator;

import banco.Banco;
import excecoes.ValidadorExcecao;
import interfaces.IValidador;

public class CriarLoginValidador implements IValidador<String> {
    @Override
    public void valida(String login) throws ValidadorExcecao {
        if (Banco.getInstance().contemLogin(login)) {
            throw new ValidadorExcecao("Este documento já foi cadastrado previamente!");
        }
    }
}
