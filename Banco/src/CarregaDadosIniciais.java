import conta.ContaCorrentePessoaFisica;
import conta.ContaCorrentePessoaJuridica;
import conta.ContaInvestimentoPessoaFisica;
import conta.ContaPoupanca;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;

public class CarregaDadosIniciais {

    public void criarDados() {

        ClientePessoaFisica alice = new ClientePessoaFisica("Alice Noemi Jorge Dos Santos", "580.380.430-49");
        ClientePessoaFisica arthur = new ClientePessoaFisica("Arthur Laureano Silva", "578.179.380-16");
        ClientePessoaFisica bruno = new ClientePessoaFisica("Bruno Rafael Ribeiro Martins", "252.243.160-90");
        ClientePessoaFisica diego = new ClientePessoaFisica("Diego Ruescas", "236.966.140-25");
        ClientePessoaFisica thomas = new ClientePessoaFisica("Thomas Ramiro", "305.378.440-82");

        ClientePessoaJuridica ada = new ClientePessoaJuridica("Ada Tecnologia e Educação S.A.", "24.861.255/0001-07");
        ClientePessoaJuridica sinqia = new ClientePessoaJuridica("SINQIA S.A", "04.065.791/0001-99");

//        ContaCorrentePessoaFisica ccAlice = new ContaCorrentePessoaFisica(alice);
//        ContaCorrentePessoaFisica ccArthur = new ContaCorrentePessoaFisica(arthur);
//        ContaCorrentePessoaFisica ccBruno = new ContaCorrentePessoaFisica(bruno);
//        ContaCorrentePessoaFisica ccDiego = new ContaCorrentePessoaFisica(diego);
//        ContaCorrentePessoaFisica ccThomas = new ContaCorrentePessoaFisica(thomas);
//
//        ContaCorrentePessoaJuridica ccAda = new ContaCorrentePessoaJuridica(ada);
//        ContaCorrentePessoaJuridica ccSinqia = new ContaCorrentePessoaJuridica(sinqia);
//
//        ContaPoupanca cpAlice = new ContaPoupanca(alice);
//        ContaPoupanca cpArthur = new ContaPoupanca(arthur);
//        ContaPoupanca cpBruno = new ContaPoupanca(bruno);
//        ContaPoupanca cpDiego = new ContaPoupanca(diego);
//        ContaPoupanca cpThomas = new ContaPoupanca(thomas);
//
//        ContaInvestimentoPessoaFisica ciAlice = new ContaInvestimentoPessoaFisica(alice);
//        ContaInvestimentoPessoaFisica ciArthur = new ContaInvestimentoPessoaFisica(arthur);
//        ContaInvestimentoPessoaFisica ciBruno = new ContaInvestimentoPessoaFisica(bruno);
//        ContaInvestimentoPessoaFisica ciDiego = new ContaInvestimentoPessoaFisica(diego);
//        ContaInvestimentoPessoaFisica ciThomas = new ContaInvestimentoPessoaFisica(thomas);
//
//        ContaCorrentePessoaJuridica ciAda = new ContaCorrentePessoaJuridica(ada);
//        ContaCorrentePessoaJuridica ciSinqia = new ContaCorrentePessoaJuridica(sinqia);
    }



}
