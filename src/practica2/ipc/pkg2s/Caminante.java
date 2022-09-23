/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2.ipc.pkg2s;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import  practica2.ipc.pkg2s.Inicio;
import static practica2.ipc.pkg2s.PanelSnake.estado;

/**
 *
 * @author jpsam
 */
public class Caminante implements Runnable{
    /**Inicio dificultades = new Inicio();
    int p = intervalo;*/
    
    PanelSnake panel;
    public Caminante(PanelSnake panel){
        this.panel=panel;
    }
    @Override
    public void run() {
        while(estado){
        panel.avanzar();
        panel.Colisiones();
        panel.ComerManzana();
        panel.repaint();
        try {
            //JOptionPane.showMessageDialog(null, "Intervalo: " + String.valueOf(Inicio.intervalo));
                Thread.sleep(Inicio.intervalo);
                //JOptionPane.showMessageDialog(null, dificultad);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Caminante.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public void parar(){
        estado = false;
    }
    
}
