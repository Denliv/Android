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

class MessageEngFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message_eng, container, false)
        val messageEditText = view.findViewById<EditText>(R.id.message_eng_fragment_edit)
        val translateButton: Button = view.findViewById(R.id.message_eng_fragment_translate_button)
        translateButton.setOnClickListener{
            Log.i(".MainActivity", "ENG")
            val messageEng = messageEditText.text.toString()
            val action = MessageEngFragmentDirections
                .actionMessageFragmentEngToResultFragment(messageEng)
            view.findNavController().navigate(action)
//            view.findNavController().navigate(R.id.action_messageFragment_eng_to_resultFragment)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = MessageEngFragment()
    }
}