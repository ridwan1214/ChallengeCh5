package com.ridwan1214.challengech5

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import kotlinx.android.parcel.Parcelize

class LandingPagesActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: ImageView

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_pages)

        viewPager = getView(R.id.pager)
        btnNext = getView(R.id.ivRight)

        val dotsIndicator = getView<SpringDotsIndicator>(R.id.spring_dots_indicator)
        val fragmentlandingPage3 = LandingPage3()
        var msg = ""
        fragmentlandingPage3.listener.observe(this, {
            msg = it
        })
        val data = listOf(LandingPage1(), LandingPage2(), fragmentlandingPage3)
        val adapter = MainAdapter(this, data)
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)

        btnNext.setOnClickListener {
            it.backgroundTintList = ContextCompat.getColorStateList(this, R.color.blue_bt_on)
            val pemain1 = Pemain(msg)
            val intent = Intent(this, MenuActivity()::class.java)
            intent.putExtra("Player", pemain1)
            startActivity(intent)
        }

    }

    class MainAdapter(fa: FragmentActivity, private val data: List<Fragment>) :
        FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = data.size
        override fun createFragment(position: Int): Fragment = data[position]
    }
}

fun <T : View> AppCompatActivity.getView(id: Int) = this.findViewById<T>(id)

@Parcelize
data class Pemain(var nama: String) : Parcelable

