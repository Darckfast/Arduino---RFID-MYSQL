/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ZnzDarck
 */
public class Logs {
    private Long id;
    private Operator operator;
    private Card card;
    private Date data;
    private Boolean granted;

    public Logs(Operator operator, Card card, Date data, Boolean granted) {
        this.operator = operator;
        this.card = card;
        this.data = data;
        this.granted = granted;
    }

    public Long getId() {
        return id;
    }

    public Operator getOperator() {
        return operator;
    }

    public Card getCard() {
        return card;
    }

    public Date getData() {
        return data;
    }

    public Boolean getGranted() {
        return granted;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Logs other = (Logs) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.operator, other.operator)) {
            return false;
        }
        if (!Objects.equals(this.card, other.card)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.granted, other.granted)) {
            return false;
        }
        return true;
    }
    
    
}
