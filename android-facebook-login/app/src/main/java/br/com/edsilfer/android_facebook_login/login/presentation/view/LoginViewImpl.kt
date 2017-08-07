package br.com.edsilfer.android_facebook_login.login.presentation.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import br.com.edsilfer.android_facebook_login.R
import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json.GraphAPISchema
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenter
import br.com.edsilfer.tookit.core.components.BaseActivity
import br.com.edsilfer.tookit.core.components.BasePresenter
import com.facebook.CallbackManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.login_view.*
import javax.inject.Inject

class LoginViewImpl : BaseActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter
    @Inject
    lateinit var callbackManager: CallbackManager

    private val buttonFacebookLogin by lazy { button_facebookLogin }

    override fun getPresenter(): BasePresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))

        buttonFacebookLogin.setReadPermissions(GraphAPISchema.ARG_USER_PERMISSIONS)
        buttonFacebookLogin.registerCallback(callbackManager, presenter)
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
}
