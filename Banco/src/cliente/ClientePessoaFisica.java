package cliente;

public class ClientePessoaFisica extends Cliente {
    private String cpf;

    public ClientePessoaFisica(String nome, String senha, String cpf) {
        super(nome, senha);
        this.cpf = cpf;
    }

    @Override
    public String getDocumento() {
        return this.cpf;
    }

    @Override
    public String getTipoPessoa() {
        return TipoPessoa.PESSOA_FISICA.getDescricao();
    }
}
