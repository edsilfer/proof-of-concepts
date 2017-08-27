package br.com.edsilfer.android_facebook_login.core.di.app

import android.app.Application
import android.content.Context
import br.com.edsilfer.android_facebook_login.core.di.session.SessionComponent
import br.com.edsilfer.android_facebook_login.login.di.LoginSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by edgar on 03/08/17.
 */
@Module(
        subcomponents = arrayOf(
                SessionComponent::class,
                LoginSubComponent::class
        )
)
abstract class AppModule {

    @Provides
    @Singleton
    fun provides(application: Application): Context = application

}
