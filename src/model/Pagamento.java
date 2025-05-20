package model;

public class Pagamento {
    private String tipo;  

   
    public Pagamento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void processarPagamento(double valorTotal) {
        System.out.println("Processando pagamento de " + valorTotal + " via " + tipo);
    }
}
