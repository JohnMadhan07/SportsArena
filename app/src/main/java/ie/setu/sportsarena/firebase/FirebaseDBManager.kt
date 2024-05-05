package ie.setu.sportsarena.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ie.setu.sportsarena.models.TimeSlot
import ie.setu.sportsarena.models.TimeSlotStore
import timber.log.Timber

object FirebaseDBManager : TimeSlotStore {
    var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    override fun findAll(timeSlotsList: MutableLiveData<List<TimeSlot>>) {
        // Assuming you have a FirebaseUser available somewhere in your app
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        // Reference to the "user-timeSlots" node for the current user
        val userTimeSlotsRef = database.child("user-timeSlots").child(uid)

        // Add a listener to fetch time slots for the current user
        userTimeSlotsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val localList = mutableListOf<TimeSlot>()

                // Iterate through the dataSnapshot to retrieve TimeSlot objects
                snapshot.children.forEach { timeSlotSnapshot ->
                    val timeSlot = timeSlotSnapshot.getValue(TimeSlot::class.java)
                    timeSlot?.let {
                        localList.add(it)
                    }
                }

                // Update the MutableLiveData with the fetched time slots
                timeSlotsList.value = localList
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.i("Firebase TimeSlot error : ${error.message}")
            }
        })
    }


    override fun findById(timeSlotId: String, timeSlot: MutableLiveData<TimeSlot>) {

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

    }

    override fun update(timeSlotId: String, timeSlot: TimeSlot) {

    }
}
