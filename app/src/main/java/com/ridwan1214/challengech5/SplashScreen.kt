package com.ridwan1214.challengech5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)

        val hdIcon = findViewById<ImageView>(R.id.ivHeader)
        Glide.with(this).load(this.resources.getString(R.string.header_url)).into(hdIcon)

        val intentLandingPages = Intent(this, LandingPagesActivity::class.java)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
                this.finish()
                startActivity(intentLandingPages)
            }, 3000
        )
    }


}