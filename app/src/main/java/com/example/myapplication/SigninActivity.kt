package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.signup_page.*

open class SigninActivity: AppCompatActivity(){
     private val url = "https://script.google.com/macros/s/AKfycbwesIpox7baQaH7TMba_MiUnp6xI6D2DKp6RL-e16_GYDX_R4s/exec"
    // test data later will change to get string from the edit text and drop box
    private val npisid = "s10185773"
    private val email = "s10185773@connect.np.edu.sg"
    private val course = "EE"
    private val pw = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        // spinner (drop down box)

        val spinner: Spinner = findViewById(R.id.courselist)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.course_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        // trying to sent data to sever

        Register.setOnClickListener {

           val stringRequest = object : SigninActivity(GET, url )
           {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params.put("NPISID",npisid)
                    params.put("COURSE",course)
                    params.put("EMAIL",email)
                    params.put("PW",pw)
                    return params
                }
           }

            Volley.newRequestQueue(this).add(stringRequest)
               
           }
        }

    }
