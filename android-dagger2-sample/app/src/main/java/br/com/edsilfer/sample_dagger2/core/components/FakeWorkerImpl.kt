package br.com.edsilfer.sample_dagger2.core.components

import android.content.Context
import br.com.edsilfer.sample_dagger2.R
import io.reactivex.Completable
import timber.log.Timber

/**
 * Created by edgar on 02/08/17.
 */
class FakeWorkerImpl(val context: Context) : FakeWorker {

    override fun doFakeJob(): Completable {
        return Completable.create {
            emiter ->
            Thread.sleep(2000)
            Timber.i(context.getString(R.string.str_fake_job_description))
            emiter.onComplete()
        }
    }

}
