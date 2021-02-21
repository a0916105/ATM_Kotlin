package tw.idv.jew.atm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {
    @Insert
    fun add(expense: Expense)

    @Query("select * from Expense")
    fun getAll() : List<Expense>
}