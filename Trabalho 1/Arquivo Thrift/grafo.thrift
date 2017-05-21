namespace java operacoesgrafo

##########################################Estruturas##########################################

#Grafo G = (V,A) -> V = Conjunto de vértices; A = Conjunto de arestas.
struct Grafo {

    1:list<Vertice> V
    2:list<Aresta>  A

}

#Vértices têm um nome(inteiro positivo), uma cor(inteiro positivo), uma descrição(string) e um peso(real).
struct Vertice {

    1:i64 nome
    2:i64 cor
    3:string descricao
    4:double peso

}

#Arestas têm nomes de dois vértices(dois inteiros positivos), um peso(real), uma flag indicando 
#se é direcionado(do primeiro para o segundo) ou se é bi-direcional, e uma descrição(string).
struct Aresta {

    1:i64 nome
    2:i64 vertice1
    3:i64 vertice2
    4:double peso
    5:bool direcionado
    6:string descricao

}

##############################################################################################


###########################################Exceções###########################################

#Exceção VerticeExistente é disparada ao tentar criar um vértice com o mesmo nome de outro vértice já existente no grafo.
exception VerticeExistente 
{
}

#Exceção VerticeNaoExistente é disparada ao tentar remover um vértice não existente no grafo.
#Também é disparada ao tentar criar uma aresta que toca um vértice não existente, além da tentativa
#de ou modificar, ou ler, ou exibir informações de um vértice que não existe no grafo.
exception VerticeNaoExistente 
{
}

#Exceção ArestaExistente é disparada ao tentar criar uma aresta ligando dois vértices que já
#possuem uma aresta de ligação.
exception ArestaExistente
{
}

#Exceção ArestaNaoExistente é disparada ao tentar remover uma aresta ligando dois vértices que não
#possuem uma aresta de ligação. Também é disparada na tentativa de ou modificar, ou ler, ou exibir
#informações de uma aresta que não existe no grafo.
exception ArestaNaoExistente
{
}

##############################################################################################


###########################################Serviços###########################################

##CRIAÇÃO##

#criarGrafo -> Inicializar um grafo de forma a recebe um conjunto de vértices e arestas.

#criarVertice -> Um vértice só pode ser adicionado se não existir outro com o mesmo nome.

#criarAresta -> Uma aresta só pode ser adicionada se não existir outra tocando os mesmos dois
#vértices. Além disso, ambos os vértices tocados pela aresta devem existir no grafo.


##REMOÇÃO##

#removerVertice -> Um vértice só pode ser removido se existir no grafo. Além disso, é necessário
#remover todas as arestas que tocam o vértice removido.

#removerAresta -> Uma aresta só pode ser removida se existir no grafo.


##MODIFICAÇÃO##

#modificarVertice -> Modificação realizada a partir do nome/identificador do vértice. Além disso, o
#nome do vértice não pode ser alterado.

#modificaçãoAresta -> Modificação realizada a partir do nome/identificador da aresta. Além disso,
#uma aresta não pode ter seus vértices mudados.


##LEITURA##

#lerGrafo -> Exibe o conjunto de vértices e aresta do grafo.

#lerVertice -> Exibe as informações de um vértice a partir de seu nome/identificador.

#lerAresta -> Exibe as informações de uma aresta a partir de seu nome/identificador.


##LISTAR-VÉRTICES-A-PARTIR-DE-UMA-ARESTA##

#listarVerticesAresta -> Exibe os nomes/identificadores dos vértices de uma aresta.


##LISTAR-ARESTAS-A-PARTIR-DE-UM-VÉRTICE##

#listarArestasVertice -> Exibe os nomes/identificadores das arestas de um vértice.


##LISTAR-VÉRTICES-VIZINHOS-DE-UM-VÉRTICE##

#listarVerticesVizinhos -> Exibe os nomes/identificadores dos vértices vizinhos de um vértice.


service OperacoesGrafo {
    
    ##CRIAÇÃO##
    
    Grafo criarGrafo(),
    
    string criarVertice(1:i64 nome, 2:i64 cor, 3:string descricao, 4:double peso)
	throws	(1:VerticeExistente VE),
    
    string criarAresta(1:i64 nome, 2:i64 vertice1, 3:i64 vertice2, 4:double peso, 5:bool direcionado, 6:string descricao) 
	throws (1:ArestaExistente AE, 2:VerticeNaoExistente VNE),
    
    ##REMOÇÃO##
    string removerVertice(1:i64 nome) throws (1:VerticeNaoExistente VNE),
    string removerAresta(1:i64 nome) throws (1:ArestaNaoExistente ANE),
    
    ##MODIFICAÇÃO##
    string modificarVertice(1:i64 nome, 2:i64 cor, 3:string descricao, 4:double peso) 
	throws 	(1:VerticeNaoExistente VNE),
    string modificarAresta(1:i64 nome, 2:double peso, 3:bool direcionado, 4:string descricao) 		throws (1:ArestaNaoExistente ANE),

    ##LEITURA##
    string lerGrafo(),
    string lerVertice(1:i64 nome) throws (1:VerticeNaoExistente VNE),
    string lerAresta(1:i64 nome) throws (1:ArestaNaoExistente ANE),

    ##LISTAR-VÉRTICES-A-PARTIR-DE-UMA-Aresta##
    string listarVerticesAresta(1:i64 nome) throws (1:ArestaNaoExistente ANE),

    ##LISTAR-ArestaS-A-PARTIR-DE-UM-VÉRTICE##
    string listarArestasVertice(1:i64 nome) throws (1:VerticeNaoExistente VNE),

    ##LISTAR-VÉRTICES-VIZINHOS-DE-UM-VÉRTICE##
    string listarVerticesVizinhos(1:i64 nome) throws (1:VerticeNaoExistente VNE),

    ##GETTERS - ARESTA E VERTICE##
    Aresta getAresta(1:i64 nome),
    Vertice getVertice(1:i64 nome),

    ##CONTROLE DE CONCORRÊNCIA E ACESSO##
    void ligarControleVertice(1:i64 nome),
    void desligarControleVertice(1:i64 nome),
    bool verificaControleVertice(1:i64 nome),

    void ligarControleAresta(1:i64 nome),
    void desligarControleAresta(1:i64 nome),
    bool verificaControleAresta(1:i64 nome),
    ##
}


##############################################################################################


	
