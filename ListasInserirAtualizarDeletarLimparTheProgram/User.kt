package com.example.myapplication

class User (var username: String, var password: String){

    override fun toString(): String {
        return "username: $username, password: $password"
    }
}