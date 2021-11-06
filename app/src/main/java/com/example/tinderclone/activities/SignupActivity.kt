package com.example.tinderclone.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tinderclone.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener{
        val user = firebaseAuth.currentUser
        if(user != null) {
            startActivity(MainActivity.newIntent(this))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener (firebaseAuthListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }


    fun onSignup(v: View){
        if(!emailET.text.toString().isNullOrEmpty() && !password_ET.text.toString().isNullOrEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(emailET.text.toString(), password_ET.text.toString())
                .addOnCompleteListener{task ->
                    if(!task.isSuccessful){
                        Toast.makeText(this,"Signup error ${task.exception?.localizedMessage}",Toast.LENGTH_SHORT).show()

                    }
                }

        }
    }


    companion object{
        fun newIntent(context: Context?)= Intent(context,SignupActivity::class.java)
    }


}