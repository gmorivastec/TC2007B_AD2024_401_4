package mx.itesm.tc2007b401native

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import mx.itesm.tc2007b401native.ui.theme.TC2007B401NativeTheme

// things to import in order to use state values
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ComposeActivity : ComponentActivity() {

    // the lifecycle of an activity remains the same
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TC2007B401NativeTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListExample()
                }
            }
        }
    }

    // SUPER IMPORTANT
    // SCOPE - composables can be defined within the scope of a class
    // or outside

    // within the class they have access to attributes / methods of a class
    // outside they don't

    // if defined within a class they can be restricted

    @Composable
    fun TheButton() {
        Button(
            onClick = {
                // whatever logic works on views activities will also work here
                // the Context type:
                // - an object that can interact with native behaviour in the OS
                // - an activiy is also a context object
                Toast.makeText(this, "TOAST FROM THE BUTTON", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = "SAY HI")
        }
    }

}


// UIs in compose are made up of building blocks called composables
// to declare a composable we need to:
// - declare a function
// - a @composable annotation
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

// doing a new composable
// speaking of scope: if you declare a composable outside you will not have access to
// attributes / behaviours in a class

// ? - nullable
// a type that can have a null object
@Composable
fun Example1(activity : Activity? = null) {

    // things to do here
    // something that contains our elements
    // to add several composables into a single one we need a container
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("THIS IS A TEXT")
        Text("THIS IS ANOTHER TEXT")
        Image(
            painter = painterResource(id = R.drawable.puppy1),
            contentDescription = "a cute puppy"
        )
        // nullable types and non-nullable types are not the same!
        // even if structurally speaking they are
        HiButton(activity = activity)
    }

    // scope test
}

@Composable
fun HiButton(activity : Activity? = null){
    Button(
        onClick = {
            Toast.makeText(activity, "HI FROM THE OUTSIDE!", Toast.LENGTH_SHORT).show()
        }
    ) {
        Text("SAY HI FROM OUTSIDE THE CLASS")
    }
}

// INPUT TEST / STATE VARIABLES
@Composable
fun InputTest(activity: Activity? = null) {

    // state variables - internal variables of a composable
    // that can trigger an event if changed

    // event - something that happens that triggers a recompose
    // recompose - redraw

    var name by remember { mutableStateOf("doggy") }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("The doggy's name is: $name")
        TextField(
            value = name,
            onValueChange = {
                // it is the name of the argument received
                // when the name is not specified
                name = it
            }
        )
        Button(
            onClick = {
                Toast.makeText(activity, "HI $name", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = "SAY HI TO THE DOGGY")
        }
    }
}

// let's talk about lists
@Composable
fun ListExample() {
    // 2 types of lists
    // "regular" - we already know size we load it in batch
    // "lazy" - elements are show while loaded

    // the regular one is a column
    Column {
        /*
        ListRow(id = R.drawable.puppy1, text = "PUPPY 1")
        ListRow(id = R.drawable.puppy1, text = "PUPPY 2")
        ListRow(id = R.drawable.puppy1, text = "PUPPY 3")
        ListRow(id = R.drawable.puppy1, text = "PUPPY 4")
        DoggyList(names = listOf<String>("doggy 1", "doggy 2", "doggy 3"))
        */

        // lazy column
        LazyColumn {
            // main difference - we will be using blocks called items

            // can be done individually
            item {
                ListRow(id = R.drawable.puppy1, text = "a puppy")
            }

            // .. or by batch
            items(3) { i ->
                ListRow(id = R.drawable.puppy1, text = "puppy $i")
            }
        }
    }

}

@Composable
fun ListRow(id : Int, text : String) {
    Row {
        Image(
            painter = painterResource(id = id),
            contentDescription = "a cute puppy"
        )
        Text(text)
    }
}

@Composable
fun DoggyList(names : List<String>) {
    Column {
        names.forEach { currentName ->
            ListRow(id = R.drawable.puppy1, text = currentName)
        }
    }
}

// you can have a special composable called preview
// that will only work on android studio
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TC2007B401NativeTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            InputTest()
        }
    }
}