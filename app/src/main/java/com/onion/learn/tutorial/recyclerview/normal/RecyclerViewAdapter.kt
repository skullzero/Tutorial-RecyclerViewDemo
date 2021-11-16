package com.onion.learn.tutorial.recyclerview.normal

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.onion.learn.tutorial.recyclerview.Account
import com.onion.learn.tutorial.recyclerview.R


class RecyclerViewAdapter(private val accounts: List<Account>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = accounts[position].id + "-" + accounts[position].name
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.accountItem)
    }
}