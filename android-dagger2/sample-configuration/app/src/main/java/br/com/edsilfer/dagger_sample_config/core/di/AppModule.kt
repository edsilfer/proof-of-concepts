package br.com.edsilfer.dagger_sample_config.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by edgar on 03/08/17.
 */
@Module
abstract class AppModule {

    @Provides
    @Singleton
    fun provides (application: Application) : Context = application

}
