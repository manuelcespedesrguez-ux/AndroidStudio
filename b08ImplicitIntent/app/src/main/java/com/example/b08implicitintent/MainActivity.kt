package com.example.b08implicitintent

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

        // 1. Vincular el WebView de la vista
        val webView = findViewById<WebView>(R.id.webview)
        val button = findViewById<Button>(R.id.button)

        // 2. IMPORTANTE: Hace que los enlaces se abran DENTRO del WebView y no en Chrome
        webView.setWebViewClient(WebViewClient())

        // 3. Habilitar JavaScript (necesario para casi todas las webs actuales)
        val webSettings = webView.getSettings()
        webSettings.setJavaScriptEnabled(true)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // 4. Cargar la URL deseada
                webView.loadUrl("https://github.com/Sergio-EsSo")
            }
        })
    }
}