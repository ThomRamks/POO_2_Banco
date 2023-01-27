package util;

import banco.Banco;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;
import interfaces.ICliente;

public class GeraDadosIniciais {

    private static final GeraDadosIniciais dadosIniciais = new GeraDadosIniciais();
    private GeraDadosIniciais(){
    }
    public static GeraDadosIniciais getInstance(){
        return dadosIniciais;
    }
    Banco banco = Banco.getInstance();

    public void carregaDadosIniciais() {
        ICliente alice = new ClientePessoaFisica("Alice Noemi Jorge dos Santos", "123456789", "215.515.516-67");
        banco.abrirContaPessoaFisica(alice);

        ICliente arthur = new ClientePessoaFisica("Arthur Laureano Silva", "123456789", "578.179.380-16");
        banco.abrirContaPessoaFisica(arthur);

        ICliente bruno = new ClientePessoaFisica("Bruno Rafael Ribeiro Martins", "123456789", "038.010.176-99");
        banco.abrirContaPessoaFisica(bruno);

        ICliente diego = new ClientePessoaFisica("Diego Ruescas", "123456789", "270.216.571-02");
        banco.abrirContaPessoaFisica(diego);

        ICliente thomas = new ClientePessoaFisica("Thomas Ramiro", "123456789", "332.244.623-96");
        banco.abrirContaPessoaFisica(thomas);

        ICliente ada = new ClientePessoaJuridica("Ada Tecnologia e Educação S.A.", "123456789", "24.861.255/0001-07");
        banco.abrirContaPessoaJuridica(ada);

        ICliente sinqia = new ClientePessoaJuridica("SINQIA S.A", "123456789", "04.065.791/0001-99");
        banco.abrirContaPessoaJuridica(sinqia);

    }
}