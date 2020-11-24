package Questao3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TelaCliente {

    public static void criaMenu(String nomeArquivo) throws InputMismatchException{

        boolean mainLoop = true;

        while (mainLoop == true){
            System.out.println("0 - para encerrar o programa");
            System.out.println("1 - cadastrar novo cliente");
            System.out.println("2 - buscar cliente pelo nome");
            System.out.println("3 - buscar cliente pelo cpf");
            System.out.println("4 - excluir cliente");
            System.out.println("5 - listar cliente(s)");

            Scanner scanner = new Scanner(System.in);
            int numEscolhido;

            try{
                System.out.print("digite um opção: ");
                numEscolhido = scanner.nextInt();

                switch (numEscolhido){
                    case 0:
                        mainLoop = false;
                        scanner.close();
                        break;
                    case 1:
                        cadastarNovoCliente(nomeArquivo);
                        break;
                    case 2:
                        buscarClientePeloNome(nomeArquivo);
                        break;
                    case 3:
                        buscarClientePeloCpf(nomeArquivo);
                        break;
                    case 4:
                        excluirCliente(nomeArquivo);
                        break;
                    case 5:
                        listarClientes(nomeArquivo);
                        break;
                    default:
                        System.out.println("\nerro, selecione um numero valido\n");
                }
            }
            catch (InputMismatchException e){
                System.out.println("\nerro, entrada deve ser um numero\n");
            }
            
        }
    }

    public static void cadastarNovoCliente(String nomeArquivo){
        Scanner scanner = new Scanner(System.in);
        String nome = " ";
        int cpf = 0;

        for (int i = 0; i < 2; i++){
            if (i == 0){
                System.out.println("digite o nome: ");
                nome = scanner.nextLine();
            }
            if (i == 1){
                System.out.println("digite o cpf: ");
                cpf = scanner.nextInt();
            }
        }

        Cliente novoCliente = new Cliente(nome, cpf);
        try{
            ClienteArquivo.inserir(novoCliente, nomeArquivo);
            System.out.println("\nCliente cadastrado com sucesso\n");
        }
        catch(Exception e){
            System.out.println("n foi possivel cadastrar o cliente");
        }
    }

    public static void buscarClientePeloNome(String nomeArquivo){
        Scanner scanner = new Scanner(System.in);
        String nome;

        System.out.println("digite o nome do cliente: ");
        nome = scanner.nextLine();

        ArrayList<Cliente> listaClientesEncontrados = ClienteArquivo.buscaPorNome(nomeArquivo, nome);

        if (listaClientesEncontrados.size() == 0){
            System.out.println("nenhum cliente com esse nome encontrado");
        }
        else{
            System.out.println("cliente(s) encontrado(s)!");
            
            for (Cliente cliente : listaClientesEncontrados){
                cliente.printInfo();
                System.out.println("----");
            }
        }
    }

    public static void buscarClientePeloCpf(String nomeArquivo){
        Scanner scanner = new Scanner(System.in);
        int cpf;

        System.out.println("digite o cpf (sem pontos ou tracos): ");
        cpf = scanner.nextInt();

        Cliente cliente = ClienteArquivo.buscaPorCpf(nomeArquivo, cpf);

        if (cliente == null){
            System.out.println("nenhum cliente encontrado");
        }
        else{
            System.out.println("cliente encontrado!");
            cliente.printInfo();
        }
    }

    public static void excluirCliente(String nomeArquivo){
        Scanner scanner = new Scanner(System.in);
        int cpf;

        System.out.println("digite o cpf (sem pontos ou tracos): ");
        cpf = scanner.nextInt();

        ClienteArquivo.excluir(nomeArquivo, cpf);
        System.out.println("cliente excluido com sucesso");
    }

    public static void listarClientes(String nomeArquivo){
        ArrayList<Cliente> listaClientes = ClienteArquivo.listar(nomeArquivo);
        System.out.println("----------------------");
        System.out.println("LISTA CLIENTES: ");
        for (Cliente cliente : listaClientes){
            cliente.printInfo();
            System.out.println("--------");
        }
    }

    public static void main(String[] args) {
        String nomeArquivo = "RegistroClientes.txt";
        criaMenu(nomeArquivo);
    }
}
