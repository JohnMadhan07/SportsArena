package ie.setu.sportsarena.ui.timeslot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.sportsarena.R
import ie.setu.sportsarena.adapters.TimeSlotAdapter
import ie.setu.sportsarena.models.TimeSlot

class TimeSlotFragment : Fragment() {

    private lateinit var timeSlotViewModel: TimeSlotViewModel
    private lateinit var timeSlotAdapter: TimeSlotAdapter
    private lateinit var timeSlotsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_timeslot, container, false)
        timeSlotsRecyclerView = view.findViewById(R.id.timeSlotsRecyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        // Example: Add a time slot
        addTimeSlot("9:00 AM", "10:00 AM")
    }

    private fun setupRecyclerView() {
        // Initialize ViewModel
        timeSlotViewModel = ViewModelProvider(this).get(TimeSlotViewModel::class.java)

        // Initialize Adapter with an empty list initially
        timeSlotAdapter = TimeSlotAdapter(emptyList())

        // Set up RecyclerView
        timeSlotsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        timeSlotsRecyclerView.adapter = timeSlotAdapter

        // Observe time slots from ViewModel
        timeSlotViewModel.timeSlots.observe(viewLifecycleOwner) { timeSlots ->
            // Update RecyclerView with time slots
            timeSlotAdapter.submitList(timeSlots)
        }
    }

    // Function to add a new time slot
    private fun addTimeSlot(startTime: String, endTime: String) {
        val newTimeSlot = TimeSlot(startTime, endTime)
        timeSlotViewModel.addTimeSlot(newTimeSlot)
    }
}
