package com.fatec.lab_banco_de_dados.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Professor
{
    private int codigo;
    private String nome;
    private String titulacao;

    @Override
    public String toString()
    {
        return (this.nome);
    }
}
