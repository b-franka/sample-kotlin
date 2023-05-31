package com.kanka.crochet.service

import com.kanka.crochet.model.Record

interface RecordService {
    fun getAll(): List<Record>
}
