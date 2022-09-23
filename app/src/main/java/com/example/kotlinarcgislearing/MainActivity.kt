package com.example.kotlinarcgislearing
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    interface User {
        var first :String
        var last : String
        var age : Int
    }

    fun Test(test :User) {

    }
}
