import util.valida.ValidaDocumento;
import util.valida.ValidaTexto;

public class Teste {

    public static void main(String[] args) {

        String cpf = "578.179.380-16";
        System.out.printf("CPF %s válido? %b%n", cpf, ValidaDocumento.isCpf(cpf));

        String cnpj = "24.861.255/0001-07";
        System.out.printf("CNPJ %s válido? %b%n", cnpj, ValidaDocumento.isCNPJ(cnpj));

        cnpj = "24861255000107";
        System.out.printf("CNPJ %s válido? %b%n", cnpj, ValidaDocumento.isCNPJ(cnpj));

        cnpj = "24861255/0001-07";
        System.out.printf("CNPJ %s válido? %b%n", cnpj, ValidaDocumento.isCNPJ(cnpj));

        cnpj = "01.234.567/0001-11";
        System.out.printf("CNPJ %s válido? %b%n", cnpj, ValidaDocumento.isCNPJ(cnpj));

        String nome = "Di3go Ruescas";
        System.out.printf("Nome %s é válido? %b%n", nome, ValidaTexto.somenteLetras(nome));

        nome = "Diego Ruescas";
        System.out.printf("Nome %s é válido? %b%n", nome, ValidaTexto.somenteLetras(nome));

        nome = "dIeGo rUesCAS";
        System.out.printf("Nome %s = %s%n", nome, ValidaTexto.upperfirstCase(nome));

        nome = "Ada Tecnologia e Educação S.A.";
        System.out.printf("Nome %s = %s%n", nome, ValidaTexto.upperfirstCase(nome));

        nome = "aDaBAnK ltDa";
        System.out.printf("Nome %s = %s%n", nome, ValidaTexto.upperfirstCase(nome));

        String senha = "12 34 56";
        System.out.printf("Senha %s tem espaço? %b%n", senha, ValidaTexto.temEspaco(senha));

        senha = "123456";
        System.out.printf("Senha %s tem espaço? %b%n", senha, ValidaTexto.temEspaco(senha));




    }

}
