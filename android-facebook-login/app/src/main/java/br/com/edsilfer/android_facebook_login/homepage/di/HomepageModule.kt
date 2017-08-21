package br.com.edsilfer.android_facebook_login.homepage.di

import br.com.edsilfer.android_facebook_login.facebook.domain.FacebookRepository
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepagePresenter
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepagePresenterImpl
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageView
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewImpl
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 04/08/17.
 */
@Module
class HomepageModule {

    @Provides
    fun providesPicasso(homepageViewImpl: HomepageViewImpl): Picasso = Picasso.with(homepageViewImpl)

    @Provides
    fun providesHomepageView(homepageViewImpl: HomepageViewImpl): HomepageView = homepageViewImpl

    @Provides
    fun providesHomepagePresenter(
            homepageView: HomepageView,
            facebookRepository: FacebookRepository
    ): HomepagePresenter = HomepagePresenterImpl(homepageView, facebookRepository)

}