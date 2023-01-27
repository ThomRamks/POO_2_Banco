package banco;

import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;
import cliente.TipoPessoa;
import conta.*;
import exceptions.ValidatorException;
import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IValidator;
import util.formata.FormataDocumento;
import util.formata.FormataTexto;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Banco {
    private static final Banco AdaBank = new Banco();
    private HashMap<IConta, ICliente> contasNoBanco = new HashMap<>();
    private int numeroDefault;
    private String clienteLogin;
    private Banco() {
        numeroDefault = 1000;
    }

    public static Banco getInstance() {
        return AdaBank;
    }

    public ICliente registrarConta(String nome, String documento, String senha, String tipoCliente) {
        ICliente cliente = null;
        nome = FormataTexto.upperfirstCase(nome);
        if (tipoCliente.equals("1")) { // parametro NullPointer
            documento = FormataDocumento.formataCpf(documento);
            cliente = new ClientePessoaFisica(nome, senha, documento);
        }
        if (tipoCliente.equals("2")) {
            documento = FormataDocumento.formataCnpj(documento);
            cliente = new ClientePessoaJuridica(nome, senha, documento);
        }
        return cliente;
    }

    public void abrirContaPessoaFisica(ICliente cliente) {
        int numero = 0;
        if (cliente.getContasUsuario().size() > 0) {
            numero = cliente.getContasUsuario().get(0).getNumero();
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta ccPessoaFisica = new ContaCorrentePessoaFisica(numero, cliente);
        IConta ciPessoaFisica = new ContaInvestimentoPessoaFisica(numero, cliente);
        IConta cpPessoaFisica = new ContaPoupanca(numero, cliente);
        Collections.addAll(cliente.getContasUsuario(), ccPessoaFisica, ciPessoaFisica, cpPessoaFisica);
        contasNoBanco.put(ccPessoaFisica, cliente);
        contasNoBanco.put(ciPessoaFisica, cliente);
        contasNoBanco.put(cpPessoaFisica, cliente);
    }

    public void abrirContaPessoaJuridica(ICliente cliente) {
        int numero = 0;
        if (cliente.getContasUsuario().size() > 0) {
            numero = cliente.getContasUsuario().get(0).getNumero();
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta ccPessoaJuridica = new ContaCorrentePessoaJuridica(numero, cliente);
        cliente.getContasUsuario().add(ccPessoaJuridica);
        IConta ciPessoaJuridica = new ContaInvestimentoPessoaJuridica(numero, cliente);
        cliente.getContasUsuario().add(ciPessoaJuridica);
        Collections.addAll(cliente.getContasUsuario(), ccPessoaJuridica, ciPessoaJuridica);
        contasNoBanco.put(ccPessoaJuridica, cliente);
        contasNoBanco.put(ciPessoaJuridica, cliente);
    }


    public boolean contemLogin(String login) {
        login = FormataDocumento.tipoDocumento(login);
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getValue().getDocumento().equals(login)) {
                clienteLogin = contaS.getValue().getDocumento();
                return true;
            }
        }
        return false;
    }

    public ICliente getCliente(String login) {
        ICliente cliente = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getValue().getDocumento().equals(login)) {
                cliente = contaS.getValue();
            }
        }
        return cliente;
    }

    public boolean checarSenha(String senha) {
        ICliente cliente = getCliente(clienteLogin);
        System.out.println(cliente.getNome());
        return cliente.validaSenha(senha);
    }

    public boolean contemConta(int numeroConta) {
        boolean contem = false;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getKey().getNumero() == numeroConta) {
                contem = true;
            }
        }
        return contem;
    }

    public String getClienteLogin() {
        return clienteLogin;
    }

    public String getTipoPessoa(int numeroConta) {
        String tipoPessoa = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getKey().getNumero() == numeroConta) {
                if (contaS.getValue().getTipoPessoa().equals(TipoPessoa.PESSOA_FISICA.getDescricao())) {
                    tipoPessoa = "PF";
                } else if (contaS.getValue().getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA.getDescricao())) {
                    tipoPessoa = "PJ";
                }
            }
        }
        return tipoPessoa;
    }

    public IConta getSubTipoConta(int numeroConta, String operacao) {
        IConta conta = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getKey().getNumero() == numeroConta && contaS.getKey().getOperacao().equals(operacao)) {
                conta = contaS.getKey();
            }
        }
        return conta;
    }

    public void depositar(IConta conta, double valor) {
        conta.depositar(valor);
    }

    public boolean transferir(IConta contaOrigem, double valor, IConta contaDestino) {
        return contaOrigem.transferir(valor, contaDestino);
    }

    public boolean sacar(IConta conta, double valor) {
        return conta.sacar(valor);
    }

    public <T> void valida(IValidator<T> validator, T objeto) throws ValidatorException {
        validator.valida(objeto);
    }
}
