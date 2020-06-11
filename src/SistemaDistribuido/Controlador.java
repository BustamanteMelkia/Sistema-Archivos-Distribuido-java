package SistemaDistribuido;

import SistemaDistribuido.modelo.Logica;
import SistemaDistribuido.vista.VentanaLogin;
import SistemaDistribuido.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class Controlador implements ActionListener, TreeSelectionListener{
    private Logica logica;
    private VentanaPrincipal ventanaPrincipal;
    private VentanaLogin ventanaLogin;
    
    Controlador(VentanaLogin ventanaLogin) throws RemoteException{
        this.logica = new Logica(this);
        this.ventanaLogin =  ventanaLogin;
    }
    
    public String getNombre(){
        return logica.getNombre();
    }
    public String getIp(){
        return logica.getIp();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)  {

        if(e.getSource() == ventanaLogin.getButtonLogin()){
            String nombre=ventanaLogin.getLoginPanel().getNombre();
            String ip=ventanaLogin.getLoginPanel().getIp();
            if(!logica.iniciarSesion(nombre,ip)){
                ventanaLogin.mensajeError("Error, el usuario ya existe");
                ventanaLogin.getLoginPanel().limpiarCampos();
            }else{
                ventanaLogin.setVisible(false);
                ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.setVisible(true);
            }
                
        }
    }
    
    public void reportarCaida(String reporteDe,String maquinaCaida) {
        this.logica.getMaquina().reportarFallo(reporteDe,maquinaCaida);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        
    }
}
