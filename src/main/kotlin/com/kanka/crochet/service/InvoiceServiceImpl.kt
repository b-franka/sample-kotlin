package com.swapp.swapplication.service

import com.lowagie.text.*
import com.lowagie.text.alignment.HorizontalAlignment
import com.swapp.swapplication.model.Record
import org.springframework.stereotype.Service
import java.io.FileOutputStream
import com.lowagie.text.pdf.PdfWriter;
import com.swapp.swapplication.exception.DocumentNotFoundException
import mu.KotlinLogging
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.awt.Color
import java.io.File

@Service
class InvoiceServiceImpl: InvoiceService {

    private val logger = KotlinLogging.logger {}

    private val storageDir = "./"

    override fun generateInvoice(records: List<Record>): String {
        return createDocument(records)
    }

    override fun getInvoiceBy(docName: String): Resource {
        try {
            return InputStreamResource(File("${storageDir}${docName}.pdf").inputStream())
        } catch (ex: Exception) {
            logger.debug { "Could not find document. docName: $docName" }
            throw DocumentNotFoundException("Can't find document with name: $docName.")
        }
    }

    private fun createDocument(records: List<Record>): String {
        val currentTimestamp = System.currentTimeMillis()
        val docName = "generated_invoice_${currentTimestamp}"
        val pdfOutputFile = FileOutputStream("${storageDir}${docName}.pdf")
        val invoiceDoc = Document()
        val pdfWriter = PdfWriter.getInstance(invoiceDoc, pdfOutputFile)

        val invoiceContentTable = getDefaultTable()

        listOf("Name", "Amount").forEach {
            val current = Cell(Phrase(it)).apply{
                isHeader = true
                backgroundColor = Color.LIGHT_GRAY
            }
            invoiceContentTable.addCell(current)
        }

        logger.info { "Creating invoice from ${records.size} records." }

        records.forEach { invoiceContentTable.apply {
                addRow(this, it.name, it.amount.toString())
            }
        }

        invoiceContentTable.apply { addRow(this, "Total", records.sumOf { it.amount }.toString()) }

        invoiceDoc.apply {
            open()
            add(invoiceContentTable)
            close()
        }
        pdfWriter.close()

        logger.info { "Successfully saved document: $docName" }

        val currentContextPath = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()
        return "${currentContextPath}/api/invoice/${docName}"
    }

    private fun addRow(table: Table, leftCell: String, rightCell: String) {
        // TODO: totally not working
        table.apply {
            addCell(Cell(Phrase(leftCell))).apply {
                setHorizontalAlignment(HorizontalAlignment.LEFT)
                borderColor = Color.WHITE
            }
            addCell(Cell(Phrase(rightCell))).apply { setHorizontalAlignment(HorizontalAlignment.RIGHT) }
        }
    }

    private fun getDefaultTable(): Table {
        return Table(2).apply {
            padding = 2f
            spacing = 1f
            width = 100f
            borderColor = Color.WHITE
        }
    }
}
