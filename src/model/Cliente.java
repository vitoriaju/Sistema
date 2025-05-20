package model;
import java.util.Date;

public class Cliente extends Pessoa {
	private int cliente_id;
    private String endereco;
    private String dataCadastro;

    public Cliente(int cliente_id, String nome, String cpf, String telefone, String endereco,String dataCadastro) {
       super(nome,cpf,telefone);
       this.cliente_id = cliente_id; 
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }
    public int getClienteId() {
        return cliente_id;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + cliente_id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", dataCadastro='" + dataCadastro + '\'' +
                '}';
    }
    
}



