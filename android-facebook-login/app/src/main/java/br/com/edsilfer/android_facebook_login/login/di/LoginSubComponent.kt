package br.com.edsilfer.android_facebook_login.login.di

import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by edgar on 25/08/17.
 */
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginSubComponent : AndroidInjector<LoginViewImpl> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LoginViewImpl>()

}