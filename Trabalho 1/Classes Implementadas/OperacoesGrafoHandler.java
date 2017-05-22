package operacoesgrafo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;

public class OperacoesGrafoHandler implements OperacoesGrafo.Iface {

    Grafo grafo; //GRAFO ÚNICO DO SISTEMA
    
    List<Long> controleVertice = new ArrayList<>(); //LISTA DE VERTICES BLOQUEADOS PARA CONTROLE DE ACESSO
    List<Long> controleAresta = new ArrayList<>(); //LISTA DE ARESTAS BLOQUEADAS PARA CONTROLE DE ACESSO
    
    ////////////////////////////////////////////////////////////////////////////
    
    //FUNÇÕES DESIGNADAS NO ARQUIVO THRIFT
    
    @Override
    public Grafo criarGrafo() throws TException {

        grafo = new Grafo();

        atualizaGrafo(grafo);

        return grafo;

    }

    @Override
    public String criarVertice(long nome, long cor, String descricao, double peso) throws VerticeExistente, TException {
        
        Vertice v = new Vertice();
        Vertice verifica = encontraVertice(nome);

        String res = "";

        if (verifica == null) {
            v.setNome(nome);
            v.setCor(cor);
            v.setDescricao(descricao);
            v.setPeso(peso);

            grafo.addToV(v);

            try {
                gravaArquivo("vertices", grafo);
            } catch (IOException ex) {
                Logger.getLogger(OperacoesGrafoHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            res = res.concat("Vertice " + v.getNome() + " adicionado no grafo");

        } else {
            throw new VerticeExistente();
        }

        return res;

    }

    @Override
    public String criarAresta(long nome, long vertice1, long vertice2, double peso, boolean direcionado, String descricao) throws ArestaExistente, VerticeNaoExistente, TException {

        Aresta a = new Aresta();
        Aresta verifica = encontraAresta(nome, vertice1, vertice2);
        Vertice v1 = encontraVertice(vertice1);
        Vertice v2 = encontraVertice(vertice2);

        String res = "";

        if (v1 == null || v2 == null) {
            throw new VerticeNaoExistente();
        }

        if (verifica == null) {

            a.setNome(nome);
            a.setVertice1(vertice1);
            a.setVertice2(vertice2);
            a.setPeso(peso);
            a.setDirecionado(direcionado);
            a.setDescricao(descricao);

            grafo.addToA(a);

            try {
                gravaArquivo("arestas", grafo);
            } catch (IOException ex) {
                Logger.getLogger(OperacoesGrafoHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            res = res.concat("Aresta " + a.getNome() + " adicionada no grafo");

        } else {
            throw new ArestaExistente();
        }

        return res;

    }

    //REMOVE UM DETERMINADO VÉRTICE
    @Override
    public String removerVertice(long nome) throws VerticeNaoExistente, TException {

        Vertice v = encontraVertice(nome);
        List<Aresta> arestas = new ArrayList<>();
        String res = "";

        if (v != null) {
            
            //REMOVE O VÉRTICE
            grafo.getV().remove(v);
            
            res = res.concat("Vertice " + nome + " removido do grafo\n");
            
            //VERIFICA AS ARESTAS DO VÉRTICE
            if(!grafo.getA().isEmpty()) {
                
                for(Aresta a: grafo.getA()) {
                    
                    if(a.getVertice1() == nome || a.getVertice2() == nome) {
                        
                        arestas.add(a);
                        
                    }
                    
                }
                                
            }
            
            //REMOVE AS ARESTAS DO VÉRTICE
            for(Aresta aresta: arestas) {
                grafo.getA().remove(aresta);
                res = res.concat("Aresta " + aresta.getNome() + " removida do grafo\n");
            }
            
            try {
                gravaArquivo("vertices", grafo);
                gravaArquivo("arestas", grafo);
            } catch (IOException ex) {
                Logger.getLogger(OperacoesGrafoHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else {
            
            throw new VerticeNaoExistente();
            
        }

        return res;

    }

    //REMOVE UMA DETERMINADA ARESTA
    @Override
    public String removerAresta(long nome) throws ArestaNaoExistente, TException {

        Aresta a = encontraAresta(nome);
        String res = "";

        if (a != null) {

            grafo.getA().remove(a);

            res = res.concat("Aresta " + a.getNome() + " removida do grafo\n");
            
            try {
                gravaArquivo("arestas", grafo);
            } catch (IOException ex) {
                Logger.getLogger(OperacoesGrafoHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            res = res.concat("Excecao disparada: Aresta nao existe");

            throw new ArestaNaoExistente();
        }

        return res;

    }

    //MODIFICA UM DETERMINADO VÉRTICE
    @Override
    public String modificarVertice(long nome, long cor, String descricao, double peso) throws VerticeNaoExistente, TException {

        Vertice v = encontraVertice(nome);

        if (v == null) {
            throw new VerticeNaoExistente();
        }
        
        v.setCor(cor);
        
        v.setDescricao(descricao);
        
        v.setPeso(peso);
        
        try {
            gravaArquivo("vertices", grafo);
        } catch (IOException ex) {
            Logger.getLogger(OperacoesGrafoHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Vertice " + v.getNome() + " modificado";

    }

    //MODIFICA UMA DETERMINADA ARESTA
    @Override
    public String modificarAresta(long nome, double peso, boolean direcionado, String descricao) throws ArestaNaoExistente, TException {
        
        Aresta a = encontraAresta(nome);

        if (a == null) {
            throw new ArestaNaoExistente();
        }
        
        if(peso != 0) {
            a.setPeso(peso);
        }
        
        if(direcionado != a.isDirecionado()) {
            a.setDirecionado(direcionado);
        }
        
        if(descricao != null) {
            a.setDescricao(descricao);
        }
        
        try {
            gravaArquivo("arestas", grafo);
        } catch (IOException ex) {
            Logger.getLogger(OperacoesGrafoHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Aresta " + a.getNome() + " modificada";
        
    }

    //RETORNA AS INFORMAÇÕES DO GRAFO
    @Override
    public String lerGrafo() throws TException {  //EXIBE AS INFORMAÇÕES DO GRAFO PARA OS CLIENTES

        String resGrafo = "";

        if (grafo != null) {

            if (!grafo.getV().isEmpty()) {

                resGrafo = resGrafo.concat("\n\n-------VERTICES------\n\n");

                for (Vertice v : grafo.getV()) {

                    resGrafo = resGrafo.concat("Vertice: " + v.getNome() + " ");
                    resGrafo = resGrafo.concat("Cor: " + v.getCor() + " ");
                    resGrafo = resGrafo.concat("Descricao: " + v.getDescricao() + " ");
                    resGrafo = resGrafo.concat("Peso: " + v.getPeso() + "\n");

                }

            }

            if (!grafo.getA().isEmpty()) {

                resGrafo = resGrafo.concat("\n-------ARESTAS-------\n\n");

                for (Aresta a : grafo.getA()) {

                    resGrafo = resGrafo.concat("Aresta: " + a.getNome() + " ");
                    resGrafo = resGrafo.concat("Vertice 1: " + a.getVertice1() + " ");
                    resGrafo = resGrafo.concat("Vertice 2: " + a.getVertice2() + " ");
                    resGrafo = resGrafo.concat("Descricao: " + a.getDescricao() + " ");
                    resGrafo = resGrafo.concat("Peso: " + a.getPeso() + "\n");

                }

            }

        }

        return resGrafo;

    }

    //RETORNA AS INFORMAÇÕES DE UM VÉRTICE
    @Override
    public String lerVertice(long nome) throws VerticeNaoExistente, TException {

        Vertice v = encontraVertice(nome);

        String res = "";

        if (v == null) {
            throw new VerticeNaoExistente();
        }

        res = res.concat("\nVERTICE ENCONTRADO\n\n");
        res = res.concat("Vertice: " + String.valueOf(v.getNome()));
        res = res.concat(" Cor: " + String.valueOf(v.getCor()));
        res = res.concat(" Descricao: " + v.getDescricao());
        res = res.concat(" Peso: " + String.valueOf(v.getPeso()) + "\n");

        return res;

    }

    //RETORNA AS INFORMAÇÕES DE UMA ARESTA
    @Override
    public String lerAresta(long nome) throws ArestaNaoExistente, TException {

        Aresta a = encontraAresta(nome);

        String res = "";

        if (a == null) {
            throw new ArestaNaoExistente();
        }

        res = res.concat("\nARESTA ENCONTRADA\n\n");
        res = res.concat("Aresta: " + a.getNome());
        res = res.concat(" Vertice 1: " + String.valueOf(a.getVertice1()));
        res = res.concat(" Vertice 2: " + String.valueOf(a.getVertice2()));
        res = res.concat(" Peso: " + String.valueOf(a.getPeso()));
        res = res.concat(" Direcionado: " + String.valueOf(a.isDirecionado()));
        res = res.concat(" Descricao: " + a.getDescricao() + "\n");

        return res;

    }

    //LISTA VÉRTICES DE UM ARESTA
    @Override
    public String listarVerticesAresta(long nome) throws ArestaNaoExistente, TException {

        Aresta a = encontraAresta(nome);

        String lista = "";
        
        if (a == null) {
            throw new ArestaNaoExistente();
        } else {
            
            lista = lista.concat("\nVertices a partir da aresta " + a.getNome() + "\n\n");

            lista = lista.concat("Vertice 1: " + String.valueOf(a.getVertice1()) + "\n");
            lista = lista.concat("Vertice 2: " + String.valueOf(a.getVertice2()) + "\n");
        
        }

        return lista;

    }

    //LISTA ARESTAS DE UM VÉRTICE
    @Override
    public String listarArestasVertice(long nome) throws VerticeNaoExistente, TException {

        Vertice v = encontraVertice(nome);

        if (v == null) {
            throw new VerticeNaoExistente();
        }

        String lista = "\nArestas a partir do vertice " + String.valueOf(v.getNome()) + "\n\n";

        for (Aresta a : grafo.getA()) {

            if (a.getVertice1() == v.getNome() || a.getVertice2() == v.getNome()) {
                lista = lista.concat("Aresta: " + a.getNome() + "\n");
            }

        }

        lista = lista.concat("\n");

        return lista;

    }

    //LISTA VÉRTICES VIZINHOS DE UM VÉRTICE
    @Override
    public String listarVerticesVizinhos(long nome) throws VerticeNaoExistente, TException {

        Vertice v = encontraVertice(nome);

        if (v == null) {
            throw new VerticeNaoExistente();
        }

        String lista = "\nVertices vizinhos do vertice " + String.valueOf(v.getNome()) + "\n\n";

        for (Aresta a : grafo.getA()) {

            if (a.getVertice1() == v.getNome()) {
                lista = lista.concat("Vertice: " + String.valueOf(a.getVertice2()) + "\n");
            }

            if (a.getVertice2() == v.getNome()) {
                lista = lista.concat("Vertice: " + String.valueOf(a.getVertice1()) + "\n");
            }

        }

        lista = lista.concat("\n");

        return lista;

    }
    
    @Override
    public Vertice getVertice(long nome) {
        return encontraVertice(nome);
    }
    
    @Override
    public Aresta getAresta(long nome) {
        return encontraAresta(nome);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    
    //Atualiza a lista de vertices e arestas quando o grafo eh inicializado
    private void atualizaGrafo(Grafo grafo) {

        try {
            grafo.setV(recuperaVertices());
            grafo.setA(recuperaArestas());
        } catch (IOException ex) {
            Logger.getLogger(OperacoesGrafoHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    
    //CONTROLE DE CONCORRÊNCIA
    
    @Override
    public void ligarControleVertice(long nome) {
        controleVertice.add(nome);
    } 
    
    @Override
    public void desligarControleVertice(long nome) {
        controleVertice.remove(nome);
    }
    
    @Override
    public boolean verificaControleVertice(long nome) {
        
        if(controleVertice.contains(nome))
            return true;
        
        return false;
    }
    
    @Override
    public void ligarControleAresta(long nome) {
        controleAresta.add(nome);
    } 
    
    @Override
    public void desligarControleAresta(long nome) {
        controleAresta.remove(nome);
    }
    
    @Override
    public boolean verificaControleAresta(long nome) {
        
        if(controleAresta.contains(nome))
            return true;
        else {
            
            Aresta a = encontraAresta(nome);
            
            if(a != null) {
            
                if(verificaControleVertice(a.getVertice1()) == true)
                    return true;
            
                if(verificaControleVertice(a.getVertice2()) == true)
                    return true;
            }
            
        }
        
        return false;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    

    ////////////////////////////////////////////////////////////////////////////
    
    //FUNÇÕES PARA BUSCAR E RETORNAR VERTICES E ARESTAS PELA SUA IDENTIFICAÇÃO (NOME)
    
    
    private Vertice encontraVertice(long nome) {

        Vertice procurado = null;

        for (Vertice v : grafo.getV()) {

            if (v.getNome() == nome) {
                procurado = v;
                break;
            }

        }

        return procurado;

    }
    
    private Aresta encontraAresta(long nome) {

        Aresta procurada = null;

        for (Aresta a : grafo.getA()) {

            if (a.getNome() == nome) {
                procurada = a;
                break;
            }

        }

        return procurada;

    }

    private Aresta encontraAresta(long nome, long v1, long v2) {

        Aresta procurada = null;

        for (Aresta a : grafo.getA()) {

            if (a.getNome() == nome || (a.getVertice1() == v1 && a.getVertice2() == v2) ) {
                procurada = a;
                break;
            }

        }

        return procurada;

    }
    ////////////////////////////////////////////////////////////////////////////
    


    //FUNÇÕES DE LEITURA E ESCRITA EM ARQUIVO
    
    
    // Leitura de arquivo (vertices)
    private List<Vertice> recuperaVertices() throws FileNotFoundException, IOException {

        FileReader arq = new FileReader("/home/matheusprandini/Documentos/SD"
                + "/Trabalho/vertices.txt");

        BufferedReader lerArq = new BufferedReader(arq);

        List<Vertice> vertices = new ArrayList<>();

        String linha = lerArq.readLine(); //Primeira linha

        if (linha != null) { //Arquivo tem conteúdo

            while (linha != null) { //Adiciona os vertices na lista de vertices

                Vertice v = new Vertice();
                String split[] = linha.split(" ");

                //Transformação dos dados
                v.setNome(Long.parseLong(split[0]));
                v.setCor(Long.parseLong(split[1]));
                v.setDescricao(split[2]);
                v.setPeso(Double.parseDouble(split[3]));

                vertices.add(v);

                linha = lerArq.readLine();

            }

        }

        arq.close();

        return vertices;

    }

    // Leitura de arquivo (arestas)
    private List<Aresta> recuperaArestas() throws FileNotFoundException, IOException {

        FileReader arq = new FileReader("/home/matheusprandini/Documentos/SD"
                + "/Trabalho/arestas.txt");

        BufferedReader lerArq = new BufferedReader(arq);

        List<Aresta> arestas = new ArrayList<>();

        String linha = lerArq.readLine(); //Primeira linha

        if (linha != null) { //Arquivo tem conteúdo

            while (linha != null) { //Adiciona os vertices na lista de arestas

                Aresta a = new Aresta();
                String split[] = linha.split(" ");

                //Transformação dos dados
                a.setNome(Long.parseLong(split[0]));
                a.setVertice1(Long.parseLong(split[1]));
                a.setVertice2(Long.parseLong(split[2]));
                a.setPeso(Double.parseDouble(split[3]));
                a.setDirecionado(Boolean.parseBoolean(split[4]));
                a.setDescricao(split[5]);

                arestas.add(a);

                linha = lerArq.readLine();

            }

        }

        arq.close();

        return arestas;

    }

    //Escreve no arquivo desejado (vertices, arestas)
    private void gravaArquivo(String arquivo, Grafo g) throws IOException {

        FileWriter arq = new FileWriter("/home/matheusprandini/Documentos/SD"
                + "/Trabalho/" + arquivo + ".txt");

        PrintWriter gravarArq = new PrintWriter(arq);

        String linha = "";

        if (!g.getA().isEmpty() && arquivo.equals("arestas")) {

            for (Aresta A : g.getA()) {

                linha = linha.concat(A.getNome() + " ");
                linha = linha.concat(String.valueOf(A.getVertice1()) + " ");
                linha = linha.concat(String.valueOf(A.getVertice2()) + " ");
                linha = linha.concat(String.valueOf(A.getPeso()) + " ");
                linha = linha.concat(String.valueOf(A.isDirecionado()) + " ");
                linha = linha.concat(A.getDescricao() + "\n");

            }

        }

        if (!g.getV().isEmpty() && arquivo.equals("vertices")) {

            for (Vertice V : g.getV()) {

                linha = linha.concat(String.valueOf(V.getNome()) + " ");
                linha = linha.concat(String.valueOf(V.getCor()) + " ");
                linha = linha.concat(V.getDescricao() + " ");
                linha = linha.concat(String.valueOf(V.getPeso()) + "\n");

            }

        }

        gravarArq.printf(linha);

        arq.close();

    }
    ////////////////////////////////////////////////////////////////////////////

    
}
