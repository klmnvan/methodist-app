package com.example.prodjectformc.ui.screen.createevent.participation

import androidx.lifecycle.ViewModel
import com.example.prodjectformc.data.network.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ParticipationViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {



}