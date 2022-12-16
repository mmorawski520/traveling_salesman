package com.example.komiwojazer



import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CityAdapter(private val list: List<CitiesViewModel>)
    : RecyclerView.Adapter<CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CityViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city: CitiesViewModel = list[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = list.size
}

class CityViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_city_row, parent, false)) {

    private var cityTextView: TextView? = null
    private var cityDestinationTextView: TextView?= null
    private var toTextView:TextView? = null
    private var distanceTextView: TextView? = null

    init {
        cityTextView = itemView.findViewById(R.id.city)
        toTextView = itemView.findViewById(R.id.textViewTo)
        cityDestinationTextView = itemView.findViewById(R.id.cityDestination)
        distanceTextView = itemView.findViewById(R.id.distance)
    }

    fun bind(movie: CitiesViewModel) {
        cityTextView?.text = movie.city
        cityDestinationTextView?.text = movie.city2
        distanceTextView?.text = movie.distance.toString()
    }
}