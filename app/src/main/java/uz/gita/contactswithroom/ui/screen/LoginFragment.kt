package uz.gita.contactswithroom.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactswithroom.R
import uz.gita.contactswithroom.contract.LogInContract
import uz.gita.contactswithroom.databinding.ScreenLoginBinding
import uz.gita.contactswithroom.model.LogInRepository
import uz.gita.contactswithroom.presenter.LogInPresenter

class LoginFragment : Fragment(R.layout.screen_login), LogInContract.View {
    private lateinit var presenter: LogInPresenter
    private val binding by viewBinding(ScreenLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = LogInPresenter(LogInRepository(), this)
        binding.signup.setOnClickListener { presenter.clickRegistr() }
        binding.btnLogin.setOnClickListener { presenter.clickLogIn() }
        requireActivity().title = "Contacts"
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun moveRegistrationScreen() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, RegistrationFragment())
            .commit()
    }

    override fun moveUserScreen(id: Long) {
        //putarguments
        val bundle = Bundle()
        bundle.putLong("id", id)

        val userFragment = UserFragment()
        userFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, userFragment)
            .commit()
    }

    override fun getLogin(): String {
        return binding.username.text.toString()
    }

    override fun getPassword(): String {
        return binding.password.text.toString()
    }
}