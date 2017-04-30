package app.soa4.model;

public class TokenUser {
    private String Username;
    private Long id;

    public TokenUser(String username, Long id) {
        Username = username;
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
