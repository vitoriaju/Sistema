package model;

public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro() {
        super("Dinheiro");  
    }

    @Override
    public void processarPagamento(double valorTotal) {
        System.out.println("Pagamento de " + valorTotal + " realizado em dinheiro.");
    }
}
