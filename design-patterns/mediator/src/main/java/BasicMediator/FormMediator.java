package BasicMediator;

import javax.swing.*;

// Classe concreta del Mediator
class FormMediator implements MediatorIF {

    private JTextField nome;
    private JTextField cognome;
    private JButton save;

    public void setNome(JTextField nome) {
        this.nome = nome;
    }

    public void setCognome(JTextField cognome) {
        this.cognome = cognome;
    }

    public void setSave(JButton save) {
        this.save = save;
    }

    @Override
    public void widgetCambiato(JComponent widget) {
        if (widget == nome || widget == cognome){
            save.setEnabled(compilati());

        }
        else if (widget == save){
            JOptionPane.showMessageDialog(null, nome.getText().strip() + " " + cognome.getText().strip());
        }
    }

    private boolean compilati(){
        return !nome.getText().strip().isEmpty() && !cognome.getText().strip().isEmpty();
    }
}
