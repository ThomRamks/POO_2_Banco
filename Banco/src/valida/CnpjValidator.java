package valida;

import interfaces.Validator;

public class CnpjValidator implements Validator<String> {
    @Override
    public String valida(String cnpj) throws ValidatorException {
        cnpj = removeCaracteresEspeciais(cnpj);

        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                || (cnpj.length() != 14)) {
            throw new ValidatorException("Número de cnpj inválido.");
        }

        char dig13;
        char dig14;
        int sm;
        int i;
        int r;
        int num;
        int peso;

        sm = 0;
        peso = 2;
        for (i = 11; i >= 0; i--) {
            num = (int) (cnpj.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10) {
                peso = 2;
            }
        }

        r = sm % 11;
        if ((r == 0) || (r == 1)) {
            dig13 = '0';
        } else {
            dig13 = (char) ((11 - r) + 48);
        }

        sm = 0;
        peso = 2;
        for (i = 12; i >= 0; i--) {
            num = (int) (cnpj.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10) {
                peso = 2;
            }
        }

        r = sm % 11;
        if ((r == 0) || (r == 1)) {
            dig14 = '0';
        } else {
            dig14 = (char) ((11 - r) + 48);
        }

        if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
            return formataCnpj(cnpj);
        } else {
            throw new ValidatorException("Número de cnpj inválido.");
        }
    }

    private static String removeCaracteresEspeciais(String doc) {
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

    public static String formataCnpj(String cnpj) {
        cnpj = removeCaracteresEspeciais(cnpj);
        return (cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "." + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14));
    }
}
