package com.example.composestartproject.presentation.base


interface Reducer<State : Reducer.ViewState, Event : Reducer.ViewEvent, Effect : Reducer.ViewEffect> {

    //Managing Screen State
    interface ViewState


    //Managing ViewModel event
    interface ViewEvent


    //Managing Nav,Snack,Toast
    interface ViewEffect

    fun reduce(previousState: State, event: Event): Pair<State, Effect?>
}

