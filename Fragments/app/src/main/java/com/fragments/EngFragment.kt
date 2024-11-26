package com.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class EngFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_eng, container, false)
        val startButton: Button = view.findViewById(R.id.eng_fragment_button_start)
        startButton.setOnClickListener {
             view.findNavController().navigate(R.id.action_startFragment_eng_to_messageFragment)
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = EngFragment()
    }
}