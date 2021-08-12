package com.ridwan1214.challengech5

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class VsComputerActivity : AppCompatActivity(), CallBack {

    private var pilihanPlayer: String? = null
    private var pilihanPlayer2: String? = null
    private val btnComBatu by lazy { findViewById<ImageView>(R.id.ivComBatu) }
    private val btnComKertas by lazy { findViewById<ImageView>(R.id.ivComKertas) }
    private val btnComGunting by lazy { findViewById<ImageView>(R.id.ivComGunting) }
    private val btnReset by lazy { findViewById<ImageView>(R.id.ivReset) }
    private val btnClose by lazy { findViewById<ImageView>(R.id.ivClose) }
    private var player1 = ""

    private val controller = Controller(this)

    private var finish: Boolean = false
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_computer)

        val hdIcon = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(this.resources.getString(R.string.header_url)).into(hdIcon)

        player1 = intent.getStringExtra("Player")!!
        findViewById<TextView>(R.id.tvPlayer).text = player1

        val playerPicked = mutableMapOf(
            R.id.ivBatu to "batu",
            R.id.ivKertas to "kertas",
            R.id.ivGunting to "gunting"
        )

        btnReset.setOnClickListener { reset() }
        btnClose.setOnClickListener { finish() }

        playerPicked.forEach { (i, value) ->
            run {
                findViewById<ImageView>(i).setOnClickListener {
                    if (!finish) {
                        it.isClickable = false
                        it.backgroundTintList =
                            ContextCompat.getColorStateList(this, R.color.softblue)
                        pilihanPlayer = value
                        pilihanPlayer2 = computerPick()
                        controller.logicGame(pilihanPlayer, pilihanPlayer2)
                        finish = true
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun computerPick(): String {
        val option = arrayOf("batu", "kertas", "gunting")
        val comPick = option.random()
        when (comPick) {
            "batu" -> btnComBatu.backgroundTintList =
                ContextCompat.getColorStateList(this, R.color.softblue)
            "kertas" -> btnComKertas.backgroundTintList =
                ContextCompat.getColorStateList(this, R.color.softblue)
            "gunting" -> btnComGunting.backgroundTintList =
                ContextCompat.getColorStateList(this, R.color.softblue)
        }
        return comPick
    }

    @SuppressLint("NewApi")
    fun reset() {
        val setBackgroundTint = arrayOf(
            R.id.ivBatu,
            R.id.ivGunting,
            R.id.ivKertas,
            R.id.ivComBatu,
            R.id.ivComKertas,
            R.id.ivComGunting
        )

        setBackgroundTint.forEach { k ->
            findViewById<ImageView>(k).backgroundTintList = getColorStateList(R.color.white)
            findViewById<ImageView>(k).isClickable = true
        }
        pilihanPlayer = ""
        pilihanPlayer2 = ""
        finish = false
    }

    override fun tampilkanHasil(hasil: String) {
        when (hasil) {
            "1" -> ResultDialogFragment.newInstance("$player1 \n MENANG").show(supportFragmentManager,null)
            "2" -> ResultDialogFragment.newInstance("CPU \n MENANG").show(supportFragmentManager,null)
            "3" -> ResultDialogFragment.newInstance("DRAW").show(supportFragmentManager,null)
        }
        Log.d("Player1", "Pemain memilih $pilihanPlayer")
        Log.d("Com", "Computer memilih $pilihanPlayer2")
        Log.d("hasil", "hasilnya $hasil")
        Log.d("hasil", "1= Pemain1 Menang, 2= Com Menang, 3= Draw")
        Toast.makeText(this, "$player1 memilih $pilihanPlayer", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "COM memilih $pilihanPlayer2", Toast.LENGTH_SHORT).show()
    }
}

