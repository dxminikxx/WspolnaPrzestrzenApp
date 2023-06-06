import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProblemsDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Problems.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Problems"
        private const val COLUMN_NAME = "Name"
        private const val COLUMN_MESSAGE = "Message"
        private const val COLUMN_PHONE = "Phone"
        private const val COLUMN_EMAIL = "Email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_MESSAGE TEXT, " +
                "$COLUMN_PHONE TEXT, " +
                "$COLUMN_EMAIL TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addProblem(name: String, message: String, phone: String, email: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_MESSAGE, message)
        values.put(COLUMN_PHONE, phone)
        values.put(COLUMN_EMAIL, email)
        val result = db.insert(TABLE_NAME, null, values)
        return result != -1L
    }
}
