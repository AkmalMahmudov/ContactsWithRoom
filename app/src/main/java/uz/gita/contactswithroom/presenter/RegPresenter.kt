package uz.gita.contactswithroom.presenter

import android.os.Handler
import android.os.Looper
import uz.gita.contactswithroom.contract.RegistrationContract
import uz.gita.contactswithroom.room.UserData
import java.util.concurrent.Executors

class RegPresenter(
    private var model: RegistrationContract.Model,
    private var view: RegistrationContract.View
) : RegistrationContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())
    override fun clickReg() {
        if (view.getLogin().length <= 4 || view.getLogin().length >= 10) {
            view.showMessage("the length of login must be between 4 and 10")
        } else if (view.getPassword().length <= 4 || view.getPassword().length >= 10) {
            view.showMessage("the length of password must be between 4 and 10")
        } else if (view.getConfirmPassword().length <= 4 || view.getConfirmPassword().length >= 10) {
            view.showMessage("the length of password must be between 4 and 10")
        } else if (view.getPassword() != view.getConfirmPassword()) {
            view.showMessage("password and confirm password must be the same")
        } else {
            val user = UserData(0, view.getLogin(), view.getPassword())
            runOnWorkerThread {
            model.insertUser(user)
            }
            view.showMessage("You're registered")
            view.moveLogIn()
        }
    }
    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }
}