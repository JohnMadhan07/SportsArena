package ie.setu.sportsarena.ui.venues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ie.setu.sportsarena.R
import ie.setu.sportsarena.databinding.FragmentVenuedetailsBinding
import ie.setu.sportsarena.ui.venuedetails.VenueDetailsViewModel

class VenueDetailFragment : Fragment() {

    private lateinit var binding: FragmentVenuedetailsBinding
    private lateinit var viewModel: VenueDetailsViewModel
    private val args by navArgs<VenueDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVenuedetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(VenueDetailsViewModel::class.java)

        // Access the argument passed from the previous fragment
        val venueId = args.venueId.toInt()

        // Fetch venue details using ViewModel
        viewModel.getVenueById(venueId).observe(viewLifecycleOwner, Observer { venue ->
            if (venue != null) {
                // Update UI with venue details
                binding.apply {
                    venueDetailImageView.setImageResource(venue.imageResId)
                    venueDetailTitleTextView.text = venue.name
                    venueDetailDescriptionTextView.text = venue.address
                }
            } else {
                // Handle case when venue is not found
                Toast.makeText(context, "Venue not found!", Toast.LENGTH_SHORT).show()
            }
        })

        // Set up button click listener for booking venue
        binding.bookVenueButton.setOnClickListener {
            // Handle booking action
            findNavController().navigate(R.id.action_venueDetailFragment_to_timeslotFragment)
        }
    }
}

