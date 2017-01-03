
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

    Entrenamiento() {
        datosVueltas = new Vector(4, 2); // En cada entrenamiento se realizan 10 vueltas
        vuelta = new Vuelta();
    }

    public boolean marcarTiempos(int tiempo) {
        if (!vuelta.setTiempoPista(tiempo)) // si no se pudo agregar tiempo
        {
            datosVueltas.addElement(vuelta);
            return true;                // Nuevo conteo vuelta
        }

        return false;

    }

    public String getTiempoVueltaAnterior() {
        Vuelta temp;
        if (datosVueltas.isEmpty()) {
            return "00:00:00:00";
        } else {
            temp = (Vuelta) datosVueltas.lastElement();
            return Cronometro.DameFormatoHora(temp.getTiempoVuelta());

        }

    }

    public int getNumeroVueltas() {
        return datosVueltas.size();
    }

    public Vuelta getVueltaActual() {
        return vuelta;
    }

    public void reiniciarVuelta() // Recuenta tiempos de pistas
    {
        vuelta = new Vuelta();
    }
}

class Vuelta {

    private int tiempoVuelta;       // Tiempo total por vuelta
    private int[] tiempoPista;   // Tiempo recorrido en cada pista

    private int contadorTiempoPista;    // Cuenta el numero de elementos almacenados

    public Vuelta() {
        tiempoPista = new int[4];     // Maximo 4 pistas
    }

    public int getTiempoVuelta() {
        return tiempoVuelta;
    }

    public int[] getTiemposPistas() {
        return tiempoPista;

    }

    /**
     * Regresa el numero de elementos contenidos en el arreglo tiempoPista y lo
     * almacena en tiempoPistaLength
     *
     * @return
     */
    public int getTiempoPistaLength() {
        int tiempoPistaLength = 0;

        for (int i = 0; i < tiempoPista.length; i++) {
            if (tiempoPista[i] != 0) {
                tiempoPistaLength++;
            }
        }

        return tiempoPistaLength;

    }

    public boolean setTiempoPista(int tiempo) {
        int index = 0, temp = 0;

//        index=getTiempoPistaLength()+1;
//        if(index>1)
//            temp= tiempoPista[index-2];
        tiempoPista[contadorTiempoPista] = tiempo;
        if (contadorTiempoPista < 3) {
            contadorTiempoPista++;
            return true;
        } else 
        {
            contadorTiempoPista=0;
        }

//        if(++index ==5)
//        {
//            tiempoVuelta=tiempo;        // Guarda el tiempo de la vuelta completa
//            return false;
//        }
//        else
        return false;

    }

}
