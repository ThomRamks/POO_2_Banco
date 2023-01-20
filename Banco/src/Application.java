import conta.*;
import banco.Banco;
import pessoa.PessoaFisica;
import pessoa.PessoaJuridica;

public class Application {
    public static void main(String[] args) {
        PessoaFisica pessoaFisica = new PessoaFisica("Diego", "123.456.789-10");
        PessoaJuridica pessoaJuridica = new PessoaJuridica("Ada", "12.455.455/0001-00");


        ContaPoupanca contaPoupanca = new ContaPoupanca(123,45678, pessoaFisica);
        ContaCorrentePessoaFisica ccPessoaFisica = new ContaCorrentePessoaFisica(321, 7898, pessoaFisica);
        ContaCorrentePessoaJuridica ccPessoaJuridica = new ContaCorrentePessoaJuridica(456, 445588, pessoaJuridica);
        ContaInvestimentoPessoaFisica ciPessoaFisica = new ContaInvestimentoPessoaFisica(321, 7898, pessoaFisica);
        ContaInvestimentoPessoaJuridica ciPessoaJuridica = new ContaInvestimentoPessoaJuridica(456, 445588, pessoaJuridica);


        Banco operacao = new Banco();
        operacao.depositar(contaPoupanca, 100);
        operacao.depositar(ccPessoaFisica, 100);
        operacao.depositar(ccPessoaJuridica, 200);
        operacao.depositar(ciPessoaFisica, 100);
        operacao.depositar(ciPessoaJuridica, 200);

        System.out.printf("Saldo Poupança: %.2f%n",contaPoupanca.getSaldo());
        System.out.printf("Saldo Conta Corrente PF: %.2f%n",ccPessoaFisica.getSaldo());
        System.out.printf("Saldo Conta Corrente PJ: %.2f%n",ccPessoaJuridica.getSaldo());
        System.out.printf("Saldo da Conta de Investimento PF: %.2f%n", ciPessoaFisica.getSaldo());
        System.out.printf("Saldo da Conta de Investimento PJ: %.2f%n", ciPessoaJuridica.getSaldo());

        System.out.println();

        boolean transferiu  = operacao.transferir(ccPessoaJuridica, 100, ccPessoaFisica);

        if (transferiu) {
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Transferência não realizada!");
        }

        System.out.println();

        System.out.printf("Saldo Poupança: %.2f%n",contaPoupanca.getSaldo());
        System.out.printf("Saldo Conta Corrente PF: %.2f%n",ccPessoaFisica.getSaldo());
        System.out.printf("Saldo Conta Corrente PJ: %.2f%n",ccPessoaJuridica.getSaldo());

    }
}
