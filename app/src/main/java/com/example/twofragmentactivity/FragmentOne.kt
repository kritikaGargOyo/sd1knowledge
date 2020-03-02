package com.example.twofragmentactivity

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager

class FragmentOne :AppCompatDialogFragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview  = inflater.inflate(R.layout.fragment_one,container,false)
        var button = rootview.findViewById<Button>(R.id.button) as Button


        button.setOnClickListener {
            val fragmentManager  = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val textbox : String = rootview.findViewById<EditText>(R.id.text).text.toString()
            val fragment = FragmentTwo()
            fragmentTransaction?.replace(R.id.container, fragment)
            fragmentTransaction?.commit()
        }
        return rootview as View

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}
