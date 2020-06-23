package com.example.myvisualmath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.webkit.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set content view
        setContentView(R.layout.activity_main)

        //val myWebView: WebView = findViewById(R.id.mainWebView)

        mainWebView.settings.javaScriptEnabled = true
        mainWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        mainWebView.settings.setSupportZoom(true)
        mainWebView.settings.builtInZoomControls = true
        mainWebView.settings.displayZoomControls = false

        mainWebView.webViewClient = MyWebViewClient()
        mainWebView.webChromeClient = MyWebChromeClient()

        if (savedInstanceState == null) {
            mainWebView.loadUrl("http://myvisualmath.ru/")
        }
    }

    private inner class MyWebChromeClient : WebChromeClient() {
        // handle new tab action urls if required
        override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
            super.onShowCustomView(view, callback)
        }
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
    override fun onBackPressed() {
        if (mainWebView.canGoBack()) {
            mainWebView.goBack()
        } else {
            super.onBackPressed();
        }
    }
}
