package operacoesgrafo;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class OperacoesGrafoCliente {

    private int porta;
    private OperacoesGrafo.Client client;
    private TTransport transport;
    private static Scanner s = new Scanner(System.in);

    public OperacoesGrafoCliente(int porta) {

        this.porta = porta;
        ligarCliente();

    }

    //Inicialia a conexao do cliente no server
    private void ligarCliente() {

        this.transport = new TSocket("127.0.0.1", this.porta);

        try {
            this.transport.open();
        } catch (TTransportException ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        TProtocol protocol = new TBinaryProtocol(this.transport);

        this.client = new OperacoesGrafo.Client(protocol);

    }

    //Desliga a conexao do cliente no server
    private void desligarCliente() {

        this.transport.close();

    }

    //Menu de acesso de operacoes do usuario
    public void menuCliente() {

        int opcao = 20;

        while (opcao != 0) {

            System.out.println("|-------------MENU--OPERACOES-------------|");
            System.out.println("\n0 -  Sair");
            System.out.println("1 -  Criar Vertice");
            System.out.println("2 -  Criar Aresta");
            System.out.println("3 -  Remover Vertice");
            System.out.println("4 -  Remover Aresta");
            System.out.println("5 -  Modificar Vertice");
            System.out.println("6 -  Modificar Aresta");
            System.out.println("7 -  Ler Vertice");
            System.out.println("8 -  Ler Aresta");
            System.out.println("9 -  Listar Vertices de uma Aresta");
            System.out.println("10 - Listar Arestas de um Vertice");
            System.out.println("11 - Listar Vertices vizinhos de um Vertice");
            System.out.println("12 - Exibir Grafo");

            System.out.println("\nDigite a operacao desejada: ");
            opcao = s.nextInt();

            switch (opcao) {

                case 1:
                    criarVertice();
                    break;

                case 2:
                    criarAresta();
                    break;

                case 3:
                    removerVertice();
                    break;

                case 4:
                    removerAresta();
                    break;

                case 5:
                    modificarVertice();
                    break;

                case 6:
                    modificarAresta();
                    break;

                case 7:
                    lerVertice();
                    break;

                case 8:
                    lerAresta();
                    break;

                case 9:
                    listarVerticesAresta();
                    break;

                case 10:
                    listarArestasVertice();
                    break;

                case 11:
                    listarVerticesVizinhos();
                    break;

                case 12:
                    exibirGrafo();
                    break;

            }

        }

    }

    //Adiciona um vertice no Grafo
    private void criarVertice() {

        System.out.println("\nEntre com os dados do Vertice a ser adicionado no grafo\n");

        long nome = -1;

        try {

            System.out.print("Nome: ");
            nome = s.nextLong();

            bloqueiaVertice(nome);

            System.out.print("Cor: ");
            long cor = s.nextLong();
            System.out.print("Descricao: ");
            String descricao = s.next();
            System.out.print("Peso: ");
            double peso = s.nextDouble();
            System.out.println("\n" + client.criarVertice(nome, cor, descricao, peso) + "\n\n");

            //System.out.println("Vertice adicionado no grafo\n\n");
        } catch (TException ex) {
            //Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Excecao - Vertice Existente no Grafo\n\n");
        } catch (Exception ex) {
            System.out.println("Erro de entrada de dados\n\n");
            //liberaVertice(nome);
        }
        liberaVertice(nome);
    }

    //Adiciona uma aresta no Grafo
    private void criarAresta() {

        System.out.println("\nEntre com os dados da Aresta a ser adicionada no grafo\n");

        long nome = -1;

        try {

            System.out.print("Nome: ");
            nome = s.nextLong();

            bloqueiaAresta(nome);

            System.out.print("Vertice 1: ");
            long v1 = s.nextLong();
            System.out.print("Vertice 2: ");
            long v2 = s.nextLong();
            System.out.print("Peso: ");
            double peso = s.nextDouble();
            System.out.println("Direcionado: ");
            boolean direcionado = s.nextBoolean();
            System.out.print("Descricao: ");
            String descricao = s.next();

            System.out.println("\n" + client.criarAresta(nome, v1, v2, peso, direcionado, descricao) + "\n\n");
            //System.out.println("Aresta adicionada no grafo\n\n");

        } catch (ArestaExistente ex1) {
            System.out.println("Excecao - Aresta Existente no Grafo\n\n");
        } catch (VerticeNaoExistente ex2) {
            System.out.println("Excecao - Vertice Nao Existente no Grafo\n\n");
        } catch (Exception ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        liberaAresta(nome);

    }

    //Remove um vertice do Grafo
    private void removerVertice() {

        System.out.println("\nEntre com o nome do Vertice que deseja remover do grafo\n");
        System.out.print("Nome: ");
        long nome = s.nextLong();

        bloqueiaVertice(nome);

        try {
            System.out.println("\n" + client.removerVertice(nome) + "\n\n");
            //System.out.println("Vertice removido do grafo\n\n");
        } catch (VerticeNaoExistente ex) {
            System.out.println("Excecao - Vertice Nao Existente no Grafo\n\n");
        } catch (TException ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        liberaVertice(nome);

    }

    //Remove uma aresta do Grafo
    private void removerAresta() {

        System.out.println("\nEntre com o nome da Aresta que deseja remover do grafo\n");
        System.out.print("Nome: ");
        long nome = s.nextLong();

        bloqueiaAresta(nome);

        try {
            System.out.println("\n" + client.removerAresta(nome) + "\n\n");
            //System.out.println("Aresta removida do grafo\n\n");
        } catch (ArestaNaoExistente ex1) {
            System.out.println("Excecao - Aresta Nao Existente no Grafo\n\n");
        } catch (TException ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        liberaAresta(nome);

    }

    //Modifica um determinado vertice no Grafo
    private void modificarVertice() {

        System.out.print("\nEntre com o nome do Vertice a ser modificado: ");

        long nome = -1;

        try {

            nome = s.nextLong();

            bloqueiaVertice(nome);

            System.out.println("Nova cor: ");
            long cor = s.nextLong();
            System.out.println("Nova descricao: ");
            String descricao = s.next();
            System.out.println("Novo Peso: ");
            double peso = s.nextDouble();

            System.out.println("\n" + client.modificarVertice(nome, cor, descricao, peso) + "\n\n");
            //System.out.println("Vertice modificado no grafo\n\n");
        } catch (TException ex) {
            //Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Excecao - Vertice Nao Existente no Grafo\n\n");
        } catch (Exception ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        liberaVertice(nome);

    }

    //Modifica uma determinada aresta no Grafo
    private void modificarAresta() {

        System.out.print("\nEntre com o nome da Aresta a ser modificada: ");
        long nome = -1;

        try {

            nome = s.nextLong();
            bloqueiaAresta(nome);

            System.out.println("Novo Peso: ");
            double peso = s.nextDouble();
            System.out.println("Direcionado: ");
            boolean direcionado = s.nextBoolean();
            System.out.println("Nova descricao: ");
            String descricao = s.next();

            System.out.println("\n" + client.modificarAresta(nome, peso, direcionado, descricao) + "\n\n");
            //System.out.println("Aresta modificada no grafo\n\n");
        } catch (TException ex) {
            //Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Excecao - Aresta Nao Existente no Grafo\n\n");
        } catch (Exception ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        liberaAresta(nome);

    }

    //Exibe informacoes de um determinado vertice do Grafo
    private void lerVertice() {

        System.out.print("\nEntre com o nome do Vertice a ser lido: ");
        long nome = s.nextLong();

        bloqueiaVertice(nome);

        try {
            System.out.println(client.lerVertice(nome));
        } catch (TException ex) {
            //Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Excecao - Vertice Nao Existente no Grafo\n\n");
        }

        liberaVertice(nome);
    }

    //Exibe informacoes de uma determinada aresta do Grafo
    private void lerAresta() {

        System.out.print("\nEntre com o nome da Aresta a ser lida: ");
        long nome = s.nextLong();

        bloqueiaAresta(nome);

        try {
            System.out.println(client.lerAresta(nome));
        } catch (TException ex) {
            //Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Excecao - Aresta Nao Existente no Grafo\n\n");
        }

        liberaAresta(nome);

    }

    private void listarVerticesAresta() {

        System.out.print("\nEntre com o nome da Aresta para verificar seus Vertices: ");
        long nome = s.nextLong();

        bloqueiaAresta(nome);

        try {
            System.out.println(client.listarVerticesAresta(nome));
        } catch (TException ex) {
            //Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Excecao - Aresta Nao Existente no Grafo\n\n");
        }

        liberaAresta(nome);

    }

    private void listarArestasVertice() {

        System.out.print("\nEntre com o nome do Vertice para verificar suas Arestas: ");
        long nome = s.nextLong();

        bloqueiaVertice(nome);

        try {
            System.out.println(client.listarArestasVertice(nome));
        } catch (TException ex) {
            //Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Excecao - Vertice Nao Existente no Grafo\n\n");
        }

        liberaVertice(nome);

    }

    private void listarVerticesVizinhos() {

        System.out.print("\nEntre com o nome do Vertice para verificar seus vizinhos: ");
        long nome = s.nextLong();

        bloqueiaVertice(nome);

        try {
            System.out.println(client.listarVerticesVizinhos(nome));
        } catch (TException ex) {
            //Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Excecao - Vertice Nao Existente no Grafo\n\n");
        }

        liberaVertice(nome);

    }

    private void exibirGrafo() {

        try {
            System.out.println(client.lerGrafo());
        } catch (TException ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void bloqueiaVertice(long nome) {

        try {
            if (client.verificaControleVertice(nome) == false) {
                client.ligarControleVertice(nome);
            } else {
                System.out.println("\nOperacao Bloqueada: vertice " + nome + " sendo usado\n");
                while (true) {

                    if (client.verificaControleVertice(nome) == false) {
                        break;
                    }

                }
                System.out.println("\nOperacao Desbloqueada\n");
            }

        } catch (TException ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void liberaVertice(long nome) {

        try {
            client.desligarControleVertice(nome);
        } catch (TException ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void bloqueiaAresta(long nome) {

        try {
            if (client.verificaControleAresta(nome) == false) {
                client.ligarControleAresta(nome);
            } else {
                System.out.println("\nOperacao Bloqueada: aresta " + nome + " ou seus vertices em uso\n");
                while (true) {

                    if (client.verificaControleAresta(nome) == false) {
                        break;
                    }

                }
                System.out.println("\nOperacao Desbloqueada\n");
            }

        } catch (TException ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void liberaAresta(long nome) {

        try {
            client.desligarControleAresta(nome);
        } catch (TException ex) {
            Logger.getLogger(OperacoesGrafoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        OperacoesGrafoCliente cliente1 = new OperacoesGrafoCliente(12345);

        cliente1.menuCliente();

    }

}
