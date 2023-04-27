package com.example.mozeterazsieuda

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*
import kotlin.collections.ArrayList

class DBHelper(context: Context):SQLiteOpenHelper(context, "Userdata", null, 1) {

    private val TABLE_EVENTS = "EventLIST"
    companion object {
        const val TABLE_ANNOUNCEMENT = "table_announcement"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table UserData (username TEXT primary key, password TEXT)")
        //create event table
        p0?.execSQL("create table $TABLE_EVENTS (id INTEGER primary key autoincrement, event_name TEXT, date TEXT, hour TEXT)")
        //create announcement table
        p0?.execSQL("create table $TABLE_ANNOUNCEMENT (id INTEGER primary key autoincrement, announcement_name TEXT, date STRING)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Userdata ")
        //drop event table
        p0?.execSQL("drop table if exists $TABLE_EVENTS ")
        //drop announcement table
//        p0?.execSQL("drop table if exists $TABLE_ANNOUNCEMENT")
    }

    fun instertdata(username: String, password: String): Boolean{
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("password", password)
        val result = p0.insert("Userdata", null, cv)
        if (result==-1 .toLong()){
            return false
        }
        return true
    }

    //method added information about new event
    fun insertEventData(eventName: String, date: String, hour: String): Boolean{
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("event_name", eventName)
        cv.put("date", date)
        cv.put("hour", hour)
        val result = p0.insert(TABLE_EVENTS, null, cv)
        if (result == -1 .toLong()){
            return false
        }
        return true
    }

    fun insertAnnouncement(announcement_name: String, date: String):Boolean{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("announcement_name", announcement_name)
        cv.put("date", date)
        val result = db.insert(TABLE_ANNOUNCEMENT, null, cv)
        if (result==-1 .toLong()){
            return false
        }
        return true
    }

    @SuppressLint("Range")
    fun getAllAnnouncement() : ArrayList<AnnouncementFragment>{
        val announcementList: ArrayList<AnnouncementFragment> = ArrayList()
        val selectQuery = "select * from $TABLE_ANNOUNCEMENT"
        val db = this.readableDatabase

        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch(e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if(cursor.moveToFirst()){
            do{
                val announcementName = cursor.getString(cursor.getColumnIndex("announcement_name"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val announcement = AnnouncementFragment(announcement_name = announcementName, date = date)
                announcementList.add(announcement)
            }while(cursor.moveToNext())
        }
        return announcementList
    }

    fun checkuserpass(username: String, password: String):Boolean{
        val p0 = this.writableDatabase
        val query = "select * from Userdata where username='$username' and password='$password'"
        val cursor = p0.rawQuery(query, null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    //method downloader information about event from table
    @SuppressLint("Range")
    fun getEventData(): MutableList<Triple<String, String, String>>{
        val p0 = this.readableDatabase
        val eventList = mutableListOf<Triple<String, String, String>>()
        val query = "select * from $TABLE_EVENTS"
        val cursor = p0.rawQuery(query, null)
        if(cursor.moveToFirst()){
            do{
                val eventName = cursor.getString(cursor.getColumnIndex("event_name"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val hour = cursor.getString(cursor.getColumnIndex("hour"))
                eventList.add(Triple(eventName, date, hour))
            }while(cursor.moveToNext())
        }
        cursor.close()
        return eventList
    }

    // metoda aktualizująca dane o wydarzeniach w tabeli
    fun updateEventData(eventName: String, newDate: String, newHour: String): Boolean{
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("date", newDate)
        cv.put("hour", newHour)
        val result = p0.update(TABLE_EVENTS, cv, "event_name=?", arrayOf(eventName))
        if(result == 0){
            return false
        }
        return true
    }

    // metoda usuwająca dane o wydarzeniach z tabeli
    fun deleteEventData(eventName: String): Boolean{
        val p0 = this.writableDatabase
        val result = p0.delete(TABLE_EVENTS, "event_name=?", arrayOf(eventName))
        if(result == 0){
            return false
        }
        return true
    }

}