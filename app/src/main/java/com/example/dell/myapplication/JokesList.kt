/*
Show list of jokes inn recycle view on the basis of category chosed in home screen along with livedata model
 */
package com.example.dell.myapplication
import android.app.AlertDialog
import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_jokes_list.*
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

private var joke_list :MutableList<Jokes> = ArrayList<Jokes>();
class JokesList : AppCompatActivity() {
    var fadapter = FavAdapter(joke_list)
    var model = liveviewModel()
    var category = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes_list)
        setSupportActionBar(toolbar)
        category = intent.getStringExtra("category")
        joke_list = loadUserNames()
        if(joke_list.isEmpty())
        {
            val dialog = AlertDialog.Builder(this@JokesList)
            dialog.setTitle(R.string.error_1_no_connection)
            dialog.setMessage(R.string.error_1_no_connection_desc)
            dialog.setCancelable(false)
            dialog.setPositiveButton(android.R.string.ok) { dialog, which ->
                dialog.dismiss()
                this@JokesList.finish()
            }
            var adialog = dialog.create()
            adialog.show();
        }else {
            fadapter = FavAdapter(joke_list)
            val listView = findViewById<RecyclerView>(R.id.recyclerView);
            listView.setLayoutManager(LinearLayoutManager(this));
            //code for livedata model and fetching of live data after changes
            model = ViewModelProviders.of(this).get(liveviewModel::class.java)
            val favsObserver = Observer<List<Jokes>> {
                fun onChanged(updatedList: MutableList<Jokes>) {
                    if (joke_list == null) {
                        joke_list = updatedList
                        fadapter = FavAdapter(joke_list)
                        listView.adapter = fadapter
                    } else {
                        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                            override fun getOldListSize(): Int {
                                return joke_list.count()
                            }

                            override fun getNewListSize(): Int {
                                return updatedList.count()
                            }

                            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                                return joke_list.get(oldItemPosition).mId === updatedList[newItemPosition].mId
                            }

                            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                                val oldFav = joke_list.get(oldItemPosition)
                                val newFav = updatedList[newItemPosition]
                                return oldFav.equals(newFav)
                            }
                        })
                        result.dispatchUpdatesTo(fadapter);
                        joke_list = updatedList
                    }
                }
            }
            listView.adapter = fadapter
            model.getFavs().observe(this, favsObserver);
        }
    }

    fun loadUserNames(): MutableList<Jokes> {
        var joke_list: MutableList<Jokes> = ArrayList<Jokes>();
        val data = openApi1(category, this@JokesList).execute().get();
        try {
            var rootjson = JSONObject(data);
            var arrayjson = rootjson.getString("value");
            var mjsonarray = JSONArray(arrayjson);
            for (i in 0 until mjsonarray.length()) {
                var json = JSONObject(mjsonarray.get(i).toString())
                joke_list.add(Jokes(json.getLong("id"), json.getString("joke")))
            }
        }
        catch(e:Exception)
        {

        }
        return joke_list
    }

    /*****
     * getting list of jokes from api on the basis of category
     */
    class openApi1(category: String, ctxt:Context) : AsyncTask<Unit, Unit, String>() {
        var categ = category;
        var context=ctxt
        var pdialog=ProgressDialog(context)
        override fun onPreExecute() {
            super.onPreExecute()
            pdialog.setMessage("Getting Content......")
            pdialog.setCancelable(false)
            pdialog.setCanceledOnTouchOutside(false)
            pdialog.show();
        }

        override fun doInBackground(vararg params: Unit?): String {
           var data=""
            try{ val url: URL = URL("http://api.icndb.com/jokes/random/10?limitTo=" + categ);
            var urlConnection = url.openConnection() as HttpURLConnection;
            urlConnection.connect();
            data = urlConnection.inputStream.bufferedReader().readText();}
           catch(e:Exception)
           {

           }
            pdialog.dismiss();
            return data;
        }
    }
}