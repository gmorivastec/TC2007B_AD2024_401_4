package mx.itesm.tc2007b401native

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textInput : EditText
    private lateinit var button1 : Button
    private lateinit var button2 : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // how to get a reference to a widget on the running app
        textInput = findViewById(R.id.editTextText)
        button1 = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)

        button1.text = "SAY HELLO"
        button2.text = "GO TO COMPOSE ACTIVITY"

        button2.setOnClickListener {

            // we need to create an intent object in order to open a new activity
            // in android we request the new activity, we don't order

            var intent = Intent(this, ComposeActivity::class.java)
            startActivity(intent)
        }

        // notes on variable declaration in kotlin
        // two possibilites for our variables depending on their mutability (ability to change)
        // the following variables can change value - they are mutable
        // type can be determined by value being saved or by explicitly declaring type
        var var1 = "string"
        var var2 : String

        var1 = "SOME OTHER STRING"
        var2 = "I CAN ALSO CHANGE!"

        // immutable variables
        // assigned once, value cannot change
        val var3 = "HI GUYS I'M A CONSTANT VALUE NICE TO MEET YOU"
        // var3 = "some other thing!"
    }

    // we can define logic to be used in a button in the following way
    fun sayHi(view : View) {

        // rules for a function to be available for a button press
        // 1 - receive a view

        // the view will be a reference to the view (widget) that triggered this logic

        // let's do a toast!
        // small info widget that displays a message into screen

        // we are using a factory method
        // desing pattern
        // https://en.wikipedia.org/wiki/Factory_method_pattern
        Toast.makeText(this, "HEY MY FRIEND: ${textInput.text}", Toast.LENGTH_LONG).show()

        // logs and log levels
        Log.i("LOGS", "INFO")
        Log.d("LOGS", "DEBUG")
        Log.w("LOGS", "WARNING")
        Log.e("LOGS", "ERROR")
        Log.wtf("LOGS", "WHAT A TERRIBLE FAILURE")
    }
}