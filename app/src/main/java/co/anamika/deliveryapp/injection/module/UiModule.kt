package co.anamika.deliveryapp.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import co.anamika.deliveryapp.api.DeliveryService
import co.anamika.deliveryapp.database.DeliveryDatabase
import co.anamika.deliveryapp.ui.OrderListActivity
import co.anamika.deliveryapp.viewModel.ViewModelFactory
import co.anamika.deliveryapp.viewModel.OrderListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class UiModule {
    @Binds
    @IntoMap
    @ViewModelKey(OrderListViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: OrderListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): OrderListActivity


    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideDatabase(context: Context): DeliveryDatabase {
            return DeliveryDatabase.getInstance(context)
        }

        @Provides
        @JvmStatic
        fun provideDeliverieService(): DeliveryService {
            return DeliveryService.create()
        }
    }

}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)