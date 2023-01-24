package util;

import banco.Banco;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;
import interfaces.ICliente;

public class GeraDadosIniciais {
    Banco banco = Banco.getInstance();

    public void carregaDadosIniciais() {
        ICliente arthur = new ClientePessoaFisica("Arthur Laureano Silva", "123456", "578.179.380-16");
        banco.abrirContaPessoaFisica(arthur);

        ICliente diego = new ClientePessoaFisica("Diego Ruescas", "123456", "123.456.789-10");
        banco.abrirContaPessoaFisica(diego);

        ICliente ada = new ClientePessoaJuridica("Ada Tecnologia e Educação S.A.", "123456789", "24.861.255/0001-07");
        banco.abrirContaPessoaJuridica(ada);


        ICliente sinqia = new ClientePessoaJuridica("SINQIA S.A", "123456789", "04.065.791/0001-99");
        banco.abrirContaPessoaJuridica(sinqia);

    }
}