package com.example.mywallet.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mywallet.data.dao.Dao
import com.example.mywallet.data.model.Entity

class HomeViewModel(dao: Dao): ViewModel() {
    val dao = dao.getAllData()
    fun getBalance(list:List<Entity>): String{
        var balance = 0.0
        for (i in list){
            if (i.type == "Income"){
                balance += i.amount
            }else{
                balance -= i.amount
            }
        }
        return "EUR ${balance}"

    }
    fun getTotalExpense(list:List<Entity>): String{
        var total = 0.0
        for (i in list){
            if (i.type == "Expense"){
                total += i.amount
            }
        }
        return "EUR ${total}"

    }
    fun getTotalIncome(list:List<Entity>): String{
        var total = 0.0
        for (i in list){
            if (i.type == "Income"){
                total += i.amount
            }
        }
        return "EUR ${total}"

    }
}
class HomeViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val dao = com.example.mywallet.data.WalletDB.getDatabase(context).dao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}