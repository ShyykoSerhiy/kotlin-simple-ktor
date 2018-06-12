package com.ktlin

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.DevelopmentEngine.main(args)



fun Application.main() {
	var count = 0 // ignore concu

	routing {
		post("/api/count") {
			synchronized(this) {
				count++
				call.respondText(count.toString(), contentType = ContentType.Text.Plain)
			}
		}

		// Static feature. Try to access `/ktor_logo.svg`
		static("") {
			resources("static")
			default("index.html")
		}
	}
}

