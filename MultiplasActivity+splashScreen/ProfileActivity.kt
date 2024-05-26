package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding //note que o nome muda para profile, enquanto na outra classe está main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonVoltar.setOnClickListener {
            /*
            OBS: esse pedaço de código irá voltar, porém esse start activity cria uma nova instancia
            é notório isso quando se aperta no botão voltar do próprio emulador
            ocorre que, se você apertou ir-voltar 3x, você terá que apertar o voltar do emulador 3x
            */
            // startActivity(Intent(this, MainActivity::class.java))

            //para resolver esse problema de instancias existe a simples e seguinte função:
            finish()
            // esse método irá parar a aplicação e terminar a execução, da mesma forma irá ir e voltar com o uso dos botões
        }
    }
}