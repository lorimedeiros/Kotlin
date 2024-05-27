package com.example.myapplication2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication2.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val i = intent //instanciando esse intent podemos transportar o valor daquele posto na mainactivity
        val nome = i.extras?.getString("nome") //função que permite buscar por valores com o identificador "nome"

        if (nome.equals("") || (nome == null)){
            Toast.makeText(applicationContext, "Nome não inserido", Toast.LENGTH_SHORT).show()
        } else {
            binding.textNome.setText("Olá, $nome")
        }
    }
}