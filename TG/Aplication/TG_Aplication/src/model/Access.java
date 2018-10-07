/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ZnzDarck
 */
public class Access {
    private Long id;
    private String nivel;

    public Access(String nivel) {
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
