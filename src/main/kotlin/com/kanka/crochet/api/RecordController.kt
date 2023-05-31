package com.swapp.swapplication.api

import com.swapp.swapplication.model.Record
import com.swapp.swapplication.service.RecordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class RecordController(private val recordService: RecordService): RecordApi {
    override fun getAllRecords(): ResponseEntity<List<Record>> {
        return ResponseEntity.ok(recordService.getAll())
    }
}
