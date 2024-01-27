package com.portafolio.test_api_kotlin.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.portafolio.appdivisas.R
import com.portafolio.appdivisas.divisa

class CustomAdapter(
    private val mList: List<ItemsViewModel>,
    var mContext: Context,
    var recyclerView: RecyclerView
) :

    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview, parent, false)

        return ViewHolder(view)
    }


    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }



    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var intent: Intent? = null
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        var activity = mContext as Activity
        //val recyclerView = activity

        /*---------------------------------------------------------------------------------------------------*/
        init {
            itemView.setOnClickListener {

                var mposition = recyclerView.getChildAdapterPosition(ItemView)
                val ItemsViewModel = mList[mposition]
                //Log.d("----------", "mposition: "+getCurrentPosition())
                Log.d("----------", "mposition: "+mposition)
                Log.d("----------", "mposition: " + ItemsViewModel.text)
                intent = Intent(mContext, divisa::class.java)
                intent!!.putExtra("text", ItemsViewModel.text)
                mContext.startActivity(intent)
            }
        }
    }
}