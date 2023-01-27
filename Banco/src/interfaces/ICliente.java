package interfaces;

import java.util.List;

public interface ICliente {
    String getNome();
    String getDocumento();
    List<IConta> getContasUsuario();
    boolean validaSenha(String senha);
    String getTipoPessoa();

}
