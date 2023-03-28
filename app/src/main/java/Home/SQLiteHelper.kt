package Home

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context):SQLiteOpenHelper(context,"Interviewapp",null,5){


    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE tblQuestion(Id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT,option1 TEXT,option2 TEXT,option3 TEXT,option4 TEXT,answer INTEGER) ")
        db?.execSQL("CREATE TABLE logQuestion(Id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT,option1 TEXT,option2 TEXT,option3 TEXT,option4 TEXT,answer INTEGER)")
        db?.execSQL("CREATE TABLE cmnQuestion(Id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT,answer TEXT)")
        db?.execSQL("CREATE TABLE AndroidQuestion(Id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT,answer TEXT)")
        db?.execSQL("CREATE TABLE IosQuestion(Id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT,answer TEXT)")
        db?.execSQL("CREATE TABLE WebQuestion(Id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT,answer TEXT)")
        db?.execSQL("CREATE TABLE JavaQuestion(Id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT,option1 TEXT,option2 TEXT,option3 TEXT,option4 TEXT,answer TEXT) ")

    }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

            db?.execSQL("drop table if exists tblQuestion")
            db?.execSQL("drop table if exists logQuestion")
            db?.execSQL("drop table if exists cmnQuestion")
            db?.execSQL("drop table if exists AndroidQuestion")
            db?.execSQL("drop table if exists IosQuestion")
            db?.execSQL("drop table if exists WebQuestion")
            db?.execSQL("drop table if exists JavaQuestion")
            onCreate(db)
        }


    }