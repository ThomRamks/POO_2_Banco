package util.formata;

public class FormataDocumento {

    public static String removeCaracteresEspeciais(String doc) {
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        return doc;
    }

    public static String formataCpf(String cpf) {
        cpf = removeCaracteresEspeciais(cpf);
        return(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
    }

    public static String formataCnpj(String cnpj) {
        cnpj = removeCaracteresEspeciais(cnpj);
        return (cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14));
    }

    public static String tipoDocumento(String documento) {
        documento = removeCaracteresEspeciais(documento);
        if (documento.length() == 11) {
            documento = formataCpf(documento);
        } else if (documento.length() == 14) {
            documento = formataCnpj(documento);
        }
        return documento;
    }
}
