package com.sagr32.twittercounter.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.sagr32.twittercounter.R
import com.sagr32.twittercounter.data.enum.ToastStatus


object Utils {
    fun copyToClipboard(context: Context, copyText: String) {
        val myClipboard = ContextCompat.getSystemService(
            context,
            ClipboardManager::class.java
        ) as ClipboardManager
        val clip = ClipData.newPlainText(context.getString(R.string.copied), copyText)
        myClipboard.setPrimaryClip(clip)
        showCustomToast(context, context.getString(R.string.copied), ToastStatus.SUCCESS)
    }



    fun showCustomToast(context: Context, message: String, status: ToastStatus) {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.toast_layout, null)
        val textView = layout.findViewById<TextView>(R.id.toast_text)
        textView.text = message
        val backgroundDrawable = when (status) {
            ToastStatus.SUCCESS -> AppCompatResources.getDrawable(context, R.drawable.toast_success)
            ToastStatus.ERROR -> AppCompatResources.getDrawable(context, R.drawable.toast_error)
            ToastStatus.WARNING -> AppCompatResources.getDrawable(context, R.drawable.toast_warning)
        }
        layout.background = backgroundDrawable
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

}