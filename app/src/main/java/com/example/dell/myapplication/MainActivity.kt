/*********
 * This is the home activity. in this activity took tha categories from api and after that show fragments with the category name.
 */
package com.example.dell.myapplication

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

var data11= ArrayList<String>();
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        this.setTitle("Home")
        /****
         * To get category data from api
         */
        class openApi() : AsyncTask<Unit, Unit, String>() {
            var pdialog = ProgressDialog(this@MainActivity)
            override fun onPreExecute() {
                super.onPreExecute()
                pdialog.setMessage("Getting Content......")
                pdialog.setCancelable(false)
                pdialog.setCanceledOnTouchOutside(false)
                pdialog.show();
            }

            override fun doInBackground(vararg params: Unit?): String {
                var data=""
                try {
                    val url: URL = URL("http://api.icndb.com/categories");
                    var urlConnection = url.openConnection() as HttpURLConnection;
                    urlConnection.connect();
                    data = urlConnection.inputStream.bufferedReader().readText();
                }
                    catch(e:Exception)
                    {
                    }
                return data;
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                var data=result
                //if data is not fetched from api
                if (data.toString()=="")
                {
                    pdialog.dismiss();
                   val dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle(R.string.error_1_no_connection)
                    dialog.setMessage(R.string.error_1_no_connection_desc)
                    dialog.setCancelable(false)
                    dialog.setPositiveButton(android.R.string.ok) { dialog, which ->
                        dialog.dismiss()
                        this@MainActivity.finish()
                    }
                    dialog.setNegativeButton("Retry") { dialog, which ->
                        dialog.dismiss()
                        openApi().execute();
                    }
                    var adialog=dialog.create()
                    adialog.show();
                }
                else{
                var rootjson = JSONObject(result);
                var arrayjson = rootjson.getString("value");
                var mjsonarray = JSONArray(arrayjson);
                for (i in 0 until mjsonarray.length()) {
                    data11.add(mjsonarray.get(i).toString());
                }
                    pdialog.dismiss();
                    //adding adapters
                val viewPager = findViewById<ViewPager>(R.id.viewPager)
                if (viewPager != null) {
                    val adapter = FragmnetAdapter(supportFragmentManager, data11)
                    viewPager.adapter = adapter
                }
            }}
        }
            openApi().execute()
    }
}
