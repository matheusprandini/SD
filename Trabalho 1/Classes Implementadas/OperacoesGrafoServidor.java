package operacoesgrafo;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class OperacoesGrafoServidor {

    public static void main(String[] args) {

        try {

            TServerTransport serverTransport = new TServerSocket(12345);

            OperacoesGrafoHandler handler = new OperacoesGrafoHandler();

            OperacoesGrafo.Processor processor = new OperacoesGrafo.Processor(handler);

            //Server que aceita somente um cliente
            //TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            
            //Server que aceita multiplos clientes
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            
            //Inicializa o grafo
            Grafo grafoServidor = handler.criarGrafo();
            
            if(grafoServidor != null)
                System.out.println("Grafo criado com sucesso");
            
            server.serve();

        } catch (Exception x) {

            x.printStackTrace();

        }

    }

}
