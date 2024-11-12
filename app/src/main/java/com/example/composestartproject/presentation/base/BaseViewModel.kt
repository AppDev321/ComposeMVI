package com.example.composestartproject.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State : Reducer.ViewState, Event : Reducer.ViewEvent, Effect : Reducer.ViewEffect>(
    initialState: State,
    private val reducer: Reducer<State, Event, Effect>
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()


    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()


    private val _effect = Channel<Effect>(capacity = Channel.CONFLATED)
    val effect = _effect.receiveAsFlow()

    private val timeCapsule: TimeCapsule<State> = TimeCapsuleImpl { storedState ->
        _state.tryEmit(storedState)
    }

    init {
        timeCapsule.addState(initialState)
    }


    fun sendEffect(effect: Effect) {
        _effect.trySend(effect)
    }

    fun sendEvent(event: Event) {
        val (newState, newEffect) = reducer.reduce(_state.value, event)
        val success = _state.tryEmit(newState)
        if (success) {
            timeCapsule.addState(newState)
        }

    }


    fun sendEventForEffect(event: Event) {
        val (newState, newEffect) = reducer.reduce(_state.value, event)
        val success = _state.tryEmit(newState)
        if (success) {
            timeCapsule.addState(newState)
        }
        newEffect?.let {
            sendEffect(it)
        }
    }


    fun selectTimeState(position: Int) {
        timeCapsule.selectState(position)
    }

    fun getTimeStates(): List<State> {
        return timeCapsule.getStates()
    }
}