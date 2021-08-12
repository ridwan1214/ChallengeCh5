package com.ridwan1214.challengech5

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MenuActivity : AppCompatActivity() {

    private lateinit var player1: Pemain

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        player1 = intent.getParcelableExtra<Pemain>("Player") as Pemain

        val textVsPemain = "${player1.nama} VS Pemain"
        findViewById<TextView>(R.id.tvVsPlayer).text = textVsPemain

        val textVsCPU = "${player1.nama} VS CPU"
        findViewById<TextView>(R.id.tvVsCPU).text = textVsCPU

        val snackbar = Snackbar.make(
            findViewById(R.id.menuActivity),
            "Selamat datang ${player1.nama}",
            Snackbar.LENGTH_INDEFINITE
        )

        snackbar.setActionTextColor(Color.parseColor(resources.getString(R.color.orange)))
        snackbar.setTextColor(Color.WHITE)

        snackbar.setAction("Tutup") {
            snackbar.dismiss()
        }

        val tvSnackbar = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
        tvSnackbar.setTypeface(resources.getFont(R.font.comic_sans_ms), Typeface.BOLD)
        tvSnackbar.textSize = 20f

        snackbar.show()

        val btnVsPlayer = findViewById<ImageView>(R.id.ivVsPlayer)
        btnVsPlayer.setOnClickListener {
            val intent = Intent(this, VsPlayerActivity::class.java)
            intent.putExtra("Player", player1.nama)
            startActivity(intent)

        }

        val btnVsCPU = findViewById<ImageView>(R.id.ivVsCPU)
        btnVsCPU.setOnClickListener {
            val intent = Intent(this,VsComputerActivity::class.java)
            intent.putExtra("Player", player1.nama)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        exitProcess(0)
    }
}