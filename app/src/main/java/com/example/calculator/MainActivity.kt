package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Variables para poder realizar las operaciones

    private var n1: Double = 0.0
    private var n2: Double = 0.0
    private var operation: Int = 0

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        //Llamadas a los botones numericos
        binding.btnZero.setOnClickListener {btnNumber("0")}
        binding.btnOne.setOnClickListener {btnNumber("1")}
        binding.btnTwo.setOnClickListener {btnNumber("2")}
        binding.btnThree.setOnClickListener {btnNumber("3")}
        binding.btnFour.setOnClickListener {btnNumber("4")}
        binding.btnFive.setOnClickListener {btnNumber("5")}
        binding.btnSix.setOnClickListener {btnNumber("6")}
        binding.btnSeven.setOnClickListener {btnNumber("7")}
        binding.btnEight.setOnClickListener {btnNumber("8")}
        binding.btnNine.setOnClickListener {btnNumber("9")}
        //Llamada del boton punto
        binding.btnDot.setOnClickListener {btnNumber(".")  }
        //Llamada de botones operadores
        binding.btnSum.setOnClickListener {btnOperation(sum)  }
        binding.btnSubtraction.setOnClickListener { btnOperation(subs)  }
        binding.btnMultiplication.setOnClickListener { btnOperation(mult) }
        binding.btnDivision.setOnClickListener { btnOperation(div) }
        //Llamada de boton de reseteo
        binding.btnReset.setOnClickListener { reset() }
        //Llamada de boton de igual
        binding.btnEqual.setOnClickListener { logic() }



    }
    //Muestra en pantalla la visualisación de los numeros
    private fun btnNumber(num: String){
        //condicional ceros y decimal
        if(binding.viewResult.text == "0" && num != "."){
            binding.viewResult.text = "$num"
        }else{
            binding.viewResult.text = "${binding.viewResult.text}$num"
        }
        //condicional saber si
        if(operation == zeroOper){
            n1 = binding.viewResult.text.toString().toDouble()
        }else{
            n2 = binding.viewResult.text.toString().toDouble()
        }

    }
    //Lógica de la calculadora
    private fun logic(){
        //eleccion de operacion
       val result = when(operation){
            sum -> n1 + n2
            subs -> n1 - n2
            mult -> n1 * n2
            div -> n1 / n2
           else -> 0
       }
        n1 = result as Double
        binding.viewResult.text = if("$result".endsWith(".0")) {
            "$result".replace(".0","") }
        else{
            "%.2f".format(result)
        }

    }

    //Botones de operaciones
    private fun btnOperation(operation: Int){
        this.operation = operation
        n1 = binding.viewResult.text.toString().toDouble()
        binding.viewResult.text = "0"
    }
    //Reseteo de la aplicacion
    private fun reset(){
        n1 = 0.0
        n2 = 0.0
        binding.viewResult.text = ""
    }

    //Objetos para llamar a las operaciones
    companion object{
        const val sum = 1
        const val subs = 2
        const val mult = 3
        const val div = 4
        const val zeroOper = 0
    }
}