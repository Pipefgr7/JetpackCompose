package com.example.tallercompose04.di

import android.content.Context
import androidx.room.Room
import com.example.tallercompose04.data.local.AppDatabase
import com.example.tallercompose04.data.local.TransaccionDao
import com.example.tallercompose04.data.repository.TransaccionRepositoryImpl
import com.example.tallercompose04.domain.repository.TransaccionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "finanzas_db").build()

    @Provides @Singleton
    fun provideDao(db: AppDatabase): TransaccionDao = db.transaccionDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton
    abstract fun bindRepo(impl: TransaccionRepositoryImpl): TransaccionRepository
}