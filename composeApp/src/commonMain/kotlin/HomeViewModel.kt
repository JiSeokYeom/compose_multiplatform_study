import api.CurrencyApiService
import api.CurrencyApiServiceImpl
import api.model.Currency
import api.model.RequestState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(

) : ViewModel() {

    val currencyApiService: CurrencyApiService = CurrencyApiServiceImpl()

    private val _events = MutableStateFlow<RequestState<List<Currency>>>(RequestState.Idle)
    val events = _events.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
          _events.value = currencyApiService.getLatestExchangeRates()
        }
    }
}

sealed class Events {
    data object Loading : Events()
}