package com.thoughtworks.archguard.aac.socket

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.archguard.aaac.api.InterpreterRequest
import org.archguard.aaac.repl.ArchdocInterpreter
import org.springframework.stereotype.Controller
import javax.websocket.OnClose
import javax.websocket.OnError
import javax.websocket.OnMessage
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint

object ReplService {
    var interpreter: ArchdocInterpreter = ArchdocInterpreter()
}

@ServerEndpoint(value = "/ascode")
@Controller
class AsCodeSocketController {
    private lateinit var replServer: ArchdocInterpreter

    @OnOpen
    fun onOpen(session: Session) {
        replServer = ReplService.interpreter
    }

    @OnClose
    fun onClose(session: Session) {
    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        val request: InterpreterRequest = Json.decodeFromString(message)
        val result = replServer.eval(request)
        session.asyncRemote.sendText(Json.encodeToString(result))
    }

    @OnError
    fun onError(session: Session?, error: Throwable) {
    }
}
