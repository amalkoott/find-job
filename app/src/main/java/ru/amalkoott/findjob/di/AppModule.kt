package ru.amalkoott.findjob.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.amalkoott.findjob.data.local.AppDao
import ru.amalkoott.findjob.data.local.AppDatabase
import ru.amalkoott.findjob.data.local.AppRepository_Impl
import ru.amalkoott.findjob.data.remote.ApiService
import ru.amalkoott.findjob.data.remote.ServerRequestRepository
import ru.amalkoott.findjob.domain.AppRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    @Singleton
    @Provides
    fun provideInternetConnection(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideServerApi(retrofit: Retrofit): ServerRequestRepository {
        return ServerRequestRepository(retrofit.create(ApiService::class.java))
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,"app_database"
        ).fallbackToDestructiveMigration()
            .build()
        return db
    }

    @Singleton
    @Provides
    fun provideAppDao(db: AppDatabase): AppDao {
        return db.notesDao()
    }

    @Singleton
    @Provides
    fun provideAppRepository(dao: AppDao): AppRepository {
        return AppRepository_Impl(dao)
    }

}