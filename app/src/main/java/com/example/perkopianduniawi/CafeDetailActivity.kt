package com.example.perkopianduniawi

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuCompat
import com.example.perkopianduniawi.databinding.ActivityCafeDetailBinding
import kotlin.properties.Delegates

class CafeDetailActivity : AppCompatActivity() {
    private lateinit var layoutBind: ActivityCafeDetailBinding

    private lateinit var cafeName: String
    private lateinit var cafeDesc: String
    private lateinit var cafeRating: String
    private lateinit var cafeOpen: String
    private lateinit var cafeClose: String
    private var cafeStatus by Delegates.notNull<Boolean>()
    private lateinit var cafeStatusText: String
    private lateinit var cafeRegion: String
    private lateinit var cafeLocation: String
    private lateinit var cafeLink: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBind = ActivityCafeDetailBinding.inflate(layoutInflater)
        val view = layoutBind.root
        setContentView(view)

        cafeName = intent.getStringExtra("cafeName").toString()
        cafeDesc = intent.getStringExtra("cafeDescription").toString()
        cafeRating = intent.getDoubleExtra("cafeRating", 0.0).toString()
        cafeOpen = intent.getStringExtra("cafeOpen").toString()
        cafeClose = intent.getStringExtra("cafeClose").toString()
        cafeLink = intent.getStringExtra("cafeLink").toString()
        cafeLocation = intent.getStringExtra("cafeLocation").toString()
        cafeStatus = intent.getBooleanExtra("cafeStatus", true)
        cafeStatusText = if (cafeStatus) "Open" else "Close"
        cafeRegion = intent.getStringExtra("cafeRegion").toString()

        layoutBind.cafeNameText.text = cafeName
        layoutBind.cafeRatingText.text = "$cafeRating / 5.0"

        layoutBind.cardItem.cafeName.text = cafeName
        layoutBind.cardItem.cafeRating.text = "$cafeRating / 5.0"
        layoutBind.cardItem.cafeRegion.text = cafeRegion
        layoutBind.cafeDescription.text = cafeDesc
        layoutBind.cafeLocation.text = cafeLocation
        layoutBind.cardItem.cafeClock.text = if(cafeStatus) "$cafeOpen s/d $cafeClose" else "CLOSED!"

        // set text danger if is closed
        if(!cafeStatus) layoutBind.cardItem.cafeClock

        actionBar(cafeName)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        MenuCompat.setGroupDividerEnabled(menu, true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)

                true
            }
            R.id.mapOpen -> {
                val url = Uri.parse(this.cafeLink)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = url
                val chooser = Intent.createChooser(intent, "Open Map")
                startActivity(chooser)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Enable back button and set title
     *
     * @author Cak Adi cakadi190@gmail.com
     * @return Unit
     * @param title Set the title of ActionBar
     */
    private fun actionBar(title: String) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        supportActionBar?.title = "Detail of \"$title\""
    }
}