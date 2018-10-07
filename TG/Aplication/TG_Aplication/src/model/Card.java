/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author ZnzDarck
 */
public class Card {
    private Long id;
    private String hash;
    private Long acesso;

    public Card(String hash, Long acesso) {
        this.hash = hash;
        this.acesso = acesso;
    }

    public Long getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcesso() {
        return acesso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (!Objects.equals(this.hash, other.hash)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.acesso, other.acesso)) {
            return false;
        }
        return true;
    }
    
    
}
