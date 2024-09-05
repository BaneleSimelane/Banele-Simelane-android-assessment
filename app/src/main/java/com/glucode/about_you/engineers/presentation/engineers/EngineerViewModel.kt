package com.glucode.about_you.engineers.presentation.engineers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glucode.about_you.engineers.domain.usecase.GetEngineers
import com.glucode.about_you.engineers.domain.util.EngineerOrder
import com.glucode.about_you.engineers.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EngineerViewModel @Inject constructor(
    private val getEngineers: GetEngineers
) : ViewModel() {

    var state by mutableStateOf(EngineerState())
        private set

    private val eventChannel = Channel<EngineerEvent>()
    val event = eventChannel.receiveAsFlow()

    private var getEngineerJob: Job? = null

    init {
        getEngineers(EngineerOrder.Years(OrderType.Descending)) //by default
    }

    fun onAction(action: EngineerAction) {
        when(action) {
            is EngineerAction.OnEngineerClick -> {
                viewModelScope.launch {
                    eventChannel.send(EngineerEvent.NavigateToAbout(
                        selectedName = action.selectedName
                    ))
                }
            }
            is EngineerAction.Order -> {
                if (state.engineerOrder.orderType::class == action.engineerOrder.orderType::class
                    && state.engineerOrder::class == action.engineerOrder::class ) {
                    return
                }
                getEngineers(action.engineerOrder)
            }
            else -> Unit
        }
    }

    private fun getEngineers(order: EngineerOrder) {
        viewModelScope.launch {
            getEngineerJob?.cancel()

            getEngineerJob = getEngineers.invoke(order)
                .onEach { engineers ->
                    state = state.copy(
                        engineers = engineers,
                        engineerOrder = order
                    )
                }.launchIn(this)
        }
    }
}