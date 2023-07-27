package com.kanka.crochet.api

import com.kanka.crochet.model.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(): HelloApi {
    override fun sayHello(name: String): ResponseEntity<Response> {
        return ResponseEntity.ok(Response("Hello bello $name"))
    }
}
