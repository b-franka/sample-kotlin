package com.swapp.swapplication.service

import org.springframework.stereotype.Service
import com.swapp.swapplication.model.Record
import mu.KotlinLogging


@Service
class RecordServiceImpl : RecordService {

    private val logger = KotlinLogging.logger {}

    private val records = listOf(
            Record("Expensive stuff", 680),
            Record("Some service", 205),
            Record("Some lux service", 2653),
            Record("You should have not order this", 5000),
            Record("Rice", 10)
    )

    override fun getAll(): List<Record> {
        logger.debug { "Accessing all records." }
        return records
    }
}
