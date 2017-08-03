package br.com.edsilfer.sample_dagger2.core.di

import android.app.Application
import br.com.edsilfer.sample_dagger2.core.App
import br.com.edsilfer.sample_dagger2.core.domain.FakeWorker
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by edgar on 02/08/17.
 */
@Singleton
@Component(
        modules = arrayOf(
                AppModule::class,
                ActivitiesBuilder::class
        )
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun providesFakeWorker(): FakeWorker

    fun inject(app: App)

}
