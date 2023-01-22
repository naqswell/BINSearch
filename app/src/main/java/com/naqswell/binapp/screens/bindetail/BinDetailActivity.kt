package com.naqswell.binapp.screens.bindetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.naqswell.binapp.networking.entities.BinData

class BinDetailActivity : AppCompatActivity(), BinDetailViewMvc.Listener {

    private lateinit var viewMvc: BinDetailViewMvc
    private var binDetails: BinData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binDetails = intent.getParcelableExtra("BinData")
        viewMvc = BinDetailViewMvc(layoutInflater, binDetails)

        setContentView(viewMvc.root)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onBankUrlClicked(url: String) {
        val formattedUrl = "https://$url"
        if (URLUtil.isValidUrl(formattedUrl)) {
            val internetIntent = Intent(Intent.ACTION_VIEW)
            internetIntent.data = Uri.parse(formattedUrl)
            startActivity(internetIntent)
        } else {
            Snackbar.make(
                viewMvc.root,
                "Wrong URL",
                Snackbar.LENGTH_SHORT
            )
                .setAction("Ok") {}
                .show()
        }
    }

    override fun onCoordinatesClicked(lat: String, lng: String) {
        if ((lat.toIntOrNull() != null) and (lng.toIntOrNull() != null)) {
            val gmmIntentUri = Uri.parse("geo:$lat,$lng")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            }
        }
    }

    override fun onPhoneClicked(phone: String) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(phone)) {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phone")
            startActivity(dialIntent)
        }
    }

}