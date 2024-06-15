/*
   Objetivo do prog: ter uma persistencia de dados com shared preferences.
   Isso é, fechar a aplicação e, quando abrir novamente, os dados estarem la
*/

package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = this.getSharedPreferences("valores", MODE_PRIVATE)
        val valor = sharedPreferences.getString("valor", "")

        binding.textResult.setText(valor)

        binding.button.setOnClickListener {
            val novoValor = binding.editValor.text.toString()
            binding.textResult.setText(novoValor)

            val editor = sharedPreferences.edit()
            editor.putString("valor", novoValor) //o nome da chave la de cima + conteudo da atualização
            editor.apply()
        }

        /*
        1. Vá nos 3 pontos na lateral superior esquerda da IDE
        2. Device Explorer
        3. data
        4. data (sim dnv)
        5. com.example.myapplication ou no lugar de myapplication o nome q vc deu ao projeto
        6. shared_prefs
        7. txaram! achou! o arquivo vai estar com o nome da chave que você deu, no meu caso "valores"
        */
    }
}