package basicMediator;

import javax.swing.*;
import java.text.MessageFormat;

// Classe concreta del Mediator
class FormMediator implements MediatorIF {

    private JTextField nome;
    private JTextField cognome;
    private JTextField nickname;
    private JButton save;

    public void setNome(JTextField nome) {
        this.nome = nome;
    }
    public void setCognome(JTextField cognome) {
        this.cognome = cognome;
    }
    public void setNickname(JTextField nickname) {
        this.nickname = nickname;
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
            showMessage();
        }
    }

    public void showMessage(){
        String message = MessageFormat.format("Nome: {0} \nCognome: {1} \nNickname: {2}",
                nome.getText().strip(),
                cognome.getText().strip(),
                nickname.getText().strip());
        JOptionPane.showMessageDialog(null, message);
    }

    private boolean compilati(){
        return !nome.getText().isBlank() && !cognome.getText().isBlank();
    }
}
