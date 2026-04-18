package prontoSoccorso.medici;

import prontoSoccorso.AbstractHandler;
import prontoSoccorso.Paziente;

public class Infermiere extends AbstractHandler {

    @Override
    public boolean handle(Paziente paziente) {
        if(paziente.gravita() <= 1) {
            System.out.println("Gestito da Infermiere");
            return true;
        } else
            return super.handle(paziente);
    }

}
