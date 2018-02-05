package com.ciazhar.emailscamchecker

import com.ciazhar.emailscamchecker.service.EmailSpamCheckerServiceImpl
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.RequestOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class MainVerticle : AbstractVerticle() {

    private val service by lazy { EmailSpamCheckerServiceImpl() }

    override fun start() {

        val router = Router.router(vertx)
        val client = vertx.createHttpClient()

        router.route().handler(BodyHandler.create())
        router.post("/api/check-score").handler { service.checkScore(it) }
        router.post("/api/check-spam").handler { service.checkSpam(it) }

        client.post(RequestOptions(config()))

        vertx.createHttpServer()
                .requestHandler({ router.accept(it) })
                .listen(8080, "127.0.0.1") { res ->
                    if (res.failed())
                        res.cause().printStackTrace()
                }
    }
}
