package br.com.edsilfer.sample_dagger2.core.di

import android.app.Application
import android.content.Context
import br.com.edsilfer.sample_dagger2.core.components.FakeWorker
import br.com.edsilfer.sample_dagger2.core.components.FakeWorkerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by edgar on 02/08/17.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesApplicationContext (application: Application) : Context = application

    @Provides
    @Singleton
    fun providesFakeWorker (context: Context) : FakeWorker = FakeWorkerImpl(context)

}
