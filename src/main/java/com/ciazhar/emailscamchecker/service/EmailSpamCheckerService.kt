package com.ciazhar.emailscamchecker.service

import io.vertx.ext.web.RoutingContext

/**
 * Created by ciazhar on 05/02/18.
 * [ Documentatiion Here ]
 */
interface EmailSpamCheckerService {
    fun checkScore(ctx : RoutingContext)
    fun checkSpam(ctx : RoutingContext)
}