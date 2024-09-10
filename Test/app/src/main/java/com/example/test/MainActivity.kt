package com.example.test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.databinding.ActivityMainBinding
import com.example.test.ui.login.SignUpActivity

class MainActivity : AppCompatActivity() {

    lateinit var username : EditText
    lateinit var password: EditText
    private lateinit var binding: ActivityMainBinding
    private var progressBar: ProgressBar? = null
    private var i = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar = findViewById(R.id.progress_Bar)
        binding.loginButton.setOnClickListener(View.OnClickListener {
            if (binding.username.text.toString().isEmpty() || !binding.username.text.toString().isValidEmail()) {
                Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show()
            }
            else if (binding.password.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            }
            else if (binding.username.text.toString() == "user" && binding.password.text.toString() == "1234"){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            } else {
                //Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
                progressBar!!.visibility = View.VISIBLE

                i = progressBar!!.progress

                Thread {
                    // this loop will run until the value of i becomes 99
                    while (i < 100) {
                        i += 1
                        // Update the progress bar and display the current value
                        handler.post {
                            progressBar!!.progress = i
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
                    progressBar!!.visibility = View.INVISIBLE
                    val intent = Intent(this@MainActivity, GenderSelection::class.java)
                    startActivity(intent)

                }.start()

            }
        })
    }

    fun onSignUpClick(view: View) {
        val intent = Intent(this@MainActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}