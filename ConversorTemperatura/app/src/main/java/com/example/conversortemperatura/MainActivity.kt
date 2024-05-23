// vai converter uma temperatura de celsius para fahrenheit
package com.example.conversortemperatura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.conversortemperatura.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConverter.setOnClickListener{
            if(!binding.editCelsius.text.toString().isEmpty()) { //para verificar se tem informação no campo, pois se algum user clicar no botão sem ter informações nem tratamento de exceção a app estoura um erro
                val celsius = binding.editCelsius.text.toString().toDouble()
                val fahrenheit = celsius * 1.8 + 32

                binding.textResultado.text = fahrenheit + "ºF"
            } else {
                binding.textResultado.text = "Temperatura inválida"
            }
        }
    }
}