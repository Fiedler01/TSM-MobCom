package ch.bfh.ti.cloudexercise;

import java.util.Date;

class Message {
    private String content;
    private Date date;
    private int upvotes;

    /*
     * constructors
     */

    /* a public niladic constructor is needed for the deserializer */
    public Message() {
    }

    Message(String content, Date date, int upvotes) {
        this.content = content;
        this.date = date;
        this.upvotes = upvotes;
    }

    /*
     * getters - must be public for serializer
     */

    @SuppressWarnings("WeakerAccess")
    public String getContent() {
        return content;
    }

    @SuppressWarnings("WeakerAccess")
    public Date getDate() {
        return date;
    }

    @SuppressWarnings("WeakerAccess")
    public int getUpvotes() {
        return upvotes;
    }
}