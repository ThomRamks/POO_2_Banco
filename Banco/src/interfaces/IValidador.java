package interfaces;

import excecoes.ValidadorExcecao;

public interface IValidador<T> {
    void valida(T objeto) throws ValidadorExcecao;
}
