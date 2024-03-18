package com.example.mywallet.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// import com.example.mywallet.data.model.ExchangeRatesApi
import kotlinx.coroutines.launch


class CurrencyViewModel : ViewModel() {


    var eurInput by mutableStateOf("")

    var gbp by mutableStateOf(0.0)
        private set

    fun changeEur(newValue: String) {
        eurInput = newValue
    }

    fun roundToDecimalPlace(number: Double, decimalPlaces: Int): Double {
        val factor = Math.pow(10.0, decimalPlaces.toDouble())
        return (number * factor).toLong() / factor
    }

    fun convert() {
        val euros = eurInput.toDoubleOrNull() ?: 0.0
        euros.toFloat()
        gbp = roundToDecimalPlace(euros * gpbRate, 2)
    }




    sealed interface ExchangeRatesUIState {
        object Loading : ExchangeRatesUIState
        object Error : ExchangeRatesUIState
        object Success : ExchangeRatesUIState
    }

    var gpbRate by mutableStateOf(0.0f)
        private set

    var exchangeRatesUIState by mutableStateOf<ExchangeRatesUIState>(ExchangeRatesUIState.Loading)
    private set

    init {
        getExchangeRateForGpb()
    }
    fun getExchangeRateForGpb() {
        viewModelScope.launch {
            //var exchangeRatesApi: ExchangeRatesApi? = null

            try {
                //exchangeRatesApi = ExchangeRatesApi.getInstance(apiKey = "hVx1xmB0oGqEET6h4GLEA8VbPDBiruhH")
                //val exchangeRates = exchangeRatesApi!!.getRates(apiKey = "hVx1xmB0oGqEET6h4GLEA8VbPDBiruhH")
                //if (exchangeRates.success) {
                    gpbRate = 0.85f
                //var exchangeRatesUIState by mutableStateOf<ExchangeRatesUIState>(ExchangeRatesUIState.Success)
                    exchangeRatesUIState = ExchangeRatesUIState.Success
                //} else {
                    //exchangeRatesUIState = ExchangeRatesUIState.Error
                //}
            } catch (e: Exception) {
                gpbRate = 0.0f
                exchangeRatesUIState = ExchangeRatesUIState.Error
            }
        }
    }


}
