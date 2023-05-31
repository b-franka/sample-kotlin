package com.kanka.crochet.api

import com.kanka.crochet.model.Record
import com.kanka.crochet.service.RecordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class RecordController(private val recordService: RecordService): RecordApi {
    override fun getAllRecords(): ResponseEntity<List<Record>> {
        return ResponseEntity.ok(recordService.getAll())
    }
}
