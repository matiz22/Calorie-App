package com.example.calorieapp.di

import android.content.Context
import androidx.room.Room
import com.example.calorieapp.data.CalorieDatabase
import com.example.calorieapp.data.CalorieDatabaseDao
import com.example.calorieapp.network.CalorieApi
import com.example.calorieapp.repository.DatabaseRepository
import com.example.calorieapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @get:Provides
    val api: CalorieApi by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
                .build().create(CalorieApi::class.java)

    }
    @Singleton
    @Provides
    fun provideCalorieDatabase(@ApplicationContext appContext: Context): CalorieDatabase {
        return Room.databaseBuilder(
            appContext,
            CalorieDatabase::class.java,
            "calorie_database"
        ).allowMainThreadQueries().build()
    }
    @Singleton
    @Provides
    fun provideCalorieDao(database: CalorieDatabase): CalorieDatabaseDao {
        return database.calorieDao()
    }
    @Singleton
    @Provides
    fun provideDatabaseRepository(calorieDao: CalorieDatabaseDao): DatabaseRepository {
        return DatabaseRepository(calorieDao)
    }

}