package id.tru.kmm.phonecheckexample.android.login
import android.content.Context
import android.content.OperationApplicationException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.tru.kmm.phonecheckexample.android.model.PhoneCheckDataSource
import id.tru.kmm.phonecheckexample.android.model.PhoneCheckRepository

/**
 * ViewModel provider factory to instantiate VerifyViewModel.
 * Required given VerifyViewModel has a non-empty constructor
 */
class VerifyViewModelFactory(applicationContext: Context) : ViewModelProvider.Factory {
    val context = applicationContext
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PhoneCheckViewModel::class.java)) {
            return PhoneCheckViewModel( PhoneCheckRepository(PhoneCheckDataSource(context)) ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}