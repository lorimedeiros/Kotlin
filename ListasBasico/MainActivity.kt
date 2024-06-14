package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
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

        val listaNumeros = ArrayList<Int>()

        listaNumeros.add(1)
        listaNumeros.add(2)
        listaNumeros.add(7)
        listaNumeros.add(5)
        listaNumeros.add(8)
        //listaNumeros [1, 2, 7, 5, 8]

        val primeiro = listaNumeros.get(0)
        //1

        listaNumeros.removeAt(0)
        //listaNumeros [2, 7, 5, 8]

        listaNumeros.remove(8)
        //listaNumeros [2, 7, 5]

        listaNumeros.sort()
        //listaNumeros [2, 5, 7]

        listaNumeros.size
        //3

        listaNumeros.clear()
        //listaNumeros []

        //sim, repetindo, agora irei brincar com a interface
        listaNumeros.add(1)
        listaNumeros.add(2)
        listaNumeros.add(7)
        listaNumeros.add(5)
        listaNumeros.add(8)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNumeros)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener{parent, view, position, id ->
            Toast.makeText(this, "Clicou em ${listaNumeros.get(position)}", Toast.LENGTH_SHORT).show()
        }
    }
}