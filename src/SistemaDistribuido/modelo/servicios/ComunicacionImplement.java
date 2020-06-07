package SistemaDistribuido.modelo.servicios;

import java.rmi.server.UnicastRemoteObject;
import ClienteInterfaces.ComunicacionInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ComunicacionImplement extends UnicastRemoteObject implements ComunicacionInterface{
    private ArrayList<String> prueba;
    public ComunicacionImplement() throws RemoteException{
        super();
        this.prueba = new ArrayList<String>();
    }
    
    public String mandarEco() throws RemoteException {
        this.prueba.add("melkia");
        //System.out.println("Servicio corriendo");
        return "Sigo vivo "+prueba;
    }
    
}
