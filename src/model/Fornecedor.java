package model;

public class Fornecedor {
    private String nomeEmpresa;
    private String cnpj;
    private String telefone;
    private String tipoProduto;

    // Construtor
    public Fornecedor(String nomeEmpresa, String cnpj, String telefone, String tipoProduto) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.tipoProduto = tipoProduto;
    }

    // Getters e Setters
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    @Override
    public String toString() {
        return "Fornecedor{" +
               "nomeEmpresa='" + nomeEmpresa + '\'' +
               ", cnpj='" + cnpj + '\'' +
               ", telefone='" + telefone + '\'' +
               ", tipoProduto='" + tipoProduto + '\'' +
               '}';
    }

}
