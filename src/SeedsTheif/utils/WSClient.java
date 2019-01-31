package SeedsTheif.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WSClient {
    private static Request request = null;
    private static EchoWebSocketListener listener = null;
    private static WebSocket ws = null;
    private static final OkHttpClient client = new OkHttpClient();

    public static void sendMessage(String text) {
        if (request == null) request = new Request.Builder().url("ws://localhost:5000").build();
        if (listener == null) listener = new EchoWebSocketListener();
        if (ws == null) ws = client.newWebSocket(request, listener);
        ws.send(text);
    }
}
