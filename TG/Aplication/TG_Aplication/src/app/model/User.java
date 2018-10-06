package app.model;

public class User {
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private Long telefone;
    private Card card;

    public User(int id, String cpf, String nome, String email, Long telefone, Card card) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Long getTelefone() {
        return telefone;
    }

    public Card getCard() {
        return card;
    }    
}
