package com.example.xhackdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContentView(R.layout.activity_main)

        val content = findViewById<TextView>(R.id.textview)

        content.viewTreeObserver.addOnPreDrawListener (
            object: ViewTreeObserver.OnPreDrawListener{
                override fun onPreDraw(): Boolean {
                    if (false){
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        return true
                    } else {
                        return false
                    }
                }
            }
        )
    }
}