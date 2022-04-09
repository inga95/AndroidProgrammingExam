package com.example.androidprogrammingexam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.image_rv_layout.view.*
import java.lang.System.load

class Adapter(val context: Context, val imageInfo:ImageInfo):
    RecyclerView.Adapter<Adapter.ViewHolderAdapter>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapter {
        val view = LayoutInflater.from(context).inflate(R.layout.image_rv_layout, parent, false)
        return ViewHolderAdapter(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAdapter, position: Int) {
        //Glide er et bibliotek som brukes for å få bilder. Linje 24 må byttes ut med bildet ->
        //man har på emulator som sendes som post request med knappen upload image

        //Glide.with(context.load(imageInfo.get(position).thumbnail_link).into(holder.imageResults))
       


    }

    class ViewHolderAdapter(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val imageResults: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun getItemCount(): Int {
        return imageInfo.size
    }

}
