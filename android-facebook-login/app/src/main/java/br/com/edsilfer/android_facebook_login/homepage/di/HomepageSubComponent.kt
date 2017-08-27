package br.com.edsilfer.android_facebook_login.homepage.di

import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewImpl
import dagger.Subcomponent
import dagger.android.AndroidInjector


/**
 * Created by edgar on 25/08/17.
 */
@Subcomponent(modules = arrayOf(HomepageModule::class))
interface HomepageSubComponent : AndroidInjector<HomepageViewImpl> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomepageViewImpl>()

}