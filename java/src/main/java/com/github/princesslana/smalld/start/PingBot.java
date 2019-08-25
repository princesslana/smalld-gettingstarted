package com.github.princesslana.smalld.start;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.github.princesslana.smalld.SmallD;

public class PingBot {
  public static void main(String[] args) {
    try (SmallD smalld = SmallD.create(System.getProperty("smalld.token"))) {
      smalld.onGatewayPayload(
          p -> {
            JsonObject json = Json.parse(p).asObject();

            if (isMessageCreate(json) && getMessageContent(json).equals("++ping")) {
              String channelId = getChannelId(json);

              sendPong(smalld, channelId);
            }
          });
      smalld.run();
    }
  }

  private static boolean isMessageCreate(JsonObject json) {
    return json.getInt("op", -1) == 0 && json.getString("t", "").equals("MESSAGE_CREATE");
  }

  private static String getMessageContent(JsonObject json) {
    return json.get("d").asObject().getString("content", "");
  }

  private static String getChannelId(JsonObject json) {
    return json.get("d").asObject().getString("channel_id", "");
  }

  private static void sendPong(SmallD smalld, String channelId) {
    smalld.post(
        "/channels/" + channelId + "/messages",
        Json.object().add("content", "pong").toString());
  }
}
