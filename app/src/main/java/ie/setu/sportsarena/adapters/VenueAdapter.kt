package ie.setu.sportsarena.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ie.setu.sportsarena.R
import ie.setu.sportsarena.models.Venue

class VenueAdapter : RecyclerView.Adapter<VenueAdapter.ViewHolder>() {

    private var venues: List<Venue> = emptyList()
    private var listener: OnItemClickListener? = null

    // Define a click listener interface
    interface OnItemClickListener {
        fun onItemClick(venue: Venue)
    }

    // Setter method to set the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.venue_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val venue = venues[position]
        holder.bind(venue)
    }

    override fun getItemCount(): Int {
        return venues.size
    }

    fun submitList(newList: List<Venue>) {
        venues = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            // Set click listener for the item view
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(venues[position])
                }
            }
        }

        fun bind(venue: Venue) {
            // Bind venue data to the layout views
            itemView.findViewById<ImageView>(R.id.venueImageView).setImageResource(venue.imageResId)
            itemView.findViewById<TextView>(R.id.venueAddressTextView).text = venue.address
        }
    }
}

