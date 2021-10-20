package com.example.api_recyclerview

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycle = findViewById<View>(R.id.rv) as RecyclerView
        val names=ArrayList<ActivityDetails>()
        recycle.adapter = RVAdapter(this,names)
        recycle.layoutManager = LinearLayoutManager(this)
        val apiInterface = APIClinent().GetClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if (apiInterface != null) {
            apiInterface.Getname()?.enqueue(object : Callback<ArrayList<ActivityDetails>> {
                override fun onResponse(
                    call: Call<ArrayList<ActivityDetails>>,
                    response: Response<ArrayList<ActivityDetails>>
                ) {
                    progressDialog.dismiss()
                    for(name in response.body()!!){
                        names.add(name)
                    }
                    recycle.adapter?.notifyDataSetChanged()
                }
                override fun onFailure(call: Call<ArrayList<ActivityDetails>>, t: Throwable) {
                    //  onResult(null)
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, ""+t.message, Toast.LENGTH_SHORT).show();
                }
            })
        }
    }
}