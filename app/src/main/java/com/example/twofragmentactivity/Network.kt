package com.example.twofragmentactivity

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import java.util.concurrent.Executors

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(var appExecutors: AppExecutors) {

    private val moviesListLiveData = MediatorLiveData<Resource<ResultType>>()

    init {
        moviesListLiveData.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        moviesListLiveData.addSource(dbSource) { data ->
            moviesListLiveData.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                moviesListLiveData.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (moviesListLiveData.value != newValue) {
            moviesListLiveData.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        moviesListLiveData.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        moviesListLiveData.addSource(apiResponse) { response ->
            moviesListLiveData.removeSource(apiResponse)
            moviesListLiveData.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        saveCallResult(processResponse(response))
                        appExecutors.mainThread().execute {
                            moviesListLiveData.addSource(loadFromDb()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        moviesListLiveData.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    moviesListLiveData.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = moviesListLiveData as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}