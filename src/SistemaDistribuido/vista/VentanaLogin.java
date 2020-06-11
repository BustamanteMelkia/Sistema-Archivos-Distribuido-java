package SistemaDistribuido.vista;

import SistemaDistribuido.Controlador;
import SistemaDistribuido.vista.PanelLogin;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentanaLogin extends JFrame {

    //Static
    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;
    
    //Paneles
    private PanelLogin panelLogin;
    
    public VentanaLogin(){
        super();
        this.initComponents();
    }
    
    private void initComponents(){
        this.setSize(new Dimension(WIDTH,HEIGHT));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.panelLogin = new PanelLogin();
        this.getContentPane().add(this.panelLogin);
    }
    
    
    public PanelLogin getLoginPanel(){
        return this.panelLogin;
    }
    
    public JButton getButtonLogin(){
        return this.panelLogin.getButtonLogin();
    }
    public void setControlador(Controlador c){
        this.panelLogin.setControlador(c);
    }
    public void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
}