import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mywallet.screens.CurrencyConverterScreen
import com.example.mywallet.screens.ErrorScreen
import com.example.mywallet.screens.LoadingScreen
import com.example.mywallet.viewmodel.CurrencyViewModel

@Composable
fun CurrencyConverterApp(navController: NavController, currencyViewModel: CurrencyViewModel = viewModel()){


    when (currencyViewModel.exchangeRatesUIState) {
        is CurrencyViewModel.ExchangeRatesUIState.Success -> {
            CurrencyConverterScreen(
                eurInput = currencyViewModel.eurInput,
                gbp = currencyViewModel.gbp,
                changeEur = { currencyViewModel.changeEur(it) },
                convert = { currencyViewModel.convert() }
            )
        }
        is CurrencyViewModel.ExchangeRatesUIState.Loading -> {
            LoadingScreen()
        }
        is CurrencyViewModel.ExchangeRatesUIState.Error -> {
            ErrorScreen()
        }
    }
}
