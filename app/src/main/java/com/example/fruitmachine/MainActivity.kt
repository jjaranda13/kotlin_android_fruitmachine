package com.example.fruitmachine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// esta linea se ha escrito sola al escribir el tipo View y
// seleccionar Android.view enla funcion prueba
import android.view.View
import android.widget.Button
import android.widget.ImageButton


class MainActivity : AppCompatActivity() , View.OnClickListener{




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var boton_juega : ImageButton = findViewById(R.id.boton_juega)
        var boton_platano : ImageButton = findViewById(R.id.boton_platano)

        boton_juega.setOnClickListener(this)
        boton_platano.setOnClickListener(this)


        class miclase{
            @JvmStatic var  cosa : Int =5
        }
        var pepe:miclase


    }


    fun prueba(miview: View) {
        var name = "platano"
        println("hola $name")

        var caca: String ?
        caca=null
        println ("caca es $caca")
    }



    //esta es la mejor forma de capturar eventos
    //porque vemos que funciones se asocian a los botones de esta
    //actividad, sin necesidad de explorar el layout buscando
    //que boton invoca a que funcion en el atributo onclick


    override fun onClick(v:View) {
        when (v.getId()) {
            R.id.boton_juega-> {
                // do something for button 1 click
                println ("juega")
                var caca: String ?
                caca=null
                println ("caca es $caca")

                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)

            }

            R.id.boton_platano ->{
                // do something for button 2 click
                println ("platano")

            }

            //.... etc
        }
    }


}