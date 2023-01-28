package interfaces;

public interface IMenuParametrizado<String, T> extends IMenu {
    void exibir(T objeto);
    void processarOpcao(String opcao, T objeto);
}
