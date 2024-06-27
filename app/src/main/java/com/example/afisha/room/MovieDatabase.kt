package com.example.afisha.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.afisha.pojo.Converters
import com.example.afisha.pojo.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "movie.db"
        private var instance: MovieDatabase? = null

        fun getInstance(application: Application): MovieDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(application, MovieDatabase::class.java, DB_NAME).build()
            }
            return instance as MovieDatabase
        }
    }

    abstract fun movieDao(): Dao
}
