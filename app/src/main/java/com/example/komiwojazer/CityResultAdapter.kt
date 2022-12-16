package com.example.komiwojazer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityResultAdapter(private val list: List<String>) :
    RecyclerView.Adapter<CityViewResultHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewResultHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CityViewResultHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CityViewResultHolder, position: Int) {
        val city: String = list[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = list.size
}

class CityViewResultHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_city_result_row, parent, false)) {

    private var city: TextView? = null

    init {

        city = itemView.findViewById(R.id.cityResult)
    }

    fun bind(c: String) {
        city?.text = c
    }
}