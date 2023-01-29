package validator;

import exceptions.ValidatorException;
import interfaces.IValidator;
import util.formata.FormataDocumento;

import java.util.InputMismatchException;

public class CNPJValidator implements IValidator<String> {
    public static boolean isCNPJ(String cnpj) {

        cnpj = FormataDocumento.removeCaracteresEspeciais(cnpj);
        String invalid = "00000000000000111111111111112222222222222233333333333333444444444444445555555555555566666666666666777777777777778888888888888899999999999999";

        if (invalid.contains(cnpj) || (cnpj.length() != 14)) {
            return false;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
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
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    @Override
    public void valida(String cnpj) throws ValidatorException {
        if (!isCNPJ(cnpj)) {
            throw new ValidatorException("CNPJ inválido.");
        }
    }
}
