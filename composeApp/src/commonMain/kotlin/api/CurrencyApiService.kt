package api

import api.model.Currency
import api.model.RequestState

interface CurrencyApiService {

    suspend fun getLatestExchangeRates(): RequestState<List<Currency>>
}