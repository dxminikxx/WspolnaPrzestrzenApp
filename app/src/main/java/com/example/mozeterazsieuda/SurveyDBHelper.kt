import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SurveyDBHelper(context: Context) : SQLiteOpenHelper(context, "SurveyData.db", null, 1) {

    companion object {
        private const val TABLE_SURVEY = "SurveyData"
        private const val COLUMN_ID = "id"
        private const val COLUMN_OPTION = "option"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_SURVEY ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_OPTION TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_SURVEY"
        db.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertSurveyData(option: String): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_OPTION, option)
        val result = db.insert(TABLE_SURVEY, null, contentValues)
        return result != -1L
    }
}
