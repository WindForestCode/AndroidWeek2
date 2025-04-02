package com.myschool.app2.util

interface Callback<T> {

    fun onSuccess(data: T)

    fun onError(throwable: Throwable)
}