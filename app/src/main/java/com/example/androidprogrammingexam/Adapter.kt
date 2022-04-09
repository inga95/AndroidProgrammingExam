package com.example.androidprogrammingexam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.image_rv_layout.view.*

class Adapter(val context: Context, val imageInfo:ImageInfo):
    RecyclerView.Adapter<Adapter.ViewHolderAdapter>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapter {
        val view = LayoutInflater.from(context).inflate(R.layout.image_rv_layout, parent, false)
        return ViewHolderAdapter(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAdapter, position: Int) {
        //Glide er et bibliotek han bruker for å få bilder. Glide må altså byttes ut med bildet fra emulatoren våres

        //Glide.with(context.load(imageInfo.get(position).thumbnail_link).into(holder.rec))

    }

    class ViewHolderAdapter(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val imageResults: ImageView = itemView.findViewById(R.id.recyclerView)
    }

    override fun getItemCount(): Int {
        return imageInfo.size
    }

}
