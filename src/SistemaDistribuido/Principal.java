package SistemaDistribuido;

import SistemaDistribuido.vista.VentanaLogin;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {
    public static void main(String [] args){
        try {
            VentanaLogin ventanaLogin = new VentanaLogin();
            Controlador controlador = new Controlador(ventanaLogin);
            ventanaLogin.setControlador(controlador);
            ventanaLogin.setVisible(true);
            
        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
