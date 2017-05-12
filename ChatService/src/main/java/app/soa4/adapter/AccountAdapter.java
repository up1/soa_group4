package app.soa4.adapter;

import app.soa4.model.Account;
import org.springframework.web.client.RestTemplate;

public class AccountAdapter {
    public String getAccountName(int userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://128.199.246.103:9008/AccountProfile/" + userId;
        Account account = restTemplate.getForObject(url, Account.class);
        String name = account.getAccount_name();
        return name;
    }
}
