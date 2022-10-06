package com.taknikiniga.internetspeedtest.fragments.history.model

import java.util.*

sealed class InternetSpeedModel{
    data class InternetHistoryModel(
        val date: Long,
        val download: String,
        val uploadSpeed: String,
        val ping: String,
    ):InternetSpeedModel()
}
