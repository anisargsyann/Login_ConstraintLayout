package com.example.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    var emailIsValid = true
    var passwordIsValid = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val button = findViewById<Button>(R.id.button)
        fun emailValidation( email: EditText) :Boolean {
            if(email.text.toString().isEmpty()) {
                emailIsValid = false
                email.error = "Invalid email"
            }
            else if(!((email.text.toString().contains("@gmail.com")) || (email.text.toString().contains("@mail.ru")))) {
                emailIsValid = false
                email.error = "Invalid email"
            }
            return emailIsValid
        }

        fun passwordValidation(password: EditText) : Boolean {
            if(password.text.isEmpty())
                passwordIsValid = false

            val passwordREGEX = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");
            passwordIsValid = passwordREGEX.matcher(password.text.toString()).matches()
            if(!passwordIsValid) password.error = "Invalid password"

            return passwordIsValid
        }
        fun check() {
            emailValidation(email)
            passwordValidation(password)
            if(emailIsValid && passwordIsValid) {
                Toast.makeText(applicationContext,"Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        button.setOnClickListener {
            check()
            Toast.makeText(applicationContext,"${email.text}  mail is $emailIsValid", Toast.LENGTH_SHORT).show()
            Toast.makeText(applicationContext,"${password.text} password is $passwordIsValid",
                Toast.LENGTH_SHORT).show()
        }
    }
}

