package app.model;

public class Card {
    private Long id;
    private Access access;
    private String hash;

    public Long getId() {
        return id;
    }

    public Card( String hash) {
        this.hash = hash;
    }
    
    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String getHash() {
        return hash;
    }

 
    
}
