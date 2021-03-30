package com.example.geominder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData


class SignUp : AppCompatActivity() {
    lateinit var fullName: TextInputEditText
    lateinit var username: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var email: TextInputEditText
    lateinit var textViewLogin: TextView
    lateinit var buttonSignUp: Button
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        fullName = findViewById(R.id.fullname)
        password = findViewById(R.id.password)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        buttonSignUp = findViewById(R.id.buttonSignUp)
        textViewLogin = findViewById(R.id.loginText)
        progress = findViewById(R.id.progress)

        buttonSignUp.setOnClickListener {
            var Fullname = fullName.text
            var Password = password.text
            var Username = username.text
            var Email = email.text

            if (Email != null && Fullname != null && Username != null && Password != null) {
                if(!Fullname.equals("") && !Username.equals("") && !Password.equals("") && !Email.equals("")){
                    progress.visibility = View.VISIBLE
                    //Start ProgressBar first (Set visibility VISIBLE)
                    //Start ProgressBar first (Set visibility VISIBLE)
                    val handler = Handler(Looper.getMainLooper())
                    handler.post(Runnable {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        val field = arrayOfNulls<String>(4)
                        field[0] = "fullname"
                        field[1] = "username"
                        field[2] = "password"
                        field[3] = "email"
                        //Creating array for data
                        val data = arrayOfNulls<String>(4)
                        data[0] = Fullname.toString()
                        data[1] = Username.toString()
                        data[3] = Password.toString()
                        data[4] = Email.toString()
                        val putData = PutData(
                            "https://192.168.1.46/GeoMinder/signup.php",
                            "POST",
                            field,
                            data
                        )
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progress.visibility = View.GONE
                                val result = putData.result
                                if(result.equals("Sign Up Success")){
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                    intent = Intent(applicationContext, Login::class.java)
                                    startActivity(intent)
                                }
                                else {
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                }
                else {
                    Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}