package uz.gita.contactswithroom.presenter

import android.os.Handler
import android.os.Looper
import uz.gita.contactswithroom.contract.LogInContract
import java.util.concurrent.Executors

class LogInPresenter(
    private var model: LogInContract.Model,
    private var view: LogInContract.View
) : LogInContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())


    override fun clickRegistr() {
        view.moveRegistrationScreen()
    }

    override fun clickLogIn() {
        when {
            view.getLogin().length !in 4..10 -> {
                view.showMessage("the length of login must be between 4 and 10")
                return
            }
            view.getPassword().length !in 4..10 -> {
                view.showMessage("the length of password must be between 4 and 10")
                return
            }
            else -> {
                runOnWorkerThread {
                    val ls = model.getUsers()
                    runOnUIThread {
                        for (element in ls) {
                            if (view.getLogin() == element.login && view.getPassword() == element.password) {
                                view.showMessage("welcome")
                                view.moveUserScreen(element.id)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }
}