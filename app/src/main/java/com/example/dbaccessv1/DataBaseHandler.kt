package com.example.dbaccessv1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DataBaseHandler(private var context: Context) : SQLiteOpenHelper(context, "MyDB.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + "Users" + " (" +
                "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name" + " VARCHAR(100), " +
                "city" + " CHAR(1))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(name : String, city : String) {
        try {
            this.writableDatabase.execSQL("Insert into Users(name,city) values ('$name', '$city')")
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            this.writableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun findData(id : Int) : User {
        val user = User()
        try {
            val result = this.readableDatabase.rawQuery("select * from Users where id = $id", null)
            while (result.moveToNext()){
                user.name = result.getString(1).toString()
                user.city = result.getString(2).toString()
            }
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        return user
    }

    fun getUser(nCtx : Context) : ArrayList<User> {

        val result = this.readableDatabase.rawQuery("Select * from Users", null)
        val userList = ArrayList<User>()

        try {
            if(result.count == 0) {
                Toast.makeText(nCtx, "No Accounts To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = User()
                    user.id = result.getInt(result.getColumnIndex("id"))
                    user.name = result.getString(result.getColumnIndex("name"))
                    user.city = result.getString(result.getColumnIndex("city"))
                    userList.add(user)
                }
                Toast.makeText(nCtx, "${result.count} Accounts Found", Toast.LENGTH_SHORT).show()
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        return userList
    }

    fun editData(id: Int, userName: String, userCity: String){
        val cv = ContentValues()
        cv.put("name", userName)
        cv.put("city", userCity)

        try {
            this.writableDatabase.update("Users", cv, "id = ?", arrayOf(id.toString()))
            this.writableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteData(id: Int) {
        try {
            this.readableDatabase.execSQL("Delete from Users where id = $id")
            this.writableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}

