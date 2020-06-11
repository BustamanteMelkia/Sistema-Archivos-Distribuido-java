package SistemaDistribuido.vista;

import SistemaDistribuido.Controlador;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelLogin extends JPanel{
    /* COMPONENTS */
    //Labels
    private JLabel chatNameLabel;
    private JLabel usernameLabel;
    private JLabel serverHostLabel;
    
    //TextFields
    private JTextField usernameTextField;
    private JTextField serverHostTextField;
    
    //Buttons
    private JButton loginButton;

    public PanelLogin(){
        super();
        this.initComponents();
        iniciarEventos();
    }
    
    private void initComponents(){
        //Panel Properties
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEtchedBorder());
        
        //This constraint variable will be used for all the components
        GridBagConstraints constraints;
        
        //ChatNameLabel
        constraints = new GridBagConstraints();
        this.chatNameLabel = new JLabel("INICIO DE SESIÓN", SwingConstants.CENTER);
        this.chatNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        this.chatNameLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = 1;
        this.add(this.chatNameLabel, constraints);
        
        //usernameLabel
        constraints = new GridBagConstraints();
        this.usernameLabel = new JLabel("nombre de usuario:", SwingConstants.RIGHT);
        this.usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        this.usernameLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(this.usernameLabel, constraints);
        
        //serverHostLabel
        constraints = new GridBagConstraints();
        this.serverHostLabel = new JLabel("IP servidor:", SwingConstants.RIGHT);
        this.serverHostLabel.setFont(new Font("Arial", Font.BOLD, 15));
        this.serverHostLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(this.serverHostLabel, constraints);
        
        //usernameTextField
        constraints = new GridBagConstraints();
        this.usernameTextField = new JTextField();
        this.usernameTextField.setFont(new Font("Arial", Font.BOLD, 15));
        this.usernameTextField.setForeground(Color.BLACK);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.usernameTextField, constraints);
        
        //serverHostTextField
        constraints = new GridBagConstraints();
        this.serverHostTextField = new JTextField();
        this.serverHostTextField.setFont(new Font("Arial", Font.BOLD, 15));
        this.serverHostTextField.setForeground(Color.BLACK);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.serverHostTextField, constraints);
        
        //loginButton
        constraints = new GridBagConstraints();
        this.loginButton = new JButton("Login");
        //this.loginButton.setEnabled(false);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(this.loginButton, constraints);
    }

    private void iniciarEventos(){
        this.usernameTextField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent evt){
                textFieldKeyReleased();
            }
            
            @Override
            public void keyTyped(KeyEvent evt){
                usernameTextFieldKeyTyped(evt);
            }
        });
        
        this.serverHostTextField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent evt){
                textFieldKeyReleased();
            }
            
            @Override
            public void keyTyped(KeyEvent evt){
                serverHostTextFieldKeyTyped(evt);
            }
        });
    }
    
    public void eraseUsername(){
        this.usernameTextField.setText("");
    }

    public void eraseAddress() {
        this.serverHostTextField.setText("");
    }
    
                        /*  E   V   E   N   T   S   */
    private void textFieldKeyReleased() {
        if(this.usernameTextField.getText().isEmpty() || this.serverHostTextField.getText().isEmpty())
            this.loginButton.setEnabled(false);
        else
            this.loginButton.setEnabled(true);
        
    }
    
    private void usernameTextFieldKeyTyped(KeyEvent evt){
        if(!Character.isLetter(evt.getKeyChar()) && !(Character.isDigit(evt.getKeyChar())))
            evt.consume();
    }
    
    private void serverHostTextFieldKeyTyped(KeyEvent evt){
        if(!(Character.isDigit(evt.getKeyChar())) && (evt.getKeyChar() != '.'))
            evt.consume();
    }
    public void limpiarCampos(){
        this.usernameTextField.setText("");
        this.serverHostTextField.setText("");
    }
    public JButton getButtonLogin(){
        return this.loginButton;
    }
    
    public void setControlador(Controlador controlador){
        this.loginButton.addActionListener(controlador);
    }

    public String getNombre() {
        return usernameTextField.getText();
    }

    public String getIp() {
        return serverHostTextField.getText();
    }
}
