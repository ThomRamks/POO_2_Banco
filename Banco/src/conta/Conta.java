package conta;

import cliente.Cliente;
import interfaces.ICliente;
import interfaces.IConta;

public abstract class Conta implements IConta {
    private int agencia;
    private int numero;
    private ICliente titular;
    protected double saldo;

    public Conta(int numero, ICliente titular) {
        this.agencia = 913;
        this.numero = numero;
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getAgencia() {
        return this.agencia;
    }
    public int getNumero() {
        return this.numero;
    }
    public ICliente getTitular() {
        return this.titular;
    }
}
