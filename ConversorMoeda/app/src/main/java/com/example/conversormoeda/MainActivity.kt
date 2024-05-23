// conversor euro para dolar, levando em consideração que a taxa seja 1.08
package com.example.conversormoeda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.conversormoeda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //variavel que fazemos associação com os elementos do layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConverter.setOnClickListener{
            val euros : Double = binding.editEuro.text.toString().toDouble() //aquele parâmetro ": Double" pode ser removido
            val dolares = String.format("%.2f", euros * 1.08) //essa paradinha é deveras inútim pois as versões nais recentes do Android Studio já deixam as 2 casas decimais em doubles, mas cá está para fins informativos

            binding.textDolares.text = dolares + "$" //concatenção do valor com o simbolo de dolar
        }
    }
}