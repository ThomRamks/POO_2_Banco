package util.valida;

import java.util.Arrays;

public class ValidaTexto {
    public static boolean somenteLetras(String texto) {
        for (Character letra : texto.toCharArray()) {
            if (!Character.isLetter(letra) && !Character.isWhitespace(letra)) {
                return false;
            }
        }
        return true;
    }

    public static boolean temEspaco(String texto) {
        for (char c : texto.toCharArray()) {
            if (c == ' ') {
                return true;
            }
        }
        return false;
    }

    public static String upperfirstCase(String texto) {
        String[] palavras = texto.trim().split(" ");
        String textoUpperFirstCase = "";
        for (String s : palavras) {
            if (!s.isBlank()) {
                if (s.equalsIgnoreCase("s.a.")
                        || s.equalsIgnoreCase("ltda")) {
                    textoUpperFirstCase = textoUpperFirstCase.concat(s.toUpperCase());
                } else {
                    textoUpperFirstCase = textoUpperFirstCase.concat(s.substring(0, 1).toUpperCase()
                            .concat(s.substring(1).toLowerCase()) + " ");
                }
            }
        }
        return textoUpperFirstCase.trim();
    }
}
