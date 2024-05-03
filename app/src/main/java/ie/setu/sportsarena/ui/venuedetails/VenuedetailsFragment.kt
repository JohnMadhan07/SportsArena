package ie.setu.sportsarena.ui.venues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ie.setu.sportsarena.databinding.FragmentVenuedetailsBinding
import ie.setu.sportsarena.models.Venue
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
        val view = binding.root

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(VenueDetailsViewModel::class.java)

        // Access the argument passed from the previous fragment
        val venueId =args.venueId.toInt()

        // Fetch venue details using ViewModel
        viewModel.getVenueById(venueId).observe(viewLifecycleOwner, Observer { venue ->
            if (venue != null) {
                // Update UI with venue details
                binding.apply {
                    venueDetailImageView.setImageResource(venue.imageResId)
                    venueDetailTitleTextView.text = venue.name
                    venueDetailDescriptionTextView.text = venue.address
                    // Set up button click listener for booking venue
                    bookVenueButton.setOnClickListener {
                        // Handle booking action
                        Toast.makeText(context, "Booking venue: ${venue.name}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Handle case when venue is not found
                Toast.makeText(context, "Venue not found!", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
}

