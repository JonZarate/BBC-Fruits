package com.jonzarate.bbc_fruits

import com.jonzarate.bbc_fruits.data.net.AnalyticsApi
import com.jonzarate.bbc_fruits.data.net.FruitsApi
import com.jonzarate.bbc_fruits.data.net.RetrofitHelper
import com.jonzarate.bbc_fruits.data.repo.AppRepository
import com.jonzarate.bbc_fruits.data.repo.FruitRepository
import com.jonzarate.bbc_fruits.view.detail.DetailViewModelFactory
import com.jonzarate.bbc_fruits.view.list.ListViewModelFactory

object Injector {

    @Volatile
    private var analyticsApi: AnalyticsApi? = null
    @Volatile
    private var fruitsApi: FruitsApi? = null

    @Volatile
    private var appRepo: AppRepository? = null
    @Volatile
    private var fruitRepo: FruitRepository? = null

    /*
        Retrofit API
     */

    private fun getAnalyticsApi(): AnalyticsApi =
        analyticsApi ?: synchronized(this) {
            RetrofitHelper.newAnalyticsApiInstance(getBaseUrl()).also { analyticsApi = it }
        }

    private fun getFruitsApi(): FruitsApi =
        fruitsApi ?: synchronized(this) {
            RetrofitHelper.newFruitsApiInstance(getBaseUrl()).also { fruitsApi = it }
        }

    /*
        Repositories
     */

    fun getAppRepository() : AppRepository =
        appRepo ?: synchronized(this) {
            AppRepository(getAnalyticsApi()).also { appRepo = it }
        }

    private fun getFruitRepository() : FruitRepository =
        fruitRepo ?: synchronized(this) {
            FruitRepository(getFruitsApi(), getAppRepository()).also { fruitRepo = it }
        }

    /*
        ViewModelProviders
     */

    fun getListViewModelFactory() =
        ListViewModelFactory(getFruitRepository(), getAppRepository())

    fun getDetailViewModelFactory() =
        DetailViewModelFactory(getAppRepository())

    /*
        Utils
     */

    private fun getBaseUrl() = BuildConfig.BASE_URL
}