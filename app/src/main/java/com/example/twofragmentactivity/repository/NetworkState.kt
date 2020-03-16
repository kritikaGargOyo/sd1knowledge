package com.example.twofragmentactivity.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val message: String) {

    companion object {
        val END_OF_LIST: NetworkState
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState


        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Runnig")
            ERROR = NetworkState(Status.FAILED,"ERROR")
            END_OF_LIST = NetworkState(Status.FAILED , "End")
        }
    }
}