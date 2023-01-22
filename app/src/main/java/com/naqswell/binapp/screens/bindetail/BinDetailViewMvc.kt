package com.naqswell.binapp.screens.bindetail

import android.content.Context
import android.view.LayoutInflater
import com.naqswell.binapp.databinding.ActivityBinDetailsBinding
import com.naqswell.binapp.networking.entities.BinData
import com.naqswell.binapp.screens.common.BaseViewMvc

const val EMPTY = "–––"

class BinDetailViewMvc(
    layoutInflater: LayoutInflater,
    binDetails: BinData?
) :
    BaseViewMvc<BinDetailViewMvc.Listener>(layoutInflater) {

    interface Listener {
        fun onBankUrlClicked(url: String)
        fun onCoordinatesClicked(lat: String, lng: String)
        fun onPhoneClicked(phone: String)
    }

    private val binding = ActivityBinDetailsBinding.inflate(layoutInflater)
    val root = binding.root
    private val context: Context = root.context


    init {
        with(binding) {

            binDetails?.let {
                lengthData.text = getEmptyIfNull(it.number?.length.toString())
                luhnData.text = getEmptyIfNull(it.number?.luhn.toString())
                schemeData.text = getEmptyIfNull(it.scheme.toString())
                typeData.text = getEmptyIfNull(it.type.toString())
                brandData.text = getEmptyIfNull(it.brand.toString())
                prepaidData.text = getEmptyIfNull(it.prepaid.toString())
                numericData.text = getEmptyIfNull(it.country?.numeric.toString())
                alpha2Data.text = getEmptyIfNull(it.country?.alpha2.toString())
                countryNameData.text = getEmptyIfNull(it.country?.name.toString())
                emojiData.text = getEmptyIfNull(it.country?.emoji.toString())
                currencyData.text = getEmptyIfNull(it.country?.currency.toString())
                latitudeData.text = getEmptyIfNull(it.country?.latitude.toString())
                longitudeData.text = getEmptyIfNull(it.country?.longitude.toString())
                bankNameData.text = getEmptyIfNull(it.bank?.name.toString())
                bankUrlData.text = getEmptyIfNull(it.bank?.url.toString())
                bankPhoneData.text = getEmptyIfNull(it.bank?.phone.toString())
                bankCityData.text = getEmptyIfNull(it.bank?.city.toString())

                bankUrlData.setOnClickListener {
                    for (listener in listeners) {
                        listener.onBankUrlClicked(bankUrlData.text.toString())
                    }
                }

                latitudeData.setOnClickListener {
                    for (listener in listeners) {
                        listener.onCoordinatesClicked(
                            latitudeData.text.toString(),
                            longitudeData.text.toString()
                        )
                    }
                }

                longitudeData.setOnClickListener {
                    for (listener in listeners) {
                        listener.onCoordinatesClicked(
                            latitudeData.text.toString(),
                            longitudeData.text.toString()
                        )
                    }
                }

                bankPhoneData.setOnClickListener {
                    for (listener in listeners) {
                        listener.onPhoneClicked(bankPhoneData.text.toString())
                    }
                }
            }
        }
    }

    private fun getEmptyIfNull(string: String): String = if (string == "null") EMPTY else string
}