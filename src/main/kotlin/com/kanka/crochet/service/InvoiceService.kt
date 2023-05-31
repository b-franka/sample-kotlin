package com.swapp.swapplication.service

import com.swapp.swapplication.model.Record
import org.springframework.core.io.Resource

interface InvoiceService {
    fun generateInvoice(records: List<Record>): String
    fun getInvoiceBy(docName: String): Resource
}
