package ru.kpfu.itis.knives.listeners.errors;

import ru.kpfu.itis.knives.listeners.AbstractClientMessageListener;
import ru.kpfu.itis.knives.protocol.Message;

import static ru.kpfu.itis.knives.protocol.Protocol.ERROR_WRONG_MOVE;
import static ru.kpfu.itis.knives.protocol.Protocol.ERROR_WRONG_POS;

public class ErrorWrongPosListener extends AbstractClientMessageListener {

    protected ErrorWrongPosListener() {
        super(ERROR_WRONG_POS);
    }

    @Override
    public void handleMessage(Message message) {
        if (message.getType() == this.getType()) {
            String information = new String(message.getData());
            client.setNotice(information);
            client.choiceRegion();
        }
    }
}
