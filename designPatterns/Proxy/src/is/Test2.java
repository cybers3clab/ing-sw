package is;

import is.lista.Lista;
import is.lista.ListeUtil;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Test2 {
    public static void main(String[] args) {
        Lista<String> ls = ListeUtil.creaLista(1_000, true);
        final int N = 100;
        Lista<String>[] array = new Lista[N];

        for (int i = 0; i < array.length; i++) {
            array[i] = ls.copia();
        }

        Random random = new Random();
        for (int i = 0; i < N; i++) {
            var exit = false;

            do {
                int k = random.nextInt(array.length);
                if (array[k] != null) {
                    array[k] = null;
                    System.gc();
                    if (random.nextFloat() < 0.7) {
                        array[k] = ls.copia();
                        array[k].aggiungi("Ciao");
                    }
                    exit = true;
                }
            } while (!exit);

            //System.out.printf("x[%d] is going to die%n",k);

            try {
                sleep(1);
                System.out.print(".");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ls = null;
        Arrays.fill(array, null);
        System.gc();
        try {
            sleep(1000);
            System.out.print(".");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("fine!");
    }
}
