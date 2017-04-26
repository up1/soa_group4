package app.soa4.model;

/**
 * Created by ARMY on 4/16/2017.
 */
public class CreateNotification {

    private int account_id1;
    private int account_id2;
    private int read_status;

    public int getAccount_id1() {return account_id1;}

    public void setAccount_id1(int account_id1) {
        this.account_id1 = account_id1;
    }

    public int getAccount_id2() {
        return account_id2;
    }

    public void setAccount_id2(int account_id2) {
        this.account_id2 = account_id2;
    }

    public int getRead_status() {
        return read_status;
    }

    public void setRead_status(int read_status) {
        this.read_status = read_status;
    }
}
