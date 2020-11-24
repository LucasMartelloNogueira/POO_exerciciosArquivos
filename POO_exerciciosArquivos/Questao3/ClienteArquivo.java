package Questao3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteArquivo {
    
    public static void inserir(Cliente cliente, String nomeArquivo){
        String cpfString = Integer.toString(cliente.getCpf());
        String dadosCliente = cliente.getNome() + "," + cpfString + "\n";
        try{
            FileWriter fw = new FileWriter(nomeArquivo, true);
            fw.write(dadosCliente);
            fw.close();
        }
        catch (IOException e){
            System.out.println("erro: não foi possível escrever no arquivo");
            e.printStackTrace();
        }
    }

    public static Cliente buscaPorCpf(String nomeArquivo, int cpf){
        try{
            FileReader fr = new FileReader(nomeArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = br.readLine();

            while (linha != null){
                String[] dados = linha.split(",");
                int cpfLido = Integer.parseInt(dados[1]);
                if (cpfLido == cpf){
                    System.out.println("achou o cliente!");
                    Cliente clienteProcurado = new Cliente(dados[0], cpfLido);
                    br.close();
                    return clienteProcurado;
                }
                linha = br.readLine();
            }
            System.out.println("Não achou o cliente");
            br.close();
            return null;
        }
        catch (IOException e){
            System.out.println("erro");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Cliente> buscaPorNome(String nomeArquivo, String nome){
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        try{
            FileReader f = new FileReader(nomeArquivo);
            BufferedReader br = new BufferedReader(f);
            
            String linha = br.readLine();

            while (linha != null){
                String[] dados = linha.split(",");

                if (dados[0].equals(nome) == true){
                    int cpfLido = Integer.parseInt(dados[1]);
                    String nomeLido = dados[0];
                    Cliente clienteProcurado = new Cliente(nomeLido, cpfLido);
                    listaClientes.add(clienteProcurado);
                }
                linha = br.readLine();
            }
            br.close();
            return listaClientes;

        }
        catch (IOException e){
            System.out.println("erro");
            e.printStackTrace();
            return listaClientes;
        }
    }

    public static ArrayList<Cliente> listar(String nomeArquivo){
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        try{
            FileReader f = new FileReader(nomeArquivo);
            BufferedReader br = new BufferedReader(f);
            
            String linha = br.readLine();

            while (linha != null){
                String[] dados = linha.split(",");
                String nome = dados[0];
                int cpf = Integer.parseInt(dados[1]);
                Cliente cliente = new Cliente(nome, cpf);
                listaClientes.add(cliente);
                linha = br.readLine();
            }
            br.close();
            return listaClientes;
        }
        catch (IOException e){
            System.out.println("erro");
            e.printStackTrace();
            return listaClientes;
        }
    }

    public static void excluir(String nomeArquivo, int cpf){

        File arquivoAntigo = new File(nomeArquivo);
        File temp = new File("temp.txt");

        try{
            FileReader fAntigo = new FileReader(arquivoAntigo);
            BufferedReader arqAntigo = new BufferedReader(fAntigo);
            
            FileWriter fNovo = new FileWriter(temp);
            BufferedWriter arqNovo = new BufferedWriter(fNovo);

            String linha = arqAntigo.readLine();

            while (linha != null){
                String[] dadosLinha = linha.split(",");
                int cpfLido = Integer.parseInt(dadosLinha[1]);

                if (cpfLido != cpf){
                    arqNovo.write(linha+"\n");
                }
                linha = arqAntigo.readLine();
            }
            
            arqAntigo.close();
            arqNovo.close();

            arquivoAntigo.delete();
            File destino = new File(nomeArquivo);
            temp.renameTo(destino);
            
        }
        catch (IOException e){
            System.out.println("erro");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nome = " ";
        int cpf = 0;
        
        for(int i = 0; i < 2; i++){
            switch (i){
                case 0:
                    nome = scanner.nextLine();
                    break;
                case 1:
                    cpf = scanner.nextInt();
                    break;
            }
        }
        scanner.close();
        System.out.println("nome: " + nome + " / cpf: " + cpf);
    }
}
