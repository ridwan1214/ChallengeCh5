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

class VsPlayerActivity : AppCompatActivity(), CallBack {

    private var player1 = ""
    private var pilihanPlayer: String? = null
    private var pilihanPlayer2: String? = null
    private val ivBatu by lazy { findViewById<ImageView>(R.id.ivBatu) }
    private val ivKertas by lazy { findViewById<ImageView>(R.id.ivKertas) }
    private val ivGunting by lazy { findViewById<ImageView>(R.id.ivGunting) }
    private val ivComBatu by lazy { findViewById<ImageView>(R.id.ivComBatu) }
    private val ivComKertas by lazy { findViewById<ImageView>(R.id.ivComKertas) }
    private val ivComGunting by lazy { findViewById<ImageView>(R.id.ivComGunting) }
    private val btnReset by lazy { findViewById<ImageView>(R.id.ivReset) }
    private val btnClose by lazy { findViewById<ImageView>(R.id.ivClose) }


    @SuppressLint("NewApi", "SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_player)

        val hdIcon = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(this.resources.getString(R.string.header_url)).into(hdIcon)

        player1 = intent.getStringExtra("Player")!!
        findViewById<TextView>(R.id.tvPlayer).text = player1
        findViewById<TextView>(R.id.tvCom).text = "Pemain2"

        val controller = Controller(this)
        val dataPilihanPlayer1 = arrayListOf(ivBatu, ivKertas, ivGunting)
        val dataPilihanPlayer2 = arrayListOf(ivComBatu, ivComKertas, ivComGunting)
        val choice = arrayListOf("batu","kertas","gunting")

        btnReset.setOnClickListener { reset() }
        btnClose.setOnClickListener { finish() }

        dataPilihanPlayer1.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                for (item in dataPilihanPlayer1){
                    pilihanPlayer = choice[index]
                    item.isClickable = false
                    if (item == dataPilihanPlayer1[index])
                    item.backgroundTintList = ContextCompat.getColorStateList(this, R.color.softblue)
                    else item.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
                }
                if (pilihanPlayer != null && pilihanPlayer2 != null)
                    controller.logicGame(pilihanPlayer,pilihanPlayer2)
            }
        }

        dataPilihanPlayer2.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                for (item in dataPilihanPlayer2){
                    pilihanPlayer2 = choice[index]
                    item.isClickable = false
                    if (item == dataPilihanPlayer2[index])
                        item.backgroundTintList = ContextCompat.getColorStateList(this, R.color.softblue)
                    else
                        item.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
                }
                if (pilihanPlayer != null && pilihanPlayer2 != null)
                    controller.logicGame(pilihanPlayer,pilihanPlayer2)
            }
        }


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
        pilihanPlayer = null
        pilihanPlayer2 = null
    }

    override fun tampilkanHasil(hasil: String) {
        when (hasil) {
            "1" -> ResultDialogFragment.newInstance("$player1 \n MENANG").show(supportFragmentManager,null)
            "2" -> ResultDialogFragment.newInstance("pemain2 \n MENANG").show(supportFragmentManager,null)
            "3" -> ResultDialogFragment.newInstance("DRAW").show(supportFragmentManager,null)
        }
        Log.d("Player1", "Pemain memilih $pilihanPlayer")
        Log.d("Com", "Computer memilih $pilihanPlayer2")
        Log.d("hasil", "hasilnya $hasil")
        Log.d("hasil", "1= Pemain1 Menang, 2= Com Menang, 3= Draw")
        Toast.makeText(this, "$player1 memilih $pilihanPlayer", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Pemain2 memilih $pilihanPlayer2", Toast.LENGTH_SHORT).show()
    }
}