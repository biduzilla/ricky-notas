package com.example.momonotes.di

import android.content.Context
import androidx.room.Room
import com.example.momonotes.data.NotaDatabase
import com.example.momonotes.data.dao.NotaDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotaDao(notaDatabase: NotaDatabase): NotaDatabaseDao {
        return notaDatabase.notaDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NotaDatabase =
        Room.databaseBuilder(
            context,
            NotaDatabase::class.java,
            "notas_db"
        ).fallbackToDestructiveMigration()
            .build()

}