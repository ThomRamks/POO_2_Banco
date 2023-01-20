package interfaces;

public interface IContaInvestimento<T extends IConta> {

    void investir(double valor);
}
