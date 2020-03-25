package com.weatherapp.core.platform

import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val pending: AtomicBoolean = AtomicBoolean(false)
    private val observersMap: MutableMap<Observer<in T>, Observer<in T>> = mutableMapOf()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        }.also {
            observersMap[observer] = it
        })
    }

    override fun removeObserver(observer: Observer<in T>) {
        super.removeObserver(observersMap.getOrElse(observer) { observer })
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = value
    }

    @AnyThread
    fun postCall() {
        postValue(value)
    }
}