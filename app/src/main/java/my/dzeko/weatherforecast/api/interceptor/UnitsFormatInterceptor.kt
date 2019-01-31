package my.dzeko.weatherforecast.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class UnitsFormatInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url()
            .newBuilder()
            .addQueryParameter("units", "metric")
            .build()

        val newRequest = originalRequest.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}