package com.example.prodjectformc.ui.screen.createevent.publication

import androidx.lifecycle.ViewModel
import com.example.prodjectformc.data.network.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

}