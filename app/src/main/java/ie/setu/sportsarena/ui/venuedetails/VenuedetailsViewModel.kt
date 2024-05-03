package ie.setu.sportsarena.ui.venuedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import ie.setu.sportsarena.models.Venue

class VenueDetailsViewModel : ViewModel() {
    private val _venues = MutableLiveData<List<Venue>>()
    // Method to get the venue details by ID
    fun getVenueById(venueId: Int): LiveData<Venue?> {
        return _venues.map { venues ->
            venues.find { it.id == venueId }
        }
    }
}


