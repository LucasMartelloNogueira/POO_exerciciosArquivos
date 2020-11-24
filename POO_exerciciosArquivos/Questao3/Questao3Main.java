package Questao3;

import java.util.ArrayList;
import java.io.*;

public class Questao3Main {
    public static void main(String[] args) {

        String nomeArquivo = "RegistroClientes.txt";

        // testando funcao de inserir 
        Cliente cliente1 = new Cliente("Lucas", 166);
        ClienteArquivo.inserir(cliente1, nomeArquivo);
        System.out.println("cliente inserido!");

        System.out.println("---------------------");

        // testando funcao busca por cpf
        int cpf = 182;
        Cliente clienteProcurado = ClienteArquivo.buscaPorCpf(nomeArquivo, cpf);
        System.out.println("achou cliente, nome: " + clienteProcurado.getNome());

        System.out.println("---------------------");

        // testando funcao busca por nome
        String nome = "Lucas";
        ArrayList<Cliente> lista = ClienteArquivo.buscaPorNome(nomeArquivo, nome);
        for (Cliente c : lista){
            System.out.println("cpf : " + c.getCpf());
        }

        System.out.println("---------------------");

        // testando funcao listar
        ArrayList<Cliente> listaClientes = ClienteArquivo.listar(nomeArquivo);
        System.out.println("LISTA CLIENTES:");
        for (Cliente cliente : listaClientes){
            System.out.println("nome: " + cliente.getNome());
            System.out.println("cpf: " + cliente.getCpf());
            System.out.println("----");
        }

        System.out.println("---------------------");

        // testando a funcao excluir
        int cpf2 = 166;
        ClienteArquivo.excluir(nomeArquivo, cpf2);
        
    }
}

