package br.com.edsilfer.sample_dagger2.core.di

import br.com.edsilfer.sample_dagger2.module_alpha.di.AlphaModule
import br.com.edsilfer.sample_dagger2.module_alpha.presentation.view.AlphaViewImpl
import br.com.edsilfer.sample_dagger2.module_beta.di.BetaModule
import br.com.edsilfer.sample_dagger2.module_beta.presentation.view.BetaViewImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by edgar on 02/08/17.
 */
@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector(modules = arrayOf(AlphaModule::class))
    abstract fun MainViewImpl (): AlphaViewImpl

    @ContributesAndroidInjector(modules = arrayOf(BetaModule::class))
    abstract fun SecondaryViewImpl (): BetaViewImpl

}
