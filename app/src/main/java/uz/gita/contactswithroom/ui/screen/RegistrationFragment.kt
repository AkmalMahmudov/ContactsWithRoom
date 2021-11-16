package uz.gita.contactswithroom.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactswithroom.R
import uz.gita.contactswithroom.contract.RegistrationContract
import uz.gita.contactswithroom.databinding.ScreenRegBinding
import uz.gita.contactswithroom.model.RegistrationRepository
import uz.gita.contactswithroom.presenter.RegPresenter

class RegistrationFragment : Fragment(R.layout.screen_reg), RegistrationContract.View {
    private lateinit var presenter: RegPresenter
    private val binding by viewBinding(ScreenRegBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = RegPresenter(RegistrationRepository(), this)
        binding.butReg.setOnClickListener { presenter.clickReg() }
        requireActivity().title = "Registration"
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun moveLogIn() {
       parentFragmentManager.beginTransaction()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, LoginFragment())
            .commit()
    }

    override fun getLogin(): String {
        return binding.username.text.toString()
    }

    override fun getPassword(): String {
        return binding.password.text.toString()
    }

    override fun getConfirmPassword(): String {
        return binding.repit.text.toString()
    }
}