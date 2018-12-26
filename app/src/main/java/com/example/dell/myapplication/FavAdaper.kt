/******
 * Adapter for attaching items into recycle view.
 */
package com.example.dell.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FavAdapter(list: List<Jokes> ): RecyclerView.Adapter<FavAdapter.FavViewHolder>() {
    var joke_list=list;
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): FavViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return FavViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return joke_list.size
    }

    override  fun onBindViewHolder(holder: FavViewHolder, position:Int) {
        if(joke_list !=null) {
            var favourites = joke_list.get(position)
            holder.mTxt.setText(" Joke Id: "+favourites.mId.toString()+"\n Joke: "+favourites.joke.toString())
        }
        else
            holder.mTxt.setText("No Text")
    }



    class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mTxt: TextView
        init {
            mTxt = itemView.findViewById(R.id.textView)
        }
    }
}
