package com.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragmentGr : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result_gr, container, false)
        val message = ResultFragmentGrArgs.fromBundle(requireArguments()).messageGr
        val translatedText = view.findViewById<TextView>(R.id.result_fragment_gr_text)
        translatedText.text = message
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragmentGr()
    }
}