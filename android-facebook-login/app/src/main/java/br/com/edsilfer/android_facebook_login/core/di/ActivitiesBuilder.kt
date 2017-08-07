package br.com.edsilfer.android_facebook_login.core.di

import br.com.edsilfer.android_facebook_login.core.di.scopes.ActivityScope
import br.com.edsilfer.android_facebook_login.facebook.di.FacebookModule
import br.com.edsilfer.android_facebook_login.homepage.di.HomepageModule
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewImpl
import br.com.edsilfer.android_facebook_login.login.di.LoginModule
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by edgar on 03/08/17.
 */
@Module
abstract class ActivitiesBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class, FacebookModule::class))
    abstract fun providesLoginViewImpl(): LoginViewImpl

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HomepageModule::class, FacebookModule::class))
    abstract fun providesHomepageViewImpl(): HomepageViewImpl

}
