package ie.setu.sportsarena.models

import androidx.lifecycle.MutableLiveData
import ie.setu.sportsarena.models.TimeSlot

interface TimeSlotStore {
    fun findAll(timeSlotsList: MutableLiveData<List<TimeSlot>>)
    fun findById(timeSlotId: String, timeSlot: MutableLiveData<TimeSlot>)
    fun create(timeSlot: TimeSlot)
    fun delete(timeSlotId: String)
    fun update(timeSlotId: String, timeSlot: TimeSlot)
}