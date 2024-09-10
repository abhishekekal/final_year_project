package com.example.test.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.test.LandingActivity
import com.example.test.LoginCompanionClass
import com.example.test.databinding.ActivityLoginBinding

import com.example.test.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var progressBarSignUp: ProgressBar? = null
    private var i = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBarSignUp = findViewById(R.id.progress_Bar)
        binding.signup!!.setOnClickListener(View.OnClickListener {
            if (binding.username.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter valid username", Toast.LENGTH_SHORT).show()
            }
            else if (binding.mobile!!.text.toString().chars().count() < 10 || !binding.mobile!!.text.toString().isValidMobile()) {
                Toast.makeText(this, "Enter valid mobile number", Toast.LENGTH_SHORT).show()
            }
            else if (binding.email!!.text.toString().isEmpty() || !binding.email!!.text.toString().isValidEmail()) {
                Toast.makeText(this, "Enter valid email id", Toast.LENGTH_SHORT).show()
            }
            else if (binding.password.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            }else {
                //Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
                LoginCompanionClass.USER_NAME = binding.email.toString()
                LoginCompanionClass.PASSWORD = binding.password.toString()
                progressBarSignUp!!.visibility = View.VISIBLE

                i = progressBarSignUp!!.progress

                Thread {
                    // this loop will run until the value of i becomes 99
                    while (i < 100) {
                        i += 1
                        // Update the progress bar and display the current value
                        handler.post {
                            progressBarSignUp!!.progress = i
                            // setting current progress to the textview
                        }
                        try {
                            Thread.sleep(20)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }

                    // setting the visibility of the progressbar to invisible
                    // or you can use View.GONE instead of invisible
                    // View.GONE will remove the progressbar
                    progressBarSignUp!!.visibility = View.INVISIBLE
                    val intent = Intent(this@SignUpActivity, LandingActivity::class.java)
                    startActivity(intent)

                }.start()
            }
        })
    }

    private fun String.isValidEmail() =
        isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidMobile() =
        isNotEmpty() && Patterns.PHONE.matcher(this).matches()

}