package prontoSoccorso.medici;

import prontoSoccorso.AbstractHandler;
import prontoSoccorso.Paziente;

public class MedicoSpecialista extends AbstractHandler {

    @Override
    public boolean handle(Paziente paziente) {
        if(paziente.gravita() == 3) {
            System.out.println("Gestito da Medico Specialista");
            return true;
        } else
            return super.handle(paziente);
    }

}
