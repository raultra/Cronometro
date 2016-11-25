
import java.util.Vector;
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
public class Entrenamiento {
    
    //private Date fechaEntrenamiento; 
    private Vuelta vuelta;  // referencia a vuelta actual           
    private Vector datosVueltas; // Almacena datos de todas las vueltas
    
    Entrenamiento(){
        datosVueltas= new Vector(10,2); // En cada entrenamiento se realizan 10 vueltas
        vuelta= new Vuelta();
    }
    
    public void marcarTiempos(int tiempo)
    {
        if(!vuelta.setTiempoPista(tiempo)) // si no se pudo agregar tiempo
        {            
            datosVueltas.addElement(vuelta);
            vuelta.borrarPistas();          // reinicia conteo
        }
        
            
    }
    
    public String getTiempoVueltaAnterior()
    {
            Vuelta temp;
            temp=(Vuelta) datosVueltas.lastElement();
            
            return Integer.toString(temp.getTiempoVuelta());
    }
    
    public int getNumeroVueltas()
    {
        return datosVueltas.size();
    }
    
    public Vuelta getVueltaActual()
    {
        return vuelta;
    }
}



class Vuelta
{
    
    private int tiempoVuelta;       // Tiempo total por vuelta
    private Vector tiempoPista;   // Tiempo recorrido en cada pista

    public Vuelta()
    {
        tiempoPista= new Vector(4);     // Maximo 4 pistas
    }
    
       
    public int getTiempoVuelta()
    {
        return tiempoVuelta;        
    }
    
    public Enumeration getTiemposPistas()
    {
        return tiempoPista.elements();
    }
    
    public boolean setTiempoPista(int tiempo)
    {
        
        if(tiempoPista.size()<=4)
        {
            tiempoVuelta+=tiempo;
            tiempoPista.addElement(new Integer(tiempo));
            return true;
        }
        else
            return false;           
        
    }
    
    public void borrarPistas()
    {
        tiempoPista.removeAllElements();
    }
    
 
}
