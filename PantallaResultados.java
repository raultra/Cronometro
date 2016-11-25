import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.StringItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raul
 */

public class PantallaResultados extends Form implements CommandListener
{
     private Entrenamiento stats;
     private StringItem tiempoContado;
     private StringItem tiempoVueltaAnterior;
     private StringItem totalVueltas;
     private StringItem pista1,pista2,pista3,pista4;
     
     
     private Command cmdSalir;
    
    PantallaResultados(MIDlet app)
    {
         super("Estadisticas de la Sesión");
         tiempoContado = new StringItem("Tiempo Total: ", "00:00:00:00");
         tiempoVueltaAnterior = new StringItem("Vuelta anterior: ", "00:00:00:00");
         
         totalVueltas = new StringItem("Número de vueltas", "00");
         pista1 = new StringItem("Tiempo Pista 1: ", "00:00:00:00");
         pista2 = new StringItem("Tiempo Pista 2: ", "00:00:00:00");
         pista3 = new StringItem("Tiempo Pista 3: ", "00:00:00:00");
         pista4 = new StringItem("Tiempo Pista 4: ", "00:00:00:00");
         
         Display.getDisplay(app).setCurrent(this);
         this.setCommandListener(this);
         
    }
    
    public void setEntrenamientoDatos(Entrenamiento a)
    {
        stats=a;
    }
    
    public void mostrarDatosEntrenamiento()
    {
        
        
    }
    
    public void commandAction(Command c, Displayable d)
    {
         if (c==cmdSalir){
             //app.destroyApp(false);
             
             
            
         }
     
    }  
    
}
