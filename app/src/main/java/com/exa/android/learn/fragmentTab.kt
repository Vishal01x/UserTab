package com.exa.android.learn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class fragmentTab : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val text = it.getString(ARG_TEXT)
            val tabText = view.findViewById<TextView>(R.id.frag_text)
            tabText.text = text
        }
    }


    // Passing data between fragments using bundle
    // It can also be achived by making bundle and shared form the class from where the fragment is called
    // then no need to create this newInstance fun
    companion object {

        private const val ARG_TEXT = "text"

        fun newInstance(text: String) : fragmentTab{
            val fragment = fragmentTab()
            val args = Bundle()
            args.putString(ARG_TEXT,text)
            fragment.arguments = args
            return fragment
        }
    }
}