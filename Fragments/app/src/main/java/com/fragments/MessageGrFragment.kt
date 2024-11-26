package com.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class MessageGrFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message_gr, container, false)
        val messageEditText = view.findViewById<EditText>(R.id.message_gr_fragment_edit)
        val translateButton: Button = view.findViewById(R.id.message_gr_fragment_translate_button)
        translateButton.setOnClickListener{
            Log.i(".MainActivity", "GR")
            val messageGr = messageEditText.text.toString()
            val action = MessageGrFragmentDirections
                .actionMessageFragmentGrToResultFragment(messageGr)
            view.findNavController().navigate(action)
//            view.findNavController().navigate(R.id.action_messageFragment_gr_to_resultFragment)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = MessageGrFragment()
    }
}