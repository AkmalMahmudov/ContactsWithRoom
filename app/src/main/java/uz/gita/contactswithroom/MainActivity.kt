package uz.gita.contactswithroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.gita.contactswithroom.ui.screen.LoginFragment
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Executors.newSingleThreadExecutor().execute {
            Thread.sleep(2000)
            openLogin()
        }
    }

    private fun openLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, LoginFragment(), "Main")
            .commit()
    }

    override fun onPause() {
        super.onResume()
        openLogin()
    }
}