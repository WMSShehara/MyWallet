package com.example.mywallet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mywallet.R
import com.example.mywallet.data.dao.ExpenseRepository
import com.example.mywallet.data.model.EntityExpense
import com.example.mywallet.viewmodel.AddExpenseViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.mywallet.data.WalletDB
import com.example.mywallet.data.dao.ExpenseDao
import com.example.mywallet.data.dao.ExpenseDaoImpl
import com.example.mywallet.data.dao.ExpenseDao_Impl

@Composable
fun AddExpense(addExpenseViewModel: AddExpenseViewModel, navController: NavController) {
    Surface(modifier= Modifier.fillMaxSize()) {
        ConstraintLayout (modifier=Modifier.fillMaxSize()) {
            val (nameRow, list, card, topbar) = createRefs()
            Image(painter = painterResource(id = R.drawable.bgg),
                contentDescription = null,
                modifier = Modifier.constrainAs(topbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            {

                Text(
                    text = "Add Transaction",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)

                )
                Image(painter= painterResource(id = R.drawable.home), contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .clickable { navController.navigate("welcome") }
                )
            }
            DataForm(modifier = Modifier
                .padding(60.dp)
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                addExpenseViewModel = addExpenseViewModel)

        }

    }
}
@Composable
fun DataForm(modifier: Modifier, addExpenseViewModel: AddExpenseViewModel) {
    var titleText by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var dateText by remember { mutableStateOf("") }
    var typeText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Type", fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = typeText,
            onValueChange = { typeText = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Title", fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = titleText,
            onValueChange = { titleText = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Amount", fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = amountText,
            onValueChange = { amountText = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Date", fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = dateText,
            onValueChange = { dateText = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Description", fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = descriptionText,
            onValueChange = { descriptionText = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val expense = EntityExpense(
                    title = titleText,
                    amount = amountText.toDouble(),
                    date = dateText.toLong(),
                    type = typeText,
                    description = descriptionText
                )
                addExpenseViewModel.insertExpense(expense)
            },
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .fillMaxWidth()
        ) {
            Text(text = "Confirm", fontSize = 14.sp)
        }

    }
}


/*@Composable
// @Preview
fun PreviewAddIncome() {
    val context = LocalContext.current
    val database = WalletDB.getInstance(context) // You need to provide the appropriate context here
    val viewModel = AddExpenseViewModel(repository = ExpenseRepository(dao = ExpenseDaoImpl(database)))
    AddExpense(addExpenseViewModel = viewModel)
}
*/

