package com.example.sifflet0

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.api.UserApi
import com.example.sifflet0.models.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

const val PREF_NAME = "LOGIN_PREF"
const val EMAIL = "EMAIL"
const val NOM = "NOM"
const val ROLE = "ROLE"
const val PRENOM = "PRENOM"
const val IMAGE2 = "IMAGE2"
const val PASSWORD = "PASSWORD"
const val TOKEN = "TOKEN"
const val USER_ID = "USER_ID"
const val IS_REMEMBRED = "IS_REMEMBRED"
lateinit var role : String


class login : AppCompatActivity() {
    lateinit var emailEditText: TextInputEditText
    lateinit var emailLayoutLogin: TextInputLayout
    lateinit var checkBox: CheckBox

    lateinit var passwordEditText: TextInputEditText
    lateinit var passwordLayoutLogin: TextInputLayout

    lateinit var loginButton: Button
    lateinit var registerButton: Button
    //lateinit var progBar: CircularProgressIndicator
    lateinit var  sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        emailEditText = findViewById(R.id.loginEmail)
        emailLayoutLogin = findViewById(R.id.txtLayoutLogin)

        passwordEditText = findViewById(R.id.loginPassword)
        passwordLayoutLogin = findViewById(R.id.txtLayoutPassword)

        checkBox = findViewById(R.id.checkBox0)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.creeCompteLogin)
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        //progBar = findViewById(R.id.progressBar)

        if (sharedPreferences.getBoolean(IS_REMEMBRED, false)){
            val role :String = sharedPreferences.getString("ROLE", null)!!
            println("9999999999999999999999999999999777777")
            println(role)
            println("9999999999999999999999999999999777777")

            if(role == "SimpleUser" )
            {
                navigateUser()

            }
            else if(role == "ProprietaireDestade" ){
                navigate()

            }

        }
        registerButton.setOnClickListener {
            navigateRegister()
        }
        loginButton.setOnClickListener {
            doLogin()
        }
    }

        private fun doLogin() {
            if (validate()) {
                //val apiInterface = UserApi.create()
                val apiInterface = RetrofiteInstance.api(this)
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
                            println("&&&&&&&&&&&&&&&&&&&&")
                            println(user.isProprietaireDestade)
                            println("&&&&&&&&&&&&&&&&&&&&&&&")

                            if (checkBox.isChecked){
                                //TODO 4 "Edit the SharedPreferences by putting all the data"
                                sharedPreferences.edit().apply{
                                    putBoolean("IS_REMEMBRED", true)
                                    putString("EMAIL",user.email)
                                    putString("TOKEN", user.token)
                                    putString("USER_ID", user._id)
                                    putString("NOM", user.nom)
                                    putString("ROLE", user.isProprietaireDestade)
                                    putString("PRENOM", user.prenom)
                                    putString("IMAGE2", user.image)

                                }.apply()

                            }else{
                                sharedPreferences.edit().apply{
                                    putString("EMAIL",user.email)
                                    putString("TOKEN", user.token)
                                    putString("USER_ID", user._id)
                                    putString("NOM", user.nom)
                                    putString("ROLE", user.nom)
                                    putString("PRENOM", user.prenom)
                                    putString("IMAGE2", user.image)

                                }.apply()

                            }

                            Toast.makeText(this@login, "Login Success", Toast.LENGTH_SHORT).show()
                            if(user.isProprietaireDestade == "SimpleUser" )
                            {
                                navigateUser()

                            }
                            else if(user.isProprietaireDestade == "ProprietaireDestade" ){
                                navigate()

                            }

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
        val i = Intent(this,MainActivityhome::class.java)
        startActivity(i)
    }
    private fun navigateUser(){
        val i = Intent(this,home::class.java)
        startActivity(i)
    }
    private fun navigateRegister(){
        val l = Intent(this,register::class.java)
        startActivity(l)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view: View? = currentFocus
        if (view != null && (ev.getAction() === MotionEvent.ACTION_UP || ev.getAction() === MotionEvent.ACTION_MOVE) && view is EditText
        ) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x: Float = ev.getRawX() + view.getLeft() - scrcoords[0]
            val y: Float = ev.getRawY() + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) (this.getSystemService(
                INPUT_METHOD_SERVICE
            ) as InputMethodManager).hideSoftInputFromWindow(
                this.window.decorView.applicationWindowToken, 0
            )
        }
        return super.dispatchTouchEvent(ev)
    }





}
