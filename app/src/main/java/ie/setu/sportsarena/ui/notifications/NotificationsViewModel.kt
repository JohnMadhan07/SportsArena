package ie.setu.sportsarena.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.setu.sportsarena.firebase.FirebaseDBManager
import ie.setu.sportsarena.models.TimeSlot

class NotificationsViewModel : ViewModel() {

    // LiveData to hold the list of time slots
    private val _timeSlots = MutableLiveData<List<TimeSlot>>()
    val timeSlots: LiveData<List<TimeSlot>> = _timeSlots

    // Function to fetch time slots
    fun fetchTimeSlots() {
        FirebaseDBManager.findAll(_timeSlots)
    }

    // Function to handle any cleanup when the ViewModel is no longer used
    override fun onCleared() {
        super.onCleared()
        // If needed, remove any observers or listeners here
    }
}
