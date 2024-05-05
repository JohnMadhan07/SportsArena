package ie.setu.sportsarena.models

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class TimeSlot(
    var uid: String = "",
    var startTime: String = "",
    var endTime: String = "",
    var isSelected: Boolean = true
) : Parcelable {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "startTime" to startTime,
            "endTime" to endTime,
            "isSelected" to isSelected
        )
    }
}
