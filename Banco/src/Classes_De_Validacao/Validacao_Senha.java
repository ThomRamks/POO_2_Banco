package Classes_De_Validacao;

import banco.Banco;
import cliente.Cliente;
import exceptions.ValidatorException;
import interfaces.ICliente;
import interfaces.IValidator;

public class Validacao_Senha implements IValidator<String> {

    private static final Validacao_Senha validator = new Validacao_Senha();
    public static Validacao_Senha getInstance(){
        return validator;
    }

    public  void valida(ICliente cliente, String senha) throws ValidatorException {
        if (!Banco.getInstance().checarSenha(cliente, senha)) {
            throw new ValidatorException("Senha inválida!");
        }
    }
}
