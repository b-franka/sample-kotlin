package com.kanka.crochet.api

import com.kanka.crochet.model.Record
import com.kanka.crochet.service.InvoiceService
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class InvoiceController(private val invoiceService: InvoiceService): InvoiceApi {
    override fun generateInvoice(record: List<Record>
    ): ResponseEntity<String> {
        return ResponseEntity.ok(invoiceService.generateInvoice(record))
    }

    override fun getGeneratedInvoice(documentName: String): ResponseEntity<Resource> {
        return ResponseEntity.ok(invoiceService.getInvoiceBy(documentName))
    }
}
