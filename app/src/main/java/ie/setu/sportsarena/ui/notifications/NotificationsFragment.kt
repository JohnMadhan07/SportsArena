package ie.setu.sportsarena.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ie.setu.sportsarena.databinding.FragmentNotificationsBinding
import ie.setu.sportsarena.models.TimeSlot

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var notificationsViewModel: NotificationsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize the ViewModel
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        // Fetch time slots when the fragment is created
        notificationsViewModel.fetchTimeSlots()

        // Observe changes in timeSlots LiveData
        val textView: TextView = binding.textNotifications
        notificationsViewModel.timeSlots.observe(viewLifecycleOwner) { timeSlots ->
            // Update the TextView with the list of time slots
            val timeSlotsString = createTimeSlotsString(timeSlots)
            textView.text = timeSlotsString
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createTimeSlotsString(timeSlots: List<TimeSlot>): String {
        // Create a string representation of the time slots
        val stringBuilder = StringBuilder()
        for (timeSlot in timeSlots) {
            stringBuilder.append("Start Time: ${timeSlot.startTime}")
        }
        return stringBuilder.toString()
    }
}
