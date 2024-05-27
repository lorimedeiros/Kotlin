package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var result: ActivityResultLauncher<Intent>
    private var nome = ""

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

        binding.buttonMudarNome.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            i.putExtra("nome", nome)
            result.launch(i) //dessa forma é possível passar para o outro lado e aplicar a informação
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null) {
                if (it.data!!.getStringExtra("nome").equals("")) {
                    Toast.makeText(applicationContext, "Operação cancelada", Toast.LENGTH_SHORT)
                        .show()
                } else if (it.resultCode == 1) { //esse 1 é o valor dado lá na mainactivity2 (tem que ser igual)
                    nome = it.data?.getStringExtra("nome")
                        .toString() //essa interrogação significa um possível null
                    binding.textNome.text = "Olá, $nome"
                } else if (it.resultCode == 2) {
                    Toast.makeText(applicationContext, "Operação cancelada", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(applicationContext, "Erro ao atualizar nome", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}