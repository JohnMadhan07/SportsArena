package ie.setu.sportsarena.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ie.setu.sportsarena.R
import ie.setu.sportsarena.databinding.FragmentGamesBinding

class GamesFragment : Fragment() {

    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        val view = binding.root

        setupButtons()

        return view
    }

    private fun setupButtons() {
        binding.buttonCricket.setOnClickListener {
            findNavController().navigate(R.id.action_gamesFragment_to_venueFragment)
        }

        binding.buttonBadminton.setOnClickListener {
            // Add navigation action for Badminton button
        }

        binding.buttonFootball.setOnClickListener {
            // Add navigation action for Football button
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
