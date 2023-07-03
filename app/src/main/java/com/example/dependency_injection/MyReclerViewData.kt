package com.example.dependency_injection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MyReclerViewData @Inject constructor(var myList: ArrayList<LoginResponse>) :
    RecyclerView.Adapter<MyReclerViewData.MyViewoldwer>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewoldwer {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recy_viiew, parent, false)
        return MyViewoldwer(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewoldwer, position: Int) {
        holder.textViewName.text = myList.get(position).title
        holder.textViewDescription.text = "Album id is : " + myList.get(position).albumId.toString()
        Picasso.get().load(myList.get(position).url).placeholder(R.drawable.ic_launcher_background)
            .into(holder.imgLogo)

    }

    inner class MyViewoldwer(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.tv_first)
        val textViewDescription: TextView = itemView.findViewById(R.id.tv_second)
        val imgLogo: ImageView = itemView.findViewById(R.id.img_logo)


    }
}