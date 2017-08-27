package br.com.edsilfer.android_facebook_login.homepage.di

import br.com.edsilfer.android_facebook_login.core.di.acttivity.ActivityScope
import br.com.edsilfer.android_facebook_login.core.di.FacebookModule
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageButtonPanelViewImpl
import dagger.Component

/**
 * Created by edgar on 06/08/17.
 */
@ActivityScope
@Component(
        modules = arrayOf(
                HomepageButtonPanelModule::class,
                FacebookModule::class
        )
)
interface HomepageButtonPanelComponent {

    fun inject(homepageButtonPanelViewImpl: HomepageButtonPanelViewImpl)


}
