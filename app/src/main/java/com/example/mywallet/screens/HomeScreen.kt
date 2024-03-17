package com.example.mywallet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
//import color from Color.kt
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.mywallet.R
import com.example.mywallet.data.model.EntityExpense
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.navigation.NavController

import com.example.mywallet.ui.theme.PurpleX
import com.example.mywallet.viewmodel.HomeViewModel
import java.util.Date


@Composable
fun HomeScreen(navController: NavController){

val viewModel:HomeViewModel = HomeViewModel.HomeViewModelFactory(LocalContext.current).create(HomeViewModel::class.java)
    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout {
            // Create references for the composables to constrain
            val (list, nameRow, topbar, card) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.bgg),
                contentDescription = null,
                modifier = Modifier.constrainAs(topbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Column {
                    Text(text = "My Wallet", fontSize = 24.sp, color = Color.White)
                }
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .clickable { navController.navigate("welcome") }
                )
               /* Button(
                    onClick = { navController.navigate("welcome")},
                    modifier = Modifier
                        .clip(RoundedCornerShape(2.dp))
                        .fillMaxWidth()
                ) {
                    Text("Back")
                }*/

            }
        val state = viewModel.expenses.collectAsState(initial= emptyList())
            val expenses = viewModel.getTotalExpense(state.value)
            val income = viewModel.getTotalIncome(state.value)
            val balance = viewModel.getBalance(state.value)
            CardItem(
                modifier = Modifier
                    .constrainAs(card) {
                        top.linkTo(nameRow.bottom)
                        //start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, balance, income, expenses
            )

            TransactionList(
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(card.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }, list = state.value, viewModel = viewModel
            )

        }
    }
}

@Composable
fun CardItem(modifier: Modifier, balance: String, income: String, expenses: String){

        Column(modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(16.dp)
            )
            .height(200.dp)
            .background(PurpleX)
            .padding(16.dp)
        ) {
            /*Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f))
                {
             */

                    Text(text = "Total Balance", fontSize = 16.sp,color = Color.White)
                    Text(text = balance, fontSize = 24.sp, fontWeight = FontWeight.Bold,color = Color.White)
                    Spacer(modifier = Modifier.padding(20.dp))

                Row(modifier = Modifier.fillMaxWidth()){

                        Column{
                            Row{
                                Image(
                                    painter = painterResource(id = R.drawable.income2),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = "Income", fontSize = 16.sp, color = Color.White)
                            }
                            Text(text = income, fontSize = 20.sp, color = Color.White)
                        }
                    //}
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)) {
                        Column( modifier = Modifier.align(Alignment.CenterEnd)){
                            Row{
                                Image(
                                    painter = painterResource(id = R.drawable.expense1),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = "Expense", fontSize = 16.sp, color = Color.White)
                            }
                            Text(text = expenses, fontSize = 20.sp, color = Color.White)
                        }
                    }

                }
            }
        }



@Composable
fun TransactionList(modifier: Modifier, list: List<EntityExpense>, viewModel: HomeViewModel){
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = "All Transactions", fontSize = 20.sp, color = Color.Gray)

            }
        }
        items(list) { item ->
            TransactionItem(
                title = item.title,
                amount = "EUR ${item.amount}",
                icon = viewModel.getItemIcon(item),
                date = item.date.toString(),
                description = item.description
            )
        }

    }
}


/*@Composable
fun CardRowItem(modifier: Modifier, title:String, amount:String, icon:Int, color: Color){
    Column(modifier = modifier){
        Row{
            Image(
                painter = painterResource(id = icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = title, fontSize = 16.sp, color = Color.White)
        }
        Text(text = amount, fontSize = 20.sp, color = Color.White)
    }

}*/
@Composable
fun TransactionItem(title: String, amount: String, icon: Int, date: String,description:String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ){
        Row{
            Image(
                painter = painterResource(id = icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Column{
                Text(text = title, fontSize = 16.sp)
                Text(text = date, fontSize = 12.sp)
                Text(text =description, fontSize = 12.sp)
            }
        }
        Text(text = amount, fontSize = 20.sp,  modifier = Modifier.align(Alignment.CenterEnd))
    }
}


/*
@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen()
}
*/
