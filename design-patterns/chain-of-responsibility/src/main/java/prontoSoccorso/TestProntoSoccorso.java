package prontoSoccorso;

import prontoSoccorso.medici.Infermiere;
import prontoSoccorso.medici.MedicoChirurgo;
import prontoSoccorso.medici.MedicoGenerale;
import prontoSoccorso.medici.MedicoSpecialista;

public class TestProntoSoccorso {
    public static void main(String[] args) {
        Paziente paziente = new Paziente("Mario", 0);
        Handler handler = new Infermiere();
        handler.setNext(new MedicoGenerale())
                .setNext(new MedicoSpecialista())
                .setNext(new MedicoChirurgo());
        handler.handle(paziente);
    }
}
