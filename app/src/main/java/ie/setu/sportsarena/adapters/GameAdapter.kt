package ie.setu.sportsarena.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ie.setu.sportsarena.R

class GameAdapter : ListAdapter<String, GameAdapter.GameViewHolder>(GameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_games, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game)
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val buttonGame: TextView = itemView.findViewById(R.id.button_cricket)

        fun bind(game: String) {
            buttonGame.text = game
            // Handle click event to navigate to VenueFragment
            itemView.setOnClickListener {
                // Access the NavController and navigate to VenueFragment
                itemView.findNavController().navigate(R.id.action_gamesFragment_to_venueFragment)
            }
        }
    }

    class GameDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

}
