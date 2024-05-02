package ie.setu.sportsarena.ui.venues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.setu.sportsarena.R
import ie.setu.sportsarena.models.Venue

class VenueViewModel : ViewModel() {
    private val _venues = MutableLiveData<List<Venue>>()
    val venues: LiveData<List<Venue>> = _venues

    init {
        // Example data, replace with your actual data source
        val venueList = listOf(
            Venue(1, "Venue 1", "Address 1", R.drawable.venue_image_1),
            Venue(2, "Venue 2", "Address 2", R.drawable.venue_image_2),
            Venue(3, "Venue 3", "Address 3", R.drawable.venue_image_1)
        )
        _venues.value = venueList
    }
}
