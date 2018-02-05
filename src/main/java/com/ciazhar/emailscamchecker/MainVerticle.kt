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

        val form = Json.decodeValue(ctx.bodyAsString, CheckScoreForm::class.java)

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
            .end(Json.encode(form))
    }

    private fun checkSpam(ctx: RoutingContext){

        val client = vertx.createHttpClient()

        val form = Json.decodeValue(ctx.bodyAsString, CheckSpamForm::class.java)

        client.postAbs("https://oopspam.herokuapp.com", { res ->
            res.bodyHandler {
                ctx.response()
                        .setStatusCode(201)
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(it.toJsonObject().encodePrettily())
            }
        })
                .putHeader("content-type","application/json; charset=utf-8")
                .setChunked(true)
                .end(Json.encode(form))

    }

    override fun start() {

        val router = Router.router(vertx)
        val client = vertx.createHttpClient()

        router.route().handler(BodyHandler.create())
        router.post("/api/check-score").handler { this.checkScore(it) }
        router.post("/api/check-spam").handler { this.checkSpam(it) }

        client.post(RequestOptions(config()))

        vertx.createHttpServer()
                .requestHandler({ router.accept(it) })
                .listen(8080, "127.0.0.1") { res ->
                    if (res.failed())
                        res.cause().printStackTrace()
                }
    }
}
