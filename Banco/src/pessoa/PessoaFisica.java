package pessoa;

public class PessoaFisica extends Pessoa {

    private String cpf;

    public PessoaFisica(String nome, String cnpj) {
        super(nome);
        this.cpf = cnpj;
    }

    public String getCpf() {
        return cpf;
    }
}
