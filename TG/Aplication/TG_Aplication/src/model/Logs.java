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
    private int id;
    private String hash;
    private String data;
    private String operador;
    private String granted;
    private String sala;

    public Logs(int id, String hash, String data, String operador, String granted, String sala) {
        this.id = id;
        this.hash = hash;
        this.data = data;
        this.operador = operador;
        this.granted = granted;
        this.sala = sala;
    }

    public int getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public String getData() {
        return data;
    }

    public String getOperador() {
        return operador;
    }

    public String getGranted() {
        return granted;
    }

    public String getSala() {
        return sala;
    }
    
    
}
