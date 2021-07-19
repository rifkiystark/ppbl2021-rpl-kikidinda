package com.kikidinda.hitrash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(val callback : (String) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    var users = listOf<User>()
    fun updateUsersData(users : List<User>){
        this.users = users
        notifyDataSetChanged()
    }
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: User, callback : (String) -> Unit){
            view.tvName.text = user.name
            view.tvPoin.text = "Poin : ${user.poin}"
            view.tvAddress.text = "Alamat : RT ${user.rt}/ RW ${user.rw}"

            if(!user.warung){
                view.ivMerchant.visibility = View.INVISIBLE

                view.setOnClickListener {
                    callback(user.id!!)
                }
            } else {
                view.ivMerchant.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], callback)
    }
}