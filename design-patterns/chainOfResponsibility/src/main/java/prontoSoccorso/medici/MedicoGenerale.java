package prontoSoccorso.medici;

import prontoSoccorso.AbstractHandler;
import prontoSoccorso.Paziente;

public class MedicoGenerale extends AbstractHandler {

    @Override
    public boolean handle(Paziente paziente) {
        if(paziente.gravita() == 2) {
            System.out.println("Gestito da Medico Generale");
            return true;
        } else
            return super.handle(paziente);
    }

}
