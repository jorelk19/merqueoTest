package com.merqueo.di

import com.merqueo.businessmodels.api.MovieApi
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * CLass used to manage the network access from apis
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 **/
class NetworkModule(private val movieApiUrl: String) {
    companion object {
        const val okHttpClientWithoutInterceptor = "defaultOkHttpClient"
        const val retrofitMovie = "retrofitMovie"
        private const val DEFAULT_TIME_OUT = 60L
    }

    /**
     * Initialize the module withe the respective retrofit information
     * */
    fun initModule(): Module {
        return module {

            // Retrofit and OkHttpClient instances
            single(named(okHttpClientWithoutInterceptor)) { provideDefaultOkHttpClient() }
            single(named(retrofitMovie)) { provideRetrofitMovieClient(get(named(okHttpClientWithoutInterceptor))) }
            // API
            single { provideMovieApi(get(named(retrofitMovie))) }
        }
    }

    /**
     * OkHttpClient without Interceptor
     */
    private fun provideDefaultOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Retrofit movie clients
     */
    private fun provideRetrofitMovieClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(movieApiUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}

/**
 * Movie api provider
 * */
private fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

