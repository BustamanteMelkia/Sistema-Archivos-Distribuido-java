package SistemaDistribuido;

import SistemaDistribuido.modelo.Maquina;
import java.rmi.RemoteException;

public class Controlador {
    private Maquina maquina;

    Controlador(){
        maquina = new Maquina(this,"melkia","192.168.0.104");
    }
    
    public String getNombre(){
        return maquina.getNombre();
    }
    public String getIp(){
        return maquina.getIp();
    }
}
