package com.trinity.a20201031_marcregistre_nycschools.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trinity.a20201031_marcregistre_nycschools.viewmodel.HighSchoolViewModel
import com.trinity.a20201031_marcregistre_nycschools.viewmodel.SatScoresViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@SuppressWarnings("unchecked")
@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HighSchoolViewModel::class)
    internal abstract fun postHighSchoolViewModel(viewModel: HighSchoolViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SatScoresViewModel::class)
    internal abstract fun postSatViewModel(scoresViewModel: SatScoresViewModel): ViewModel

}
