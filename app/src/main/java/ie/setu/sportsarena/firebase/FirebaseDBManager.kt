package ie.setu.sportsarena.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.setu.sportsarena.models.TimeSlot
import ie.setu.sportsarena.models.TimeSlotStore
import timber.log.Timber

object FirebaseDBManager : TimeSlotStore {
    var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    override fun findAll(timeSlotsList: MutableLiveData<List<TimeSlot>>) {
        // Implement the logic to retrieve all time slots from Firebase and update the MutableLiveData object
        // For example:
        // val allTimeSlots = // Logic to fetch time slots from Firebase
        // timeSlotsList.postValue(allTimeSlots)
    }

    override fun findById(timeSlotId: String, timeSlot: MutableLiveData<TimeSlot>) {
        // Implement the logic to find a time slot by its ID from Firebase and update the MutableLiveData object
        // For example:
        // val foundTimeSlot = // Logic to fetch time slot from Firebase by ID
        // timeSlot.postValue(foundTimeSlot)
    }

    override fun create(timeSlot: TimeSlot) {
        Timber.i("Firebase DB Reference : $database")

        // Assuming you have a FirebaseUser available somewhere in your app
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        val key = database.child("timeSlots").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }

        // Update the time slot with the generated key
        timeSlot.uid = key
        val timeSlotValues = timeSlot.toMap()

        // Define the child updates to be applied atomically
        val childAdd = HashMap<String, Any>()
        childAdd["/timeSlots/$key"] = timeSlotValues
        childAdd["/user-timeSlots/$uid/$key"] = timeSlotValues

        // Perform the atomic update
        database.updateChildren(childAdd)
    }


    override fun delete(timeSlotId: String) {
        // Implement the logic to delete a time slot from Firebase by its ID
    }

    override fun update(timeSlotId: String, timeSlot: TimeSlot) {
        // Implement the logic to update a time slot in Firebase by its ID
    }
}
