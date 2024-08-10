package com.example.byebyebad

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile

//abstract class to create the database
abstract class MistakeDatabase:RoomDatabase() {

    //abstract function to get the mistakeDao from the database
    abstract fun mistakeDao(): MistakeDao

    companion object{
        //the use of volatile is to ensure that the value of INSTANCE is always up-to-date and the same to all execution threads.
        @Volatile
        //the INSTANCE variable will keep a reference to the database
        private var INSTANCE : MistakeDatabase? = null

        fun getDatabase(context :Context): MistakeDatabase{
            //if the INSTANCE is not null, then return it,
            //if it is, then create the database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MistakeDatabase::class.java,
                    "mistake_database"
                ).build()
                //assign the instance to the INSTANCE to keep a reference to the database
                INSTANCE = instance
                instance
            }
        }
    }
}