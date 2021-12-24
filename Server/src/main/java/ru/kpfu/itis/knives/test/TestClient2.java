package ru.kpfu.itis.knives.test;

import ru.kpfu.itis.knives.protocol.Message;
import ru.kpfu.itis.knives.protocol.Protocol;

import java.io.IOException;

public class TestClient2 {

    public static void main(String[] args) {
        try {
            TestClient client2 = new TestClient(Protocol.PORT);
            client2.send31Message();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
