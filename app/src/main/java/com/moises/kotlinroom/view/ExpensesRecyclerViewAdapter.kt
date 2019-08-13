package com.moises.kotlinroom.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import com.moises.kotlinroom.R
import com.moises.kotlinroom.database.Expenses
import kotlinx.android.synthetic.main.item_list_expenses.view.*

/**
 * Created by Moises on 8/12/2019.
 */

class ExpensesRecyclerViewAdapter(private val mContext: Context,
                                 private val mValues: MutableList<Expenses>,
                                  private val mExpensesListener :ExpensesListener)
    : RecyclerView.Adapter<ExpensesRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Expenses
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_expenses, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mNameView.text = item.name

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }

        holder.mOptionsView.setOnClickListener {view ->
            onOptionMenuClicked(position, view)
        }
    }

    @SuppressLint("InflateParams")
    private fun onOptionMenuClicked(position: Int, view: View) {
        val popup = PopupMenu(mContext, view)
        popup.menuInflater.inflate(R.menu.menu_ip_options, popup.menu)
        popup.setOnMenuItemClickListener { myItem ->

            //Getting Id of selected Item
            val item = myItem!!.itemId

            when (item) {

                R.id.action_delete -> {
                    mExpensesListener.onDeleteExpenses(mValues[position])
                }


            }
            true
        }
        popup.show()
    }


    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mNameView: TextView = mView.expensesName
        val mOptionsView: ImageButton = mView.fileOptions


        override fun toString(): String {
            return super.toString() + " '" + mNameView.text + "'"
        }
    }


}

interface ExpensesListener {

    fun onDeleteExpenses(expenses: Expenses)

}