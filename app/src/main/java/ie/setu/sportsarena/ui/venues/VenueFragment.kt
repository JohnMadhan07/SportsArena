package ie.setu.sportsarena.ui.venues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.sportsarena.R
import ie.setu.sportsarena.adapters.VenueAdapter
import ie.setu.sportsarena.models.Venue

class VenueFragment : Fragment() {
    private lateinit var venueRecyclerView: RecyclerView
    private lateinit var venueAdapter: VenueAdapter
    private lateinit var venueViewModel: VenueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_venue, container, false)
        venueRecyclerView = view.findViewById(R.id.venueRecyclerView)
        setupViewModel()
        setupRecyclerView()
        return view
    }

    private fun setupViewModel() {
        venueViewModel = ViewModelProvider(this).get(VenueViewModel::class.java)
        venueViewModel.venues.observe(viewLifecycleOwner, Observer { venues ->
            venueAdapter.submitList(venues)
        })
    }

    private fun setupRecyclerView() {
        venueAdapter = VenueAdapter()
        venueRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        venueRecyclerView.adapter = venueAdapter
    }
}
