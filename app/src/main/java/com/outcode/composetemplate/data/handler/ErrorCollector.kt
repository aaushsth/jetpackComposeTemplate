package com.outcode.myapplication.data.handler

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.RetentionManager
import com.outcode.composetemplate.App


val chuckerCollector: ChuckerCollector by lazy {
    ChuckerCollector(App.instance, true, RetentionManager.Period.ONE_HOUR)
}

