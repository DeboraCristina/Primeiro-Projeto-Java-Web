package com.fatec.lab_banco_de_dados.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Disciplina
{
    private int codigo;
    private String nome;
    private Professor professor;

    @Override
    public String toString()
    {
        return String.format("Código: %d - nome: %s - Professor: %s",
                this.codigo, this.nome, this.professor);
    }
}
