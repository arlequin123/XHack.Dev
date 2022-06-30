package com.example.xhackdev.utils

import com.example.xhackdev.data.models.NewMessageDto
import com.example.xhackdev.utils.Constants.BASE_URL
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException
import java.util.*
import javax.inject.Inject

object SocketHandler {

    private var mSocket: Socket? = null
    private var token = ""
    val gson: Gson = Gson()

    @Synchronized
    fun setSocket(authToken: String): Boolean {
        return try {
            token = authToken
            val headers = mapOf(Pair("Authorization", listOf("Bearer $authToken")))
            val options = IO.Options.builder().setExtraHeaders(headers).build()

            mSocket = IO.socket(BASE_URL, options)
            mSocket?.on("connect", onConnect)
            mSocket?.on(IoIncomingEvents.NEW_MESSAGE.name, onNewMessage)
            true
        } catch (e: URISyntaxException) {
            false
        }
    }


    @Synchronized
    fun connect() {
        mSocket?.connect()
    }

    @Synchronized
    fun disconnect() {
        mSocket?.disconnect()
        mSocket?.off("connect", onConnect)
        mSocket?.off(IoIncomingEvents.NEW_MESSAGE.name, onNewMessage)
    }


    private var onConnect = Emitter.Listener {
        mSocket?.emit(IoOutgoingEvents.AUTHORIZE.name, token)
    }


    private var onNewMessage = Emitter.Listener { args ->
        val message = gson.fromJson( args.first() as String, NewMessageDto::class.java)
    }


     enum class IoOutgoingEvents(name: String) {
        AUTHORIZE("Authorize"),
        SEND_MESSAGE("SendMessage"),
        DELETE_MESSAGE("DeleteMessage"),
        READ_MESSAGE("ReadMessage")
    }

    enum class IoIncomingEvents(name: String) {
        NEW_MESSAGE("NewMessage"),
        READ_CHAT("ReadChat"),
        CHAT_REMOVED("ChatRemoved"),
        CHAT_LEAVED("ChatLeaved")
    }
}