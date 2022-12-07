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

class CityAdapter(private val list: List<CitiesViewModel>)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: CitiesViewModel = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_city_row, parent, false)) {

    private var cityTextView: TextView? = null
    private var distanceTextView: TextView? = null


    init {
        cityTextView = itemView.findViewById(R.id.city)
        distanceTextView = itemView.findViewById(R.id.distance)
    }

    fun bind(movie: CitiesViewModel) {
        cityTextView?.text = movie.city
        distanceTextView?.text = movie.distance.toString()
    }

}