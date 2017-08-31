package br.com.edsilfer.android_facebook_login.login.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import br.com.edsilfer.android_facebook_login.R
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenter
import br.com.edsilfer.reactive_facebook.data.datasource.rest.json.GraphAPISchema
import br.com.edsilfer.tookit.core.components.BaseActivity
import br.com.edsilfer.tookit.core.components.BasePresenter
import br.com.edsilfer.tookit.presentation.view.contract.LoadingScreen
import br.com.edsilfer.tookit.presentation.view.contract.LoadingView
import com.facebook.CallbackManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.login_view.*
import javax.inject.Inject

class LoginViewImpl : BaseActivity(), LoadingView {

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, LoginViewImpl::class.java)
    }

    @Inject
    lateinit var presenter: LoginPresenter
    @Inject
    lateinit var loadingScreen: LoadingScreen
    @Inject
    lateinit var callbackManager: CallbackManager

    override fun getPresenter(): BasePresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        prepareFacebookConfiguration()
    }

    private fun prepareFacebookConfiguration() {
        button_facebookLogin.setReadPermissions(GraphAPISchema.ARG_USER_PERMISSIONS)
        button_facebookLogin.registerCallback(callbackManager, presenter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_login_view_impl, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun show() = loadingScreen.show()

    override fun dismiss() = loadingScreen.dismiss()
}
