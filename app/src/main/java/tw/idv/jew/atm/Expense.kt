package tw.idv.jew.atm

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Expense(
//    @NonNull  //預設值
    @ColumnInfo(name = "dateAt")
    var date: String,
    var info: String,
    var amount: Int
    //id欄位排序會變最後
//    ,@PrimaryKey(autoGenerate = true)
//    var id: Long = 0
) {
    //欄位排序優先
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}