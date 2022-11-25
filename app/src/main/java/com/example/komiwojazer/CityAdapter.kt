package com.example.komiwojazer



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

data class CitiesViewModel(val city: String, val distance: Int) {
}
class CustomAdapter(private val mList: List<CitiesViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.cityView.text = ItemsViewModel.city
        holder.distanceView.text = ItemsViewModel.distance.toString()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cityView: TextView = itemView.findViewById(R.id.city)
        val distanceView: TextView = itemView.findViewById(R.id.distance)

    }
}