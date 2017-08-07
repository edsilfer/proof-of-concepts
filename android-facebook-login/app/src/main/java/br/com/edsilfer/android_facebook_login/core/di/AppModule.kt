package br.com.edsilfer.android_facebook_login.core.di

import android.app.Application
import android.content.Context
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenter
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenterImpl
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginView
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
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
