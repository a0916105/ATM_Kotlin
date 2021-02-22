package tw.idv.jew.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_expense.*
import kotlinx.android.synthetic.main.expense_row.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class ExpenseActivity : AppCompatActivity() {
    companion object {
        val TAG = ExpenseActivity::class.java.simpleName
    }
    private lateinit var database: ExpenseDatabase

    val expenseData = arrayListOf<Expense>(
        Expense("2021/02/01", "Lunch", 120),
        Expense("2021/02/02", "停車費", 60),
        Expense("2021/02/05", "日用品", 215),
        Expense("2021/02/07", "Parking", 55),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        database = Room.databaseBuilder(this,
                ExpenseDatabase::class.java, "expense.db")
                .build()
        //Query expenses
        CoroutineScope(Dispatchers.IO).launch {
//        CoroutineScope(Dispatchers.Default).launch {
            val expenses = database.expenseDao().getAll()
            Log.d(TAG, expenses.size.toString())

            val adapter = object : RecyclerView.Adapter<ExpenseViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                        : ExpenseViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.expense_row, parent, false)
                    return ExpenseViewHolder(view)
                }

                override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
                    val exp = expenses.get(position)
                    holder.date.setText(exp.date)
                    holder.info.setText(exp.info)
                    holder.amount.setText(exp.amount.toString())
                }

                override fun getItemCount(): Int {
                    return expenses.size
                }
            }
            runOnUiThread {
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(this@ExpenseActivity)
                recycler.adapter = adapter
            }
        }

        fab.setOnClickListener {
            val database = Room.databaseBuilder(this,
                ExpenseDatabase::class.java, "expense.db")
                .build()
            Executors.newSingleThreadExecutor().execute {
//                database.expenseDao().add(expenseData[0])
                for (expense in expenseData)
                    database.expenseDao().add(expense)
            }
        }
    }

    class ExpenseViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val date = itemView.exp_date
        val info = itemView.exp_info
        val amount = itemView.exp_amount
    }
}