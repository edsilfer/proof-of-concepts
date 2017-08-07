package br.com.edsilfer.android_facebook_login.login.presentation.presenter

import br.com.edsilfer.tookit.core.components.BasePresenter
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult

/**
 * Created by edgar on 03/08/17.
 */
interface LoginPresenter : BasePresenter, FacebookCallback<LoginResult>
