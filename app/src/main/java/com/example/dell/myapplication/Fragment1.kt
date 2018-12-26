/*
Show a button in fragment after click show list of jokes
 */
package com.example.dell.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Fragment1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        var view: View= inflater.inflate(R.layout.activity_fragment1, container, false);
        var button=view.findViewById<Button>(R.id.button);
        button.setOnClickListener{view ->
            val intent = Intent(view.context,JokesList::class.java)
            intent.putExtra("category","explicit")
            startActivity(intent)}
        return view;    }
}
