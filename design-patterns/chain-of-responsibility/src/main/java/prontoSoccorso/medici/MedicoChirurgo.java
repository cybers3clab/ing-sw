package prontoSoccorso.medici;

import prontoSoccorso.AbstractHandler;
import prontoSoccorso.Paziente;

public class MedicoChirurgo extends AbstractHandler {

    @Override
    public boolean handle(Paziente paziente) {
        if(paziente.gravita() >= 4) {
            System.out.println("Gestito da Medico Chirurgo");
            return true;
        } else
            return super.handle(paziente);
    }

}
