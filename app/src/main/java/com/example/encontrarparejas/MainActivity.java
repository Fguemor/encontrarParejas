package com.example.encontrarparejas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    //Declaraciín de los botones
    ImageButton ib1;
    ImageButton ib2;
    ImageButton ib3;
    ImageButton ib4;
    ImageButton ib5;
    ImageButton ib6;
    boolean bloqueo= false;
    //Boton para reiniciar
    Button b2;
    //las imagenes
    int imagenes[] ;
    //se guardan duplicadas en un array
    ImageButton[] botones = new ImageButton[6];
    //para barajar
    ArrayList<Integer> barajado;
    //los botones que se han pulsado, para saber cual ha sido el primero en pulsarse
    ImageButton primero;
    //posiciones de las imágenes a comparar en el vector de imágenes
    int numero_1, numero_2;
    //el número de aciertos
    int aciertos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Identifico las imageButton
        ib1 = (ImageButton) findViewById(R.id.ib1);
        ib2 = (ImageButton)findViewById(R.id.ib2);
        ib3 = (ImageButton)findViewById(R.id.ib3);
        ib4 = (ImageButton)findViewById(R.id.ib4);
        ib5 = (ImageButton)findViewById(R.id.ib5);
        ib6 = (ImageButton)findViewById(R.id.ib6);
        botones[0] = ib1;
        botones[1] = ib2;
        botones[2] = ib3;
        botones[3] = ib4;
        botones[4] = ib5;
        botones[5] = ib6;
        //Guardo el fondo que tendrán las cartas al inicio
        ib1.setImageResource(R.drawable.img);
        ib2.setImageResource(R.drawable.img);
        ib3.setImageResource(R.drawable.img);
        ib4.setImageResource(R.drawable.img);
        ib6.setImageResource(R.drawable.img);
        ib5.setImageResource(R.drawable.img);
        imagenes= new int[]{R.drawable.img_3, R.drawable.img5, R.drawable.img_2,};

    }

    /**
     * Método para reiniciar el juego
     * @param view
     */
    public void reiniciar(View view) {
        ib1.setImageResource(R.drawable.img);
        ib2.setImageResource(R.drawable.img);
        ib3.setImageResource(R.drawable.img);
        ib4.setImageResource(R.drawable.img);
        ib6.setImageResource(R.drawable.img);
        ib5.setImageResource(R.drawable.img);

    }

    /**
     * Método que utilizo para desordenar las fotos
     * de mis cartas
     * @param longitud
     * @return
     */
    public ArrayList<Integer> mezclar(int longitud) {
        ArrayList resultado = new ArrayList<Integer>();
        for (int i = 0; i < longitud; i++)
            resultado.add(i % longitud / 2);
        //Shuffle las coge aleatoriamente
        Collections.shuffle(resultado);
        return resultado;
    }

    /**
     * Método Jugar al cual llamamos al hacer el click en una
     * carta, este nos muestra imagenes de forma aleatoria
     * @param view
     */
    public void jugar(View view) {
        //Llamo al método mezclar
        barajado = mezclar(imagenes.length * 2);
        //declaro un Imagebutton
        ImageButton ib=(ImageButton) view;
        for (int i=0;i>botones.length;i++)

            ib.setImageResource(imagenes[barajado.get(1)]);
        if (!bloqueo)
            comprobar(2, ib);





        }




    /**
     * Método para comprobar si son correctas
     * @param i
     * @param ib
     */
    public void comprobar(int i, final ImageButton ib){
        //Si no hay ningun boton pulsado
        if (primero==null){
            //primero será el boton que acabamos de pulsar
            primero=ib;
            primero.setImageResource(imagenes[barajado.get(i)]);
            primero.setEnabled(false);
            numero_1=barajado.get(i);

        }else{
            bloqueo=true;
            ib.setImageResource(imagenes[barajado.get(i)]);
            ib.setEnabled(false);
            numero_2 =barajado.get(i);
            //Comparo si los numeros son iguales
      if (numero_1== numero_2) {
          primero = null;
          bloqueo=false;
          aciertos++;
          //Si tenemos tres aciertos ponemos has ganado con Toast
          if (aciertos == 3) {
              Toast toast = Toast.makeText(this, "Has ganado "+aciertos, Toast.LENGTH_LONG);
              toast.show();
          }
      }else{
          primero.setImageResource(R.drawable.img);
          ib.setImageResource(R.drawable.img);
         primero.setEnabled(true);
         ib.setEnabled(true);
          primero=null;
          bloqueo=false;

      }
       }
    }


}










