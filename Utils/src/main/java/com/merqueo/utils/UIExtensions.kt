package com.merqueo.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.jakewharton.picasso.OkHttp3Downloader
import com.merqueo.utils.app.App
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

/**
 * Thw extensions could be used in different controls in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */

private val picasso: Picasso by lazy { Picasso.Builder(App.getAppContext()).downloader(OkHttp3Downloader(
    App.getAppContext())).build() }

/**
 * Extension to load image from url
 * */
fun ImageView.loadImage(imageUrl: String) {
    picasso.load(App.getAppContext().getString(R.string.image_api)  + "/t/p/w500" + imageUrl)
        .placeholder(R.drawable.image_placeholder)
        .into(this)
}

/**
 * Extension to load images from local repository
 * */
fun ImageView.loadLocalImage(resourceId: Int) {
    picasso.load(resourceId)
        .placeholder(R.drawable.image_placeholder)
        .into(this)
}

/**
 * Method to add custom listener when text change in edit text control
 * */
fun EditText.doOnTextChange(textChange: (CharSequence) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {
            textChange.invoke(s)
        }
    })
}

/**
 * Method to return string with currency format to double field
 * */
fun Double.toCurrencyFormat(): String {
    var localIdentifier = Locale.US
    if (Navigation.getInstance.getString(R.string.currencyIdentifier).contains("_")) {
        val locateSplit = Navigation.getInstance.getString(R.string.currencyIdentifier).split("_")
        localIdentifier = Locale(locateSplit[0], locateSplit[1])
    }
    val retorno = NumberFormat.getCurrencyInstance(localIdentifier).format(this)
    return if (Navigation.getInstance.getString(R.string.hasDecimals).contentEquals("true")) retorno else retorno.substringBefore(".")
}

/**
 * Method to return string with currency format to float field
 * */
fun Float.toCurrencyFormat(): String {
    var localIdentifier = Locale.US
    if (Navigation.getInstance.getString(R.string.currencyIdentifier).contains("_")) {
        val locateSplit = Navigation.getInstance.getString(R.string.currencyIdentifier).split("_")
        localIdentifier = Locale(locateSplit[0], locateSplit[1])
    }
    val retorno = NumberFormat.getCurrencyInstance(localIdentifier).format(this)
    return if (Navigation.getInstance.getString(R.string.hasDecimals).contentEquals("true")) retorno else retorno.substringBefore(".")
}

/**
 * Method to return string with currency format to int field
 * */
fun Int.toCurrencyFormat(): String {
    var localIdentifier = Locale.US
    if (Navigation.getInstance.getString(R.string.currencyIdentifier).contains("_")) {
        val locateSplit = Navigation.getInstance.getString(R.string.currencyIdentifier).split("_")
        localIdentifier = Locale(locateSplit[0], locateSplit[1])
    }
    val retorno = NumberFormat.getCurrencyInstance(localIdentifier).format(this)
    return if (Navigation.getInstance.getString(R.string.hasDecimals).contentEquals("true")) retorno else retorno.substringBefore(".")
}