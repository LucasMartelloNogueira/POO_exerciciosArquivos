package Questao3;

public class Cliente {
    private String nome;
    private int cpf;

    public Cliente(String nome, int cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String novoNome) throws DadoInvalidoException{
        if (novoNome instanceof String){
            this.nome = novoNome;
        }
        else{
            throw new DadoInvalidoException("Erro, parametro passado não é do tipo String");
        }
    }
    
    public int getCpf(){
        return this.cpf;
    }

    public void setCpf(int novoCpf) throws DadoInvalidoException{
        if (novoCpf == (int)novoCpf){
            this.cpf = novoCpf;
        }
        else{
            throw new DadoInvalidoException("Erro, parametro passado não é do tipo int");
        }
        
    }
    
    public void printInfo(){
        System.out.println("nome: " + this.getNome());
        System.out.println("cpf: " + this.getCpf());
    }

}
