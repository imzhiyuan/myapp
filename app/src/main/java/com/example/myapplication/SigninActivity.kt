package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.signup_page.*
import kotlinx.android.synthetic.main.signup_page.view.*
import org.w3c.dom.Text

open class SigninActivity: AppCompatActivity(){
     private val url = "https://script.google.com/macros/s/AKfycbwesIpox7baQaH7TMba_MiUnp6xI6D2DKp6RL-e16_GYDX_R4s/exec"
    // test data later will change to get string from the edit text and drop box
    lateinit var npisid : EditText
    lateinit var email : EditText
    lateinit var pw : EditText
    lateinit var course : String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        //get save edit text from user
        npisid = findViewById<EditText>(R.id.regid)
        email = findViewById<EditText>(R.id.regemail)
        pw = findViewById<EditText>(R.id.regpass)


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

            spinner.onItemSelectedListener= object:
            AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                    // An item was selected.
                    course = parent.getItemAtPosition(pos).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }


        // trying to sent data to sever

        Register.setOnClickListener {
           // val textView = findViewById<TextView>(R.id.textView)
            val queue = Volley.newRequestQueue(this)
            val url = "https://script.google.com/macros/s/AKfycbwesIpox7baQaH7TMba_MiUnp6xI6D2DKp6RL-e16_GYDX_R4s/exec"

            // Request a string response from the provided URL.
            val stringRequest = object: StringRequest(Request.Method.POST, url,
                Response.Listener<String> {
                    // Display the first 500 characters of the response string.
                   // textView.text = "it worked!"
             },
                Response.ErrorListener { /*textView.text = "That didn't work!"*/ }) {
                //sending the data to the sheets
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["action"] = "Register"
                    params["NPISID"] = npisid.text.toString()
                    params["COURSE"] = course
                    params["EMAIL"] = email.text.toString()
                    params["PW"] = pw.text.toString()

                    return params
                }
            }
            // Add the request to the RequestQueue.
            queue.add(stringRequest)
            // register complete go to login page
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
