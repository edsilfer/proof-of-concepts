package br.com.edsilfer.android_facebook_login.core.di.acttivity

import android.app.Activity
import br.com.edsilfer.android_facebook_login.login.di.LoginSubComponent
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


/**
 * Created by edgar on 25/08/17.
 */
@Module
abstract class ActivityBinding {

    @Binds
    @IntoMap
    @ActivityKey(LoginViewImpl::class)
    abstract fun bindLoginView(builder: LoginSubComponent.Builder): AndroidInjector.Factory<out Activity>

}