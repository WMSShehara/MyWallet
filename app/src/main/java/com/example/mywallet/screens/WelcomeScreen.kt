package com.example.mywallet.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywallet.ui.theme.PurpleX


@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Wallet", color = PurpleX,
            modifier = Modifier.padding(8.dp),
            style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Button(
            onClick = { navController.navigate("home")},
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .fillMaxWidth()
        ) {
            Text("View All Transactions")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = { navController.navigate("addExpense")},
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .fillMaxWidth()
        ) {
            Text("Add new Transaction")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = { navController.navigate("currencyConverter")},
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .fillMaxWidth()
        ) {
            Text("Currency Converter")
        }
    }
}
/*@Composable
@Preview(showBackground = true)
fun PreviewWelcomeScreen() {
    WelcomeScreen(navController = NavHostController())
}*/