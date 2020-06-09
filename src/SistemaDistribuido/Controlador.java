package SistemaDistribuido;

import SistemaDistribuido.modelo.Logica;
import SistemaDistribuido.vista.VentanaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class Controlador implements ActionListener{
    private Logica logica;
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
            if(!logica.iniciarSesion(nombre,ip));
                ventanaLogin.getLoginPanel().limpiarCampos();
        }
    }
    
    public void reportarCaida(String reporteDe,String maquinaCaida) {
        this.logica.getMaquina().reportarFallo(reporteDe,maquinaCaida);
    }

}
