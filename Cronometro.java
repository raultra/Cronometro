import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import java.util.Timer;
import java.util.TimerTask; 

class Cronometro extends TimerTask {
     private long contador=0;
     private Timer timerVelocidad;
     private StringItem  txt;

     public Cronometro(StringItem t){
                    txt=t;
     }
     public void run(){
                     contador=contador + 23; //incremento en 23 centésimas el tiempo
                     txt.setText(DameFormatoHora(contador));
     }
     public void Iniciar(){
                     contador=0;
                     timerVelocidad=new Timer();
                     timerVelocidad.schedule(this,0, 230); //cada 230 milisegundos dispara run()
     }
     public void Pausar(){
                    timerVelocidad.cancel();
     }
     
     public long getTiempoCron()
     {
         return contador;
     }
     
     public String DameFormatoHora(long millis){
         String Hora;
         long centesimas, segundos,minutos,horas;
         centesimas=millis%100;
         segundos=(millis/100) % 60;
         minutos=(millis/100)/60;
         minutos=minutos%60;
         horas=(((millis/100)/60)/60);

         if (horas<10)
         Hora="0" + horas + ":" ;
         else
         Hora= horas + ":";
         if (minutos<10)
         Hora=Hora + "0" + minutos + ":";
         else
         Hora= Hora + minutos + ":";
         if (segundos<10)
         Hora=Hora + "0" + segundos + ":";
         else
         Hora= Hora + segundos + ":";
         if (centesimas<10)
         Hora=Hora + "0" + centesimas;
         else
         Hora= Hora + centesimas;
         return Hora ;
     }
}
