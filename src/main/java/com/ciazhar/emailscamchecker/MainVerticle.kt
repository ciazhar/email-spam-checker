package com.ciazhar.emailscamchecker

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.RequestOptions
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler

class MainVerticle : AbstractVerticle() {

    private fun checkScore(ctx : RoutingContext){

        val client = vertx.createHttpClient()

        val mail = Json.decodeValue(ctx.bodyAsString,Email::class.java)

        client.postAbs("https://spamcheck.postmarkapp.com/filter", { res ->
            res.bodyHandler {
                ctx.response()
                    .setStatusCode(201)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(it.toJsonObject().encodePrettily())
            }
        })
            .putHeader("content-type","application/json; charset=utf-8")
            .setChunked(true)
            .end(Json.encode(mail))
    }

    override fun start() {

        val router = Router.router(vertx)
        val client = vertx.createHttpClient()

        router.route().handler(BodyHandler.create())
        router.post("/api/check").handler { this.checkScore(it) }

        client.post(RequestOptions(config()))

        vertx.createHttpServer()
                .requestHandler({ router.accept(it) })
                .listen(8080, "127.0.0.1") { res ->
                    if (res.failed())
                        res.cause().printStackTrace()
                }
    }
}
