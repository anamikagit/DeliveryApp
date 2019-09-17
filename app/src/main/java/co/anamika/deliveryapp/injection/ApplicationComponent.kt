package co.anamika.deliveryapp.injection

import android.app.Application
import co.anamika.deliveryapp.DeliveryApplication
import co.anamika.deliveryapp.injection.module.ApplicationModule
import co.anamika.deliveryapp.injection.module.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, UiModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: DeliveryApplication)
}