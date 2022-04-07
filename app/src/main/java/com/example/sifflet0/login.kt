package com.example.sifflet0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    lateinit var emailEditText: TextInputEditText
    lateinit var emailLayoutLogin: TextInputLayout

    lateinit var passwordEditText: TextInputEditText
    lateinit var passwordLayoutLogin: TextInputLayout

    lateinit var loginButton: Button
    //lateinit var progBar: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.loginEmail)
        emailLayoutLogin = findViewById(R.id.txtLayoutLogin)

        passwordEditText = findViewById(R.id.loginPassword)
        passwordLayoutLogin = findViewById(R.id.txtLayoutPassword)


        loginButton = findViewById(R.id.loginButton)
        //progBar = findViewById(R.id.progressBar)

        loginButton.setOnClickListener {
            doLogin()
        }
    }

        private fun doLogin() {
            if (validate()) {
                //val apiInterface = UserApi.create()
                val apiInterface = RetrofiteInstance.api
                //progBar.visibility = View.VISIBLE

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
                var user = User()
                user.email = emailEditText.text.toString()
                user.motdepasse = passwordEditText.text.toString()
                apiInterface.seConnecter(
                    user
                ).enqueue(object :
                    Callback<User> {

                    override fun onResponse(call: Call<User>, response: Response<User>) {

                        val user = response.body()

                        if (user != null) {
                            Toast.makeText(this@login, "Login Success", Toast.LENGTH_SHORT).show()
                            navigate()
                        } else {
                            Toast.makeText(this@login, "User not found", Toast.LENGTH_SHORT).show()
                        }

                        //progBar.visibility = View.INVISIBLE
                        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {

                        Toast.makeText(this@login, "Connexion error!", Toast.LENGTH_SHORT).show()

                        //progBar.visibility = View.INVISIBLE
                        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    }

                })

            }
        }

        private fun validate(): Boolean {
            emailLayoutLogin.error = null
            passwordLayoutLogin.error = null

            if (emailEditText.text!!.isEmpty()) {
                emailLayoutLogin.error = getString(R.string.mustNotBeEmpty)
                return false
            }

            if (passwordEditText.text!!.isEmpty()) {
                passwordLayoutLogin.error = getString(R.string.mustNotBeEmpty)
                return false
            }

            return true
        }
    private fun navigate(){
        val i = Intent(this,home::class.java)
        startActivity(i)
    }


    }
