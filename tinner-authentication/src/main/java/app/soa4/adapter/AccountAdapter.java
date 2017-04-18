package app.soa4.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountAdapter {
    private final String URL = "";
    public Account getAccountDetail(String username){
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.getForObject(URL+username , Account.class);
    }
}
