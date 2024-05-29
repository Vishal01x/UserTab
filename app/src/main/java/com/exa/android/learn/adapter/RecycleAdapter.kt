package com.exa.android.learn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.exa.android.learn.DataTypes.User
import com.exa.android.learn.DataTypes.UserX
import com.exa.android.learn.R

class RecycleAdapter(var data: List<UserX>) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    lateinit var myListner : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(d : List<UserX>, position: Int)
    }

    fun onRecycleViewClick(listner : onItemClickListner){
        myListner = listner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapter.ViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view, myListner)
    }

    override fun onBindViewHolder(holder: RecycleAdapter.ViewHolder, position: Int) {
        val user_name = data[position].firstName + data[position].lastName
        holder.name.text = user_name

        holder.des.text = (data[position].address).toString()

        holder.img.load(data[position].image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val itemView : View, listner: onItemClickListner) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
        val des  = itemView.findViewById<TextView>(R.id.des)
        val img = itemView.findViewById<ImageView>(R.id.image)

        init {
            itemView.setOnClickListener {
                listner.onItemClick(data , adapterPosition)
            }
        }
    }

   fun updateData(newData : List<UserX>){
       data = newData
       notifyDataSetChanged()
   }

}