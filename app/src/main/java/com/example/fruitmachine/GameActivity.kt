package com.example.fruitmachine

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.security.KeyStore
import java.util.*

// la clase hereda de otras dos clases
class GameActivity : AppCompatActivity(), View.OnClickListener {

    //Our variables
    //private var mImageView: ImageView? = null
    private var mUri: Uri? = null
    //Our widgets
    //private lateinit var btnCapture: Button
    //private lateinit var btnChoose : Button
    //Our constants
    private val OPERATION_CAPTURE_PHOTO = 1
    private val OPERATION_CHOOSE_PHOTO = 2

    private fun capturePhoto(){

        val almacenaje="externalFile" // externalcache , externalFile, internal, resource
        var capturedImage: File =File("")
        if (almacenaje=="externalcache") {
            //external storage (sd card) de tipo cache
            capturedImage = File(externalCacheDir, "My_Captured_Photo.jpg")
        }

        else if (almacenaje=="externalFile"){
          //external storage de tipo fichero. funciona pero no lo encuentro
          val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
          capturedImage = File(dir, "My_Captured_Photo.jpg")
        }
        ///internal storage. es imposible de ver desde el sistema ficheros de emulador
        else if (almacenaje=="internal") {
            capturedImage = File(getFilesDir() , "My_Captured_Photo.jpg")
        }
        //como resource
        if (almacenaje=="resource")
        {
            val mis: InputStream = this.resources.openRawResource(R.raw.donald)
            val reader = BufferedReader(InputStreamReader(mis))

            //val bufferedReader: BufferedReader = File("/raw/donald.jpg").bufferedReader()
            //val inputString = bufferedReader.use { it.readText() }
            //println(inputString)
            var visor: ImageView = ImageView(this)
            visor.setImageResource(R.raw.donald) //funciona

            val layout: ConstraintLayout = findViewById(R.id.milayout)
            layout.addView(visor)
            return
        }



        if(capturedImage.exists()) {
            capturedImage.delete()
        }
        capturedImage.createNewFile()




        mUri = if(Build.VERSION.SDK_INT >= 24){
           // FileProvider.getUriForFile(this, "info.camposha.kimagepicker.fileprovider",
             //   capturedImage)
            FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                BuildConfig.APPLICATION_ID + ".provider", capturedImage);

        } else {
            Uri.fromFile(capturedImage)
        }

        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri)




        startActivityForResult(intent, OPERATION_CAPTURE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        println ("imagen capturada")
        println(mUri)
        var visor: ImageView = ImageView (this)
        visor.setImageURI(mUri)


        val layout: ConstraintLayout = findViewById(R.id.milayout)
        layout.addView(visor)

    }


    var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var image1 : ImageView = findViewById(R.id.imageView1)
        var image2 : ImageView = findViewById(R.id.imageView2)
        var image3 : ImageView = findViewById(R.id.imageView3)
        var boton: Button =findViewById(R.id.button)
        image1.setOnClickListener(this)
        image2.setOnClickListener(this)
        image3.setOnClickListener(this)
        boton.setOnClickListener(this)

    }



    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.imageView1-> {
                // do something for button 1 click
                println ("1")



            }

            R.id.imageView2 ->{
                // do something for button 2 click
                println ("2")

            }
            R.id.imageView3 ->{
                // do something for button 2 click
                println ("3")

            }
            R.id.button ->{
                // do something for button 2 click
                if (count==0) {
                    println("boton")
                    var image1: ImageView = findViewById(R.id.imageView1)
                    image1.setImageResource(R.drawable.platano)
                    count ++


                    var str = null//"Hello World"
                    str.let { println("$it!!") }
                    println(str)
                    print ("comprobado")

                }
                else if (count==1){
                    println("boton")
                    var image2: ImageView = findViewById(R.id.imageView2)
                    image2.setImageResource(R.drawable.platano)

                    var txt: TextView =  TextView(this)
                    txt.setText("FELICIDADES")
                    txt.setTextSize(40.0F)

                    val layout: ConstraintLayout = findViewById(R.id.milayout)
                    layout.addView(txt)

                    count++
                }
                else{
                    println("foto")
                    /*
                    val imageCaptureHelper by lazy {
                        ImageCaptureHelper(this)



                    }
                    */
                    //imageCaptureHelper.takePhoto()
                    capturePhoto()



                }

            }
        }
        //.... etc
      }//end func








}//end class
