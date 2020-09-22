package ch.bfh.ti.cloudexercise;

class KeyMessagePair {
    private final String key;
    private final Message message;

    KeyMessagePair(String key, Message message) {
        this.key = key;
        this.message = message;
    }

    String getKey() {
        return key;
    }

    Message getMessage() {
        return message;
    }
}
