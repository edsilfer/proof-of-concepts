package br.com.edsilfer.android_facebook_login.core.components

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Action

/**
 * Created by edgar on 06/09/17.
 */
object BehaviorCatalyst {

    fun <T> catalyzeOnObservableSubscribe(action: Action): ObservableTransformer<T, T> =
            ObservableTransformer { it.doOnSubscribe { action.run() } }


    fun catalyzeCompletableSubscribe(action: Action): CompletableTransformer =
            CompletableTransformer {
                it.doOnSubscribe { action.run() }
            }

}