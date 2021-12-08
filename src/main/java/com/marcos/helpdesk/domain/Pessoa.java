package com.marcos.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcos.helpdesk.domain.enums.Perfil;
import com.marcos.helpdesk.domain.enums.Prioridade;
import com.marcos.helpdesk.domain.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String nome;

    @Column(unique = true)
    @CPF
    protected String cpf;

    @Column(unique = true)
    @Email
    protected String email;

    protected String senha;

    public Pessoa(){
        super();
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Perfis")
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCricao = LocalDate.now();

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCod());
    }




}
