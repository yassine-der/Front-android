package com.example.sifflet0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var nomEditText: EditText
    lateinit var prenomEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var confirmaPasswordEditText: EditText
    lateinit var loginButton1: Button
    lateinit var registreButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nomEditText = findViewById(R.id.signUpNom)
        prenomEditText = findViewById(R.id.signUpPrenom)
        emailEditText = findViewById(R.id.signUpEmail)
        passwordEditText = findViewById(R.id.signUppassword)
        confirmaPasswordEditText = findViewById(R.id.signUpConfirmPassword)
        loginButton1 = findViewById(R.id.loginButtonSignUp)
        registreButton = findViewById(R.id.signUpButton)

        loginButton1.setOnClickListener{
            val k = Intent(this,login::class.java)
            startActivity(k)
        }

        registreButton.setOnClickListener{
            val nom = nomEditText.toString().trim()
            val prenom = prenomEditText.toString().trim()
            val password = passwordEditText.toString().trim()
            val confimePassword = confirmaPasswordEditText.toString().trim()
            val email1 = emailEditText.toString().trim()

            if(email1.isEmpty()){
                emailEditText.error = "Email required"
            }
        }
    }
}