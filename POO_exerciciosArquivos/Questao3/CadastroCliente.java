package Questao3;

public class CadastroCliente {
    Cliente[] listaClientes;
    int indiceAtual;

    public CadastroCliente(){
        this.listaClientes = new Cliente[10];
        this.indiceAtual = 0;
    }

    public void inserir(String nome, int cpf) throws RepositorioException, ClienteJaExistenteException{

        if (this.indiceAtual == 0){
            Cliente cliente = new Cliente(nome, cpf);
            this.listaClientes[indiceAtual] = cliente;
            this.indiceAtual += 1;
        }
        else if (this.indiceAtual == 10){
            throw new RepositorioException("Erro, não pode mais inserir clientes no cadastro, memória cheia");
        }
        else{
            try {
                this.buscaPorCpf(cpf);
                throw new ClienteJaExistenteException("Erro, cliente com mesmo cpf já está no cadastro");
            }
            catch (ClienteInexistenteException e){
                Cliente cliente = new Cliente(nome, cpf);
                this.listaClientes[indiceAtual] = cliente;
                this.indiceAtual += 1;
            }
        }
        
    }

    public Cliente buscaPorCpf(int cpf) throws ClienteInexistenteException {
        for (int i = 0; i < this.indiceAtual; i++){
            Cliente cliente = this.listaClientes[i];
            if (cliente.getCpf() == cpf){
                return cliente;
            }
        }
        throw new ClienteInexistenteException("Erro, cliente não existente");
    }

}
