package com.mycompany.app.model;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@Data
@ManagedBean(eager = true)
@RequestScoped
public class User {

    private String password;

    private String username;
}
