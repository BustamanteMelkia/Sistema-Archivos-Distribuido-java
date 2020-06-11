package SistemaDistribuido.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelOpciones extends JPanel{
   private JButton eliminar;
   private JButton transferir;
   private JComboBox maquinas;
   private JLabel nombreUsuario;
   private JLabel activo;
   
    public PanelOpciones (){
        super();                /*Esto ya se descontroló xd*/
        iniciarComponentes();
    }
   
   private void iniciarComponentes(){
       this.setBackground(new Color(47,58,86));
       this.setLayout(new FlowLayout(FlowLayout.CENTER,5,10));
       
       this.activo = new JLabel("");
       this.activo.setIcon(new ImageIcon("punto.png"));
       this.add(activo);
       
       nombreUsuario = new JLabel("David");
       nombreUsuario.setForeground(new Color(216,232,232));
       nombreUsuario.setFont(new Font("Helvetica", Font.BOLD, 25));
       this.add(nombreUsuario);
       
       this.eliminar = new JButton("Eliminar");
       this.eliminar.setPreferredSize(new Dimension(130,30));
       this.eliminar.setBackground(new Color(216,232,232));
       this.eliminar.setForeground(new Color(47,58,86));
       this.eliminar.setFocusPainted(false);
       //this.add(eliminar);
       
       this.transferir =  new JButton("Transferir");
       this.transferir.setPreferredSize(new Dimension(130,30));
       this.transferir.setBackground(new Color(216,232,232));
       this.transferir.setForeground(new Color(47,58,86));
       this.transferir.setFocusPainted(false);
       //this.add(transferir);
       
       this.maquinas = new JComboBox();
       this.maquinas.setPreferredSize(new Dimension(170,30));
       this.maquinas.setBackground(new Color(216,232,232));
       maquinas.addItem("Seleccione Destino");
       maquinas.addItem("Maquina1");
       maquinas.addItem("Maquina2");
       maquinas.addItem("Maquina3");
       maquinas.addItem("Maquina4");
       //this.add(maquinas);
   }
    
}
