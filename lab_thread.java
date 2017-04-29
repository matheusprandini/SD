package lab_thread;

import java.util.*;

public class lab_thread extends Thread {

    //lista contendo as threads
    public static List<lab_thread> lista = new ArrayList<>();

    //atributo "s" que armazena a string recebida pela thread
    public String s;

    //construtor que recebe a string
    public lab_thread(String pstr) {
        this.s = pstr;
    }

    //método que encontra a primeira letra minúscula da string "s", transforma em maiúscula e atualiza a lista de threads
    public void run() {
        
        //percorre a string "s" para transformar a primeira letra minúscula em maiúscula
        for (int i = 0; i <= s.length(); i++) {
            
            if (Character.isLowerCase(s.charAt(i))) {
                
                s = s.substring(0, i) + Character.toUpperCase(s.charAt(i)) + s.substring(i + 1);
                break;
                
            }
            
        }

        //ainda restam letras maiúsculas
        if (!s.toUpperCase().equals(s)) {
            
            System.out.println(lista.get(0).s); //mostra a string transformada
            lista.add(lista.get(0)); //adiciona a thread atual no fim da lista
            lista.remove(0); //remove a thread atual do início da lista
            lista.get(0).s = s; //passa a string atual para a próxima thread da lista
            lista.get(0).run(); //chama a próxima thread
            
        }
        else { //todas as letras estão maiúsculas
            
            System.out.println(lista.get(0).s + " - FIM =D");
            
        }

    }

    public static void main(String args[]) {

        //thread 1
        lab_thread t = new lab_thread("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzab");
        
        lista.add(t);
        
        //cria as 29 threads restantes
        for (int i = 0; i < 29; i++) {
            t = new lab_thread("");
            lista.add(t);
        }

        //inicia thread 1
        lista.get(0).run();

    }

}
