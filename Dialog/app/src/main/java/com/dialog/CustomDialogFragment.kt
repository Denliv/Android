package com.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Typeface
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val textView = TextView(activity)
        textView.text = "!!!!!!!!!!!!!!!!!!!!!!!!!!ALERT!!!!!!!!!!!!!!!!!!!!!!!!!!"
        textView.textSize = 24F
        textView.setTypeface(null, Typeface.BOLD)
        textView.gravity = Gravity.CENTER
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setCustomTitle(textView)
                .setSingleChoiceItems(arrayOf("Male", "Female"), 0) { dialog, item -> }
                .setPositiveButton("8/2(2+2)=16") {
                        dialog, id ->
                    dialog.dismiss()
                    (activity as MainActivity?)!!.positiveClicked()
                }
                .setNeutralButton("8/2(2+2)=I don't know") {
                        dialog, id ->
                    dialog.dismiss()
                    (activity as MainActivity?)!!.neutralClicked()
                }
                .setNegativeButton("8/2(2+2)=1") {
                        dialog, id ->
                    dialog.dismiss()
                    (activity as MainActivity?)!!.negativeClicked()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}