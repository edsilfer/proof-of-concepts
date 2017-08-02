package br.com.edsilfer.sample_dagger2.core.components

import io.reactivex.Completable

/**
 * Created by edgar on 02/08/17.
 */
interface FakeWorker {

    fun doFakeJob () : Completable

}
