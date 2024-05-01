import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GamesViewModel : ViewModel() {

    private val _gamesList = MutableLiveData<List<String>>()
    val gamesList: LiveData<List<String>> = _gamesList

    init {
        // Example data, you can replace it with your actual data source
        _gamesList.value = listOf("Cricket", "Badminton", "Football")
    }
}
