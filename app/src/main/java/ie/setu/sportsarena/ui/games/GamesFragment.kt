package ie.setu.sportsarena.ui.games
import GameAdapter
import GamesViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.sportsarena.R

class GamesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    private val viewModel: GamesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_games, container, false)

        // Initialize RecyclerView
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize GameAdapter
        gameAdapter = GameAdapter()
        recyclerView.adapter = gameAdapter

        // Observe gamesList LiveData and update the adapter when the list changes
        viewModel.gamesList.observe(viewLifecycleOwner) { games ->
            gameAdapter.submitList(games)
        }

        return root
    }
}
