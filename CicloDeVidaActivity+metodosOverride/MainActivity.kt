package com.example.ciclodevidaactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ciclodevidaactivity.databinding.ActivityMainBinding

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

        binding.button1.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
//selecionar cada um dos usos dos 5 médodos on para debug
/*
onCreate: assim que iniciado já é chamado pois é, em prática, onde a activity é iniciada e alocada na memória
onStart: o método imediatamente anterior a apresentação da activity para o user
onResume: chamado quando a activity está pronta para ser executada e aguarda a manipulação do user, neste método a activity vai se manter até que haja uma nova interação
onPause: chamada quando a activity é minimizada ou chamada por outra activity
OBS: nenhum processo pesado ou demorado deve ser posto no onPause
onStop: a partir do momento que estou vendo a 2ª act a 1ª entra em onStop
OBS: se eu voltar da 2ª act para a 1ª, a segunda que entrará para onPause e a primeira terá novamente onStart e onResume e, AI SIM, a segunda entrará em onStop
     nisso minha 2ª activity entra em onDestroy
onDestroy: a act é destruída e, para ser novamente iniciada ela passa novamente pelo processo de construção
*/