/*
serão lidos login e senha
qualquer login diferente de "user" e qualquer senha diferente de "pass" resultará em login inválido
em ambos os casos (válido/inválido) os dados digitados pelo usuário serão apagados após o clique no botão
*/
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

        //trabalhando com as situações de interação com os campos de login
        binding.buttonEntrar.setOnClickListener{
            val username = binding.editUsername.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if (username.equals("user") && password.equals("pass")){
                Toast.makeText(applicationContext, "Login válido!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Login inválido!", Toast.LENGTH_SHORT).show()
            }
            //limpando as informações
            binding.editUsername.setText("")
            binding.editPassword.setText("")
        }
    }
}
