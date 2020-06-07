package SistemaDistribuido.modelo;
import java.rmi.server.UnicastRemoteObject;
import ClienteInterfaces.InformacionMaquina;
import SistemaDistribuido.Controlador;
import java.rmi.RemoteException;

public class InfoMaquinaImplement extends UnicastRemoteObject implements InformacionMaquina{
    private Controlador controlador;
    private String nombre;
    private String ip;
    private String respaldoEn;
    private String respaldoDe;
    private int id;
    
    public InfoMaquinaImplement(Controlador controlador, String nombre, String ip) throws RemoteException{
        super();
        this.controlador =  controlador;
        this.nombre = nombre;
        this.ip = ip;
        this.id = 0;
        this.respaldoEn = "";
        this.respaldoDe = "";
        
    }

    @Override
    public void broadcast(String mensaje) throws RemoteException {
        
    }
    @Override
    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String getRespaldoEn() {
        return respaldoEn;
    }

    @Override
    public String getRespaldoDe() {
        return respaldoDe;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }  

    @Override
    public void setRespaldoEn(String nombre) throws RemoteException {
        this.respaldoEn = nombre;
    }

    @Override
    public void setRespaldoDe(String nombre) throws RemoteException {
        this.respaldoDe =  nombre;
    }

    @Override
    public void asignarMaquina() throws RemoteException {
        this.controlador.prueba();
    }
    
}
