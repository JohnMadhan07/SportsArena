package ie.setu.sportsarena.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ie.setu.sportsarena.R
import ie.setu.sportsarena.models.TimeSlot

class TimeSlotAdapter(private var timeSlots: List<TimeSlot>) : RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.time_slot_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timeSlot = timeSlots[position]
        holder.bind(timeSlot)
    }

    override fun getItemCount(): Int {
        return timeSlots.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeSlotTextView: TextView = itemView.findViewById(R.id.timeSlotTextView)

        init {
            itemView.setOnClickListener {
                val timeSlot = timeSlots[adapterPosition]
                timeSlot.isSelected = !timeSlot.isSelected // Toggle selection state
                notifyDataSetChanged() // Notify adapter of data change
            }
        }

        fun bind(timeSlot: TimeSlot) {
            // Bind time slot data to the layout views
            val timeSlotText = "${timeSlot.startTime} - ${timeSlot.endTime}"
            timeSlotTextView.text = timeSlotText

            // Update UI based on selection state
            if (timeSlot.isSelected) {
                // Highlight the selected time slot
                timeSlotTextView.setBackgroundResource(R.drawable.time_slot_bg_highlighted)
            } else {
                // Reset background when not selected
                timeSlotTextView.setBackgroundResource(0)
            }
        }
    }

    fun submitList(newList: List<TimeSlot>) {
        timeSlots = newList
        notifyDataSetChanged() // Notify RecyclerView of the data change
    }

}

