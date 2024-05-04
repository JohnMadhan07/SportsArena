package ie.setu.sportsarena.ui.timeslot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.setu.sportsarena.firebase.FirebaseDBManager
import ie.setu.sportsarena.models.TimeSlot
import timber.log.Timber

class TimeSlotViewModel : ViewModel() {
    private val _timeSlots: MutableLiveData<List<TimeSlot>> = MutableLiveData()
    val timeSlots: LiveData<List<TimeSlot>> = _timeSlots

    init {
        // Initialize timeSlots LiveData with an empty list
        _timeSlots.value = emptyList()
    }

    fun addTimeSlot(timeSlot: TimeSlot) {
        Timber.d("Before calling FirebaseDBManager.create(timeSlot)")
        try {
            FirebaseDBManager.create(timeSlot)
            // Add the new time slot to the existing list and update the LiveData
            val currentList = _timeSlots.value.orEmpty().toMutableList()
            currentList.add(timeSlot)
            _timeSlots.value = currentList
        } catch (e: IllegalArgumentException) {
            Timber.e(e, "Error adding time slot")
        }
        Timber.d("After calling FirebaseDBManager.create(timeSlot)")
    }
}



