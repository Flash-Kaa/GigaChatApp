package com.flasshka.networktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.flasshka.networktest.models.database.Dependencies
import com.flasshka.networktest.ui.theme.NetworkTestTheme
import com.flasshka.networktest.viewmodels.VM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DI will be added
        val vm: VM by lazy {
            Dependencies.init(this)
            VM(Dependencies.repository)
        }

        /*val requests = Request(vm)

        newSingleThreadContext("myCoroutine").use { myCoroutine ->
            runBlocking(myCoroutine) {
                requests.getToken()
                requests.getAnswer()
            }
        }*/

        setContent {
            NetworkTestTheme {
                Surface(
                    Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}