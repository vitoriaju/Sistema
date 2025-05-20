package model;

import Pagamento;

public class CartaoCredito implements Pagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado no cartão de crédito.");
    }
}
