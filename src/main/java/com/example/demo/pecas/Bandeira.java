/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.pecas;

import com.example.demo.modelos.Pecas;
import com.example.demo.modelos.Posicao;
import com.example.demo.modelos.Tabuleiro;
import java.util.Random;

/**
///
 * @author aldri
 */
public class Bandeira extends Pecas{
    private static int quantidade = 1;
 
   public Bandeira(String nome, String color, Posicao position, boolean escondida, boolean mobilidade) {
        this.nome = nome;
        this.color = color;
        this.position = position;
        this.escondida = escondida;
        this.mobilidade = mobilidade;
    }
    
    public Bandeira() {
    }

    public static int getQuantidade() {
        return quantidade;
    }
    
   @Override
    public void addPecas(Tabuleiro tabuleiro,String cor, boolean escondida){
        
        Posicao newPosition = new Posicao();
        Random geradorX = new Random();
        newPosition.setX(geradorX.nextInt(5));
        if(cor.equals("Vermelho"))
            newPosition.setY(4);
        else
            newPosition.setY(0);
        this.nome = "BA";
        this.color = cor;
        this.escondida = escondida;
        this.position = newPosition;
        this.mobilidade = false;
        tabuleiro.ListIniciais.add(this);
        tabuleiro.setPosicao(this, position);}
}
