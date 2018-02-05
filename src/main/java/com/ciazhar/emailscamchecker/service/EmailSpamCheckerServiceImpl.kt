package com.ciazhar.emailscamchecker.service

import com.ciazhar.emailscamchecker.CheckScoreForm
import com.ciazhar.emailscamchecker.CheckSpamForm
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext

/**
 * Created by ciazhar on 05/02/18.
 * [ Documentatiion Here ]
 */


class EmailSpamCheckerServiceImpl : EmailSpamCheckerService{

    private val vertx = Vertx.vertx()

    override fun checkScore(ctx: RoutingContext) {
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

    override fun checkSpam(ctx: RoutingContext) {
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

}