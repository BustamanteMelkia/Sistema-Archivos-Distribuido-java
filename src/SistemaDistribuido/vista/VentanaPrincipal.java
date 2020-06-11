package SistemaDistribuido.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame{
    private final int WIDTH=870;
    private final int HEIGHT = 730;
    private JPanel panelPrincipal;
    private PanelOpciones panelOpciones;
    private PanelSistemaArchivos sistemaArchivos;
    private PanelSistemaArchivos sistemaArchivos2;
    private PanelSistemaArchivos sistemaArchivos3;
    private PanelSistemaArchivos sistemaArchivos4;

    public VentanaPrincipal() {
    	super("Sistema de Archivos Distribuido");
        this.iniciarComponentes();
    }
    private void iniciarComponentes(){
        this.setSize(WIDTH,HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        this.panelOpciones = new PanelOpciones();
        this.add(panelOpciones,BorderLayout.NORTH);
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
        this.panelPrincipal.setBackground(new Color(47,58,86));
        
        sistemaArchivos = new PanelSistemaArchivos(this,"C:\\Users\\melquia\\Desktop\\pcb circular","Maquina1");
        this.panelPrincipal.add(sistemaArchivos);
        sistemaArchivos2 = new PanelSistemaArchivos(this,"C:\\Users\\melquia\\Desktop\\C++","Maquina2");
        this.panelPrincipal.add(sistemaArchivos2);
        sistemaArchivos3 = new PanelSistemaArchivos(this,"C:\\Users\\melquia\\Desktop\\DiseñoDigital","Maquina3");
        this.panelPrincipal.add(sistemaArchivos3);
        sistemaArchivos4 = new PanelSistemaArchivos(this,"C:\\Users\\melquia\\Desktop\\Arduino","Maquina4");
        this.panelPrincipal.add(sistemaArchivos4);

        this.add(panelPrincipal,BorderLayout.CENTER);
    }

}
