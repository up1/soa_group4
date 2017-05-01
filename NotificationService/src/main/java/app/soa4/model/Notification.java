package app.soa4.model;



public class Notification {
    private Integer noti_id;
    private Integer accountId_1;
    private Integer accountId_2;
    private Integer chat_id;

    public Integer getNoti_id() {
        return noti_id;
    }

    public void setNoti_id(Integer noti_id) {
        this.noti_id = noti_id;
    }

    public Integer getAccountId_1() {
        return accountId_1;
    }

    public void setAccountId_1(Integer accountId_1) {
        this.accountId_1 = accountId_1;
    }

    public Integer getAccountId_2() {
        return accountId_2;
    }

    public void setAccountId_2(Integer accountId_2) {
        this.accountId_2 = accountId_2;
    }

    public Integer getChat_id() {
        return chat_id;
    }

    public void setChat_id(Integer chat_id) {
        this.chat_id = chat_id;
    }
}
