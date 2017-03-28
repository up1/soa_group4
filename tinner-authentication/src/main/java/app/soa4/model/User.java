package app.soa4.model;

/**
 * Created by DSJIN on 27/3/2560.
 */
public class User {
    private String Username;
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
