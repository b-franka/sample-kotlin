package com.swapp.swapplication.service

import com.swapp.swapplication.model.Record

interface RecordService {
    fun getAll(): List<Record>
}
