package br.com.edsilfer.android_facebook_login.core.di.session

import android.app.Activity
import br.com.edsilfer.android_facebook_login.homepage.di.HomepageSubComponent
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewImpl
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by edgar on 25/08/17.
 */
@Module
abstract class SessionActivityBinding {

    @Binds
    @IntoMap
    @ActivityKey(HomepageViewImpl::class)
    abstract fun bonHomepageView(builder: HomepageSubComponent.Builder): AndroidInjector.Factory<out Activity>

}