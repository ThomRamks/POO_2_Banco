package interfaces;

public interface ICliente {
    String getNome();
    String getDocumento();
    boolean validaSenha(String senha);
}
