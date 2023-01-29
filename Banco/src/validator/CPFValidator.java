package validator;

import exceptions.ValidatorException;
import interfaces.IValidator;
import util.formata.FormataDocumento;

import java.util.InputMismatchException;

public class CPFValidator implements IValidator<String> {

    public static boolean isCPF(String cpf) {
        cpf = FormataDocumento.removeCaracteresEspeciais(cpf);
        String invalid = "00000000000111111111112222222222233333333333444444444445555555555566666666666777777777778888888888899999999999";
        if (invalid.contains(cpf) || (cpf.length() != 11)) {
            return false;
        }

        char dig10;
        char dig11;
        int sm;
        int i;
        int r;
        int num;
        int peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    @Override
    public void valida(String cpf) throws ValidatorException {
        if (!isCPF(cpf)) {
            throw new ValidatorException("CPF inválido.");
        }
    }

}
