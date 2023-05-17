/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.modelos;

/**
 *
 * @author aldri
 */
public class Posicao {
    private int x;
    private int y;
    
    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Posicao() {
    }
           
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public boolean equals (Posicao pos) {
        return (this.getX() == pos.getX()) && (this.getY() == pos.getY());
    }
    
    @Override
    public String toString() {
       return this.getX()+" - "+ this.getY();
    }
    
}
