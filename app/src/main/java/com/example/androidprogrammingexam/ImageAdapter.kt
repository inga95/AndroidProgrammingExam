package com.example.androidprogrammingexam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.image_rv_layout.view.*

class ImageAdapter(val context: Context?, private val searchImages: ArrayList<ImageApi>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.image_rv_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Binding View here

        //Picasso.get().load(dogsImages[position].message).into(holder.itemView.dogImage)
    }

    override fun getItemCount(): Int {
        return searchImages.size
    }


    class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!), View.OnClickListener{
        override fun onClick(v: View?){


            //Onclick function here

        }

        init {
            itemView.setOnClickListener(this)
        }

        val searchImage = itemView.resultImages!!
    }
}