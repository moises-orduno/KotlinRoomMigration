package com.moises.kotlinroom.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.moises.kotlinroom.R
import com.moises.kotlinroom.database.Expenses
import com.moises.kotlinroom.database.KotlinRoomDao
import com.moises.kotlinroom.database.KotlinRoomDatabase
import com.moises.kotlinroom.database.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),ExpensesListener {
    
    override fun onDeleteExpenses(expenses: Expenses) {
        GlobalScope.launch {
            mRepository.deleteExpensesEntry(expenses)
            runOnUiThread {
                mExpenses?.remove(expenses)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    private var mExpenses: MutableList<Expenses>? = null
    private lateinit var mRepository: Repository
    private var mExpensesListener = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRepository = Repository(KotlinRoomDatabase.getInstance(this)!!.kotlinRoomDao())
        recyclerView.layoutManager = LinearLayoutManager(this)

        getAllExpenses()

        fab.setOnClickListener { view ->

            val expenses = editTextExpenses.text.toString()
            val name = editTextName.text.toString()

            if (TextUtils.isEmpty(expenses)) {


                return@setOnClickListener
            }
            if (TextUtils.isEmpty(name)) {

                return@setOnClickListener
            }
            val expensesToAdd = Expenses(0, name, expenses.toDouble(), System.currentTimeMillis())
            GlobalScope.launch {

                mRepository.insertExpensesEntry(expensesToAdd)
                runOnUiThread {
                    mExpenses?.add(expensesToAdd)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }

        }
    }

    private fun getAllExpenses() {

        GlobalScope.launch {
            mExpenses = mRepository.getAllExpensesEntries()
            recyclerView.adapter = ExpensesRecyclerViewAdapter(this@MainActivity, mExpenses!!,mExpensesListener)
        }

    }


}
