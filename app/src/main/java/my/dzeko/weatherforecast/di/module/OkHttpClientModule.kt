package my.dzeko.weatherforecast.di.module

import dagger.Module
import dagger.Provides
import my.dzeko.weatherforecast.api.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class OkHttpClientModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor) : OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(apiKeyInterceptor)
        return builder.build()
    }
}