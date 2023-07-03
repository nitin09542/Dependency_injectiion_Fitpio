package com.example.dependency_injection

import android.app.Application
import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

interface One {
    fun getName()
}

class ImplementOne @Inject constructor(@RetrofitOne private val name: String) : One {
    override fun getName() {
        Log.d("Tag", "get on $name")
    }

}

class Main @Inject constructor(private val one: One) {
    fun getName() {
        one.getName()
    }

}
/*
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleClass{
    @Binds
    @Singleton
    abstract fun binding(implementOne: ImplementOne):One
}*/
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitOne

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitTwo

@Module
@InstallIn(ActivityComponent::class)
object LoginModule {
    @Provides
    @ActivityScoped
    fun provideLoginRepository(loginApi: LoginApi): LogInRepository {
        return LogInRepository(loginApi)
    }

}


/*@Module
@InstallIn(SingletonComponent::class)
 class AppModuleClass {

    @RetrofitOne
    @Provides
    fun myName(): String = "Jyant"

    @RetrofitTwo
    @Provides
    fun myScoolName(): String = "GalGotia's college"

    @Provides
    @Singleton
    fun binding(@RetrofitOne name: String): One=ImplementOne(name)


    @Provides
    @Singleton
    fun bindingSchool( @RetrofitTwo sname: String): MyNewInterface=GetSchoolName(sname)
    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }
}*/
