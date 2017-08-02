package br.com.edsilfer.sample_dagger2.core.di

import br.com.edsilfer.sample_dagger2.main_view.di.MainViewModule
import br.com.edsilfer.sample_dagger2.main_view.presentation.view.MainViewImpl
import br.com.edsilfer.sample_dagger2.secondary_view.di.SecondaryViewModule
import br.com.edsilfer.sample_dagger2.secondary_view.presentation.view.SecondaryViewImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by edgar on 02/08/17.
 */
@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainViewModule::class))
    abstract fun MainViewImpl (): MainViewImpl

    @ContributesAndroidInjector(modules = arrayOf(SecondaryViewModule::class))
    abstract fun SecondaryViewImpl (): SecondaryViewImpl

}
