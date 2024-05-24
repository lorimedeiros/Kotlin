//aqui está sendo apresentado como é usada a classe Toast
//ela faz surgir um elemento visual temporario na tela, o pop-up (pequeno balãozinho na parte inferior da tela)
package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

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

            binding.button.setOnClickListener{
                val nome = binding.editNome.text.toString().trim()
                val sobrenome = binding.editSobrenome.text.toString().trim()
                //trim serve para remover espaços antes e depois do nome em caso do usar digitar, por exemplo "      Maria  "

                if(nome.isEmpty() || sobrenome.isEmpty()){
                    binding.textResultado.text = "Nome não inserido"
                    Toast.makeText(applicationContext, "Nome não inserido", Toast.LENGTH_LONG).show()
                    //aqui é feito o toast com longa duração
                } else {
                    binding.textResultado.text = "Olá $nome $sobrenome"
                    Toast.makeText(applicationContext, "Olá $nome $sobrenome", Toast.LENGTH_SHORT).show()
                    //aqui é feito o toast com curta duração
                }
            }

    }
}