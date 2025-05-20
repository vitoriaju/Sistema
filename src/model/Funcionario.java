package model;

public class Funcionario extends Pessoa {
	private int id;
    private String cargo;
    private double salario;
    private String dataContratacao;

    public Funcionario(int id, String nome, String cpf, String telefone, String cargo, double salario, String dataContratacao) {
        super(nome, cpf, telefone);
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
    }

    // getters 
    
    public int getId() {
        return id;
    }

    
    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
               "id=" + id +
               ", cargo='" + cargo + '\'' +
               ", salario=" + salario +
               ", dataContratacao='" + dataContratacao + '\'' +
               '}';
    }
}
