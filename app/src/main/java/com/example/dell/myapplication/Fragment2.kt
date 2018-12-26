/*
Show a button in fragment after click show list of jokes
 */
package com.example.dell.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.*

import kotlinx.android.synthetic.main.activity_fragment2.*

class Fragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        var view: View= inflater.inflate(R.layout.activity_fragment2, container, false);
        var button=view.findViewById<Button>(R.id.button2);
        button.setOnClickListener{view ->
        val intent = Intent(view.context,JokesList::class.java);
            intent.putExtra("category","nerdy")
        startActivity(intent)}
        return view;
    }
}
