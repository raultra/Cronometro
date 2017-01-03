
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;



import java.util.Enumeration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raul
 */
public class PantallaPrincipal extends Form implements CommandListener {
  
     private MIDlet app;
     private Command cmdIniciar;
     private Command cmdSalir;
     private Command cmdPausa;
     private Command cmdContinuar;  
     private Command cmdMarcar;      
     private Cronometro cronometro;
     
     private StringItem tiempoContado;                  // Tiempo Total
     private StringItem tiempoVueltaAnterior;          // vuelta anterior
     private StringItem totalVueltas;                  // Total Vueltas
     private StringItem pista[];                       // Tiempo de cada pista (4)
     
     private Entrenamiento caminata;
     private PantallaResultados estadisticas;
     
     private int contadorPistas=0;
     private int tiempoActual=0;
     private int tiempoAnterior=0;

     PantallaPrincipal(MIDlet a){
         super("Cronómetro");
         app= a;
         cmdIniciar=new Command("Iniciar",Command.OK,0);
         cmdSalir=new Command("Salir",Command.EXIT,1);
         cmdPausa=new Command("Pausar",Command.STOP,0);
         cmdContinuar=new Command("Continuar",Command.OK,1);
         cmdMarcar=new Command("Marcar",Command.OK,0);
         
         tiempoContado = new StringItem("Tiempo:", "00:00:00:00");
         tiempoVueltaAnterior = new StringItem("L x P:", "00:00:00:00");
         
         totalVueltas = new StringItem("Vuelta:", "00");
         pista= new StringItem[4];
         
         pista[0]= new StringItem("Tiempo Pista 1: ", "00:00:00:00");
         pista[1]= new StringItem("Tiempo Pista 2: ", "00:00:00:00");                    
         pista[2]= new StringItem("Tiempo Pista 3: ", "00:00:00:00");                    
         pista[3]= new StringItem("Tiempo Pista 4: ", "00:00:00:00");
         
         
                 
         this.addCommand(cmdSalir);
         this.addCommand(cmdIniciar);
         this.append(tiempoContado);
         this.append(totalVueltas);
         this.append(tiempoVueltaAnterior);
         this.append(pista[0]);
         this.append(pista[1]);
         this.append(pista[2]);
         this.append(pista[3]);
         
         this.setCommandListener(this);
     }

     public void iniciarCron()
     {
         Display.getDisplay(app).vibrate(100);
         tiempoActual=0;
         tiempoAnterior=0;
         caminata= new Entrenamiento();
         cronometro=new Cronometro(tiempoContado,tiempoVueltaAnterior);
         cronometro.Iniciar();
         this.removeCommand(cmdIniciar);
         //this.removeCommand(cmdSalir);
        //this.addCommand(cmdPausa);
         this.addCommand(cmdMarcar);

     }

    public void pararCron()
    {
         cronometro.Pausar(); 
         this.removeCommand(cmdPausa);
         this.removeCommand(cmdContinuar);  
         this.addCommand(cmdSalir); 
         this.addCommand(cmdIniciar); 
         
    }

    public void continuarCron()
    {
        cronometro.Pausar();
        cronometro=new Cronometro(tiempoContado);
        cronometro.Iniciar();
    }
    
    public void marcarCron()
    {
        int temp;
        boolean i;
        Display.getDisplay(app).vibrate(200);
        
        tiempoActual=(int)cronometro.getTiempoCron();
        cronometro.resetLapso();
        //tiempoVueltaAnterior.setText(Cronometro.DameFormatoHora(tiempoActual));
        temp=tiempoActual-tiempoAnterior;
        
        mostrarPistas(temp);
        i=caminata.marcarTiempos(temp);
        tiempoAnterior= tiempoActual;
        actualizarVista(i);
//        if(i) 
//            caminata.reiniciarVuelta();
        
    }
    
    private void actualizarVista(boolean r)
    {
        int pistas[];
         
        //tiempoContado.setLabel(caminata.);
        
        //tiempoVueltaAnterior.setText(caminata.getTiempoVueltaAnterior());
         
        totalVueltas.setText(Integer.toString(caminata.getNumeroVueltas()));
         
//        if(!r)
//        {
//            pistas= caminata.getVueltaActual().getTiemposPistas();
//
//            for (int i=0; i<pistas.length ;i++) 
//            {
//                 pista[i].setText(Cronometro.DameFormatoHora(pistas[i]));
//
//            }
//        }
//        else
//            resetPistas();
         
    }
    
    private void mostrarPistas(int temp)
    {
        if(contadorPistas==0) 
            resetPistas();
        
        pista[contadorPistas].setText(Cronometro.DameFormatoHora(temp));
         
         if(contadorPistas<3)
             contadorPistas++;
         else
             contadorPistas=0;
             
         
    }
             
    
    private void resetPistas()
    {
        String t = "00:00:00:00";
        
         pista[0].setText(t);
         pista[1].setText(t);
         pista[2].setText(t);
         pista[3].setText(t);
    }

    public void commandAction(Command c, Displayable d)
    {
         if (c==cmdSalir){
             //app.destroyApp(false);
             estadisticas= new PantallaResultados(app);
             
             app.notifyDestroyed();
         }
         else if(c==cmdIniciar){
            iniciarCron();
                       
         }
         else if (c==cmdPausa){
            pararCron();
                       
         }
         else if (c==cmdContinuar){
            continuarCron();
        }
         else if(c==cmdMarcar)
         {
             marcarCron();
             
         }
    }  
}
