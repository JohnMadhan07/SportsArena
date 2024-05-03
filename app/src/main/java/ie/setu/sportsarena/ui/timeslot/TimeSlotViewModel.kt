package ie.setu.sportsarena.ui.timeslot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.setu.sportsarena.models.TimeSlot


class TimeSlotViewModel : ViewModel() {
    private val _timeSlots = MutableLiveData<List<TimeSlot>>()

    // Initialize some example time slots
    init {
        val exampleTimeSlots = listOf(
            TimeSlot("9:00 AM", "10:00 AM"),
            TimeSlot("10:00 AM", "11:00 AM"),
            TimeSlot("11:00 AM", "12:00 PM"),
            TimeSlot("12:00 AM", "13:00 PM"),
            TimeSlot("13:00 AM", "14:00 PM"),
            TimeSlot("14:00 AM", "15:00 PM"),
            TimeSlot("15:00 AM", "16:00 PM"),
            TimeSlot("16:00 AM", "17:00 PM")
        )
        _timeSlots.value = exampleTimeSlots
    }

    // Expose LiveData to observe time slots
    val timeSlots: LiveData<List<TimeSlot>>
        get() = _timeSlots
}
