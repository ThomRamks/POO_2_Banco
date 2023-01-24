import conta.ContaCorrentePessoaFisica;
import conta.ContaCorrentePessoaJuridica;
import conta.ContaPoupanca;
import banco.Banco;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;
import util.GeraDadosIniciais;

public class Application {
    public static void main(String[] args) {
        GeraDadosIniciais dadosIniciais = new GeraDadosIniciais();
        dadosIniciais.carregaDadosIniciais();
        Banco banco = Banco.getInstance();

        String documentoArthur = "578.179.380-16";
        banco.listarContasUsuario(documentoArthur);

        String documentoDiego = "123.456.789-10";
        banco.listarContasUsuario(documentoDiego);

        String documentoAda = "24.861.255/0001-07";
        banco.listarContasUsuario(documentoAda);

        String documentoSinqia = "04.065.791/0001-99";
        banco.listarContasUsuario(documentoSinqia);
    }
}
