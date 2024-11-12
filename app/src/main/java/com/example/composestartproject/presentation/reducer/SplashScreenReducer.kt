package com.example.composestartproject.presentation.reducer

import androidx.compose.runtime.Immutable
import com.example.composestartproject.presentation.base.Reducer


@Immutable
sealed class SplashEvent : Reducer.ViewEvent {
    data class SplashLoading(val isLoading: Boolean) : SplashEvent()
    data class UpdateSplashData(val data: String) : SplashEvent()
}

@Immutable
sealed class SplashEffect : Reducer.ViewEffect {
    data object NavigateToMainActivity : SplashEffect()
    data class ShowSnackBar(val message: String) : SplashEffect()
}

@Immutable
data class SplashState(
    val isLoading: Boolean,
    val data: String
) : Reducer.ViewState {
    companion object {
        fun initial(): SplashState {
            return SplashState(isLoading = false, data = "Starting point")
        }
    }
}


class SplashScreenReducer : Reducer<SplashState, SplashEvent, SplashEffect> {
    override fun reduce(
        previousState: SplashState,
        event: SplashEvent
    ): Pair<SplashState, SplashEffect?> {
        return when (event) {
            is SplashEvent.SplashLoading -> {
                previousState.copy(isLoading = event.isLoading) to null
            }

            is SplashEvent.UpdateSplashData -> {
                val updatedState = previousState.copy(
                    data = event.data
                )
                val effect = SplashEffect.ShowSnackBar(message = updatedState.data)
                updatedState to effect
            }
        }
    }
}
