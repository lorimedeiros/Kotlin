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

    private lateinit var binding: ActivityMainBinding
    private var positionSelecionada =
        -1 //como a lista começa com 0 é só colocar -1 que tá tudo certo

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

        val listUsers = ArrayList<User>()
        listUsers.add(User("Lori", "0000"))
        listUsers.add(User("Yves", "NintendoBoaDms"))
        listUsers.add(User("Pedro", "MainFadinha123"))

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listUsers)
        binding.listViewUsers.adapter = adapter

        binding.listViewUsers.setOnItemClickListener { _, _, position, _ ->  //estão sendo representados por underscore pois não são utilizados
            binding.editUser.setText(listUsers.get(position).username)
            binding.editPass.setText(listUsers.get(position).password)
            //preenche os campos com as informações do user selecionado

            positionSelecionada = position //"selecionando" elemento
        }

        binding.buttonInserir.setOnClickListener {
            val username = binding.editUser.text.toString().trim()
            val password = binding.editPass.text.toString().trim()

            if (username.isNotEmpty() && !password.isEmpty()) { //deixei as duas formas de propósito para mostrar as possibilidades
                listUsers.add(User(username, password))
                adapter.notifyDataSetChanged() //aplica as alterações, sem isso aqui a lista fica a msm coisa para visualização

                //limpando campos
                binding.editUser.setText("")
                binding.editPass.setText("")
            }
        }

        binding.buttonEditar.setOnClickListener {
            if (positionSelecionada >= 0) { //para evitar que o programa dê pau certificando que tenhamos uma posição válida selecionada
                val username = binding.editUser.text.toString().trim()
                val password = binding.editPass.text.toString().trim()

                if (username.isNotEmpty() && !password.isEmpty()) {
                    listUsers.get(positionSelecionada).username = username
                    listUsers.get(positionSelecionada).password = password
                    adapter.notifyDataSetChanged()

                    binding.editUser.setText("")
                    binding.editPass.setText("")

                    positionSelecionada = -1 //sim, tem que "resetar" ou eu posso apagar o próximo elemento na posição que ficou
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Nenhum elemento selecionado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonDeletar.setOnClickListener {
            if (positionSelecionada >= 0) {
                listUsers.removeAt(positionSelecionada)
                adapter.notifyDataSetChanged()

                binding.editUser.setText("")
                binding.editPass.setText("")

                positionSelecionada = -1
            } else {
                Toast.makeText(
                    applicationContext,
                    "Nenhum elemento selecionado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonLimpar.setOnClickListener {
            listUsers.clear()

            adapter.notifyDataSetChanged()
            binding.editUser.setText("")
            binding.editPass.setText("")
            positionSelecionada = -1
        }
    }
}