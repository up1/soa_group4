package app.soa4.model;

/**
 * Created by Boeing on 5/1/2017.
 */
public class ChatNotification {
    private int sender_id;
    private int reciever_id;
    private double time;
    private int status;


    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReciever_id() {
        return reciever_id;
    }

    public void setReciever_id(int reciever_id) {
        this.reciever_id = reciever_id;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
