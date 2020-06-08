package com.github.princesslana.smalld.start

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.github.princesslana.smalld.SmallD

fun main() {
    SmallD.create(System.getProperty("smalld.token")).use { smalld ->
        smalld.onGatewayPayload { handlePingExecution(smalld, it) }
        smalld.run()
    }
}

private fun handlePingExecution(smalld: SmallD, payload: String) {
    val json = Json.parse(payload).asObject() ?: return
    if (isMessageCreate(json) && getMessageContent(json) == "++ping") {
        val channelId = getChannelId(json) ?: return
        smalld.sendPong(channelId)
    }
}

private fun isMessageCreate(json: JsonObject): Boolean {
    return with(json) { getInt("op", -1) == 0 && getString("t", "") == "MESSAGE_CREATE" }
}

private fun getMessageContent(json: JsonObject): String {
    return json["d"].asObject().getString("content", "")
}

private fun getChannelId(json: JsonObject): String? {
    return json["d"].asObject().getString("channel_id", null)
}

private fun SmallD.sendPong(channelId: String) {
    post("/channels/$channelId/messages", JsonObject().add("content", "pong").toString())
}