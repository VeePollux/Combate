/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.modelos;

/**
 *
 * @author aldri
 */
public abstract class Pecas {
    protected String nome;
    protected String color;
    protected Posicao position;
    protected boolean escondida = false;
    protected boolean mobilidade;

    
 
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Posicao getPosition() {
        return position;
    }

    public void setPosition(Posicao position) {
        this.position = position;
    }

    public boolean isEscondida() {
        return escondida;
    }

    public void setEscondida(boolean escondida) {
        this.escondida = escondida;
    }

    public boolean isMobilidade() {
        return mobilidade;
    }

    public void setMobilidade(boolean mobilidade) {
        this.mobilidade = mobilidade;
    }
    
    public abstract void addPecas(Tabuleiro tabuleiro, String cor, boolean escondida);
    
    
}
