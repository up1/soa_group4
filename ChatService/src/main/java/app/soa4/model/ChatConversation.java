package app.soa4.model;

public class ChatConversation {
    int c_id;
    int user_one;
    int user_two;
    String name;
    String channel;
    double time;
    int status;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getUser_one() {
        return user_one;
    }

    public void setUser_one(int user_one) {
        this.user_one = user_one;
    }

    public int getUser_two() {
        return user_two;
    }

    public void setUser_two(int user_two) {
        this.user_two = user_two;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
