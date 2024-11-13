package com.example.compose.presentation.base


/**
 * Time capsule interface which serves as a holder for the different states a screen will have
 * during its lifetime.
 *
 *
 * @param State The type of [Reducer.ViewState] to store
 */
interface TimeCapsule<State : Reducer.ViewState> {
    fun addState(state: State)
    fun selectState(position: Int)
    fun getStates(): List<State>
}

/**
 * Time travel capsule implementing the [TimeCapsule] interface.
 *
 * @param State The type of [Reducer.ViewState] to store
 * @property onStateSelected Lambda function to call when debugging to retrieve stored states
 */
class TimeCapsuleImpl<State : Reducer.ViewState>(
    private val onStateSelected: (State) -> Unit
) : TimeCapsule<State> {

    private val states = mutableListOf<State>()

    override fun addState(state: State) {
        states.add(state)
    }

    override fun selectState(position: Int) {
        onStateSelected(states[position])
    }

    override fun getStates(): List<State> {
        return states
    }
}