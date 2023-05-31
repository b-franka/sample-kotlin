package com.kanka.crochet.service

import com.kanka.crochet.model.Record
import org.springframework.core.io.Resource

interface InvoiceService {
    fun generateInvoice(records: List<Record>): String
    fun getInvoiceBy(docName: String): Resource
}
