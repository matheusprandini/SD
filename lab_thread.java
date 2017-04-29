package lab_thread;

import java.util.*;

public class lab_thread extends Thread {

    public static List<lab_thread> lista = new ArrayList<>();

    public String s;

    public lab_thread(String pstr) {
        this.s = pstr;
    }

    public void run() {

        for (int i = 0; i <= s.length(); i++) {
            
            if (Character.isLowerCase(s.charAt(i))) {
                
                s = s.substring(0, i) + Character.toUpperCase(s.charAt(i)) + s.substring(i + 1);
                break;
                
            }
            
        }

        if (!s.toUpperCase().equals(s)) {
            
            System.out.println(lista.get(0).s);
            lista.add(lista.get(0));
            lista.remove(0);
            lista.get(0).s = s;
            lista.get(0).run();
            
        }
        else {
            
            System.out.println(lista.get(0).s);
            
        }

    }

    public static void main(String args[]) {

        lab_thread t = new lab_thread("Hello from a thread with lowercase to uppercase");
        lista.add(t);
        for (int i = 0; i < 29; i++) {
            t = new lab_thread("");
            lista.add(t);
            //System.out.println(lista.size());
        }

        lista.get(0).run();

    }

}
