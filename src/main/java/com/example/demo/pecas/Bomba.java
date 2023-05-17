package com.example.demo.pecas;
import com.example.demo.modelos.Pecas;
import com.example.demo.modelos.Posicao;
import com.example.demo.modelos.Tabuleiro;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author aldri
 */
public class Bomba extends Pecas{
        public static int quantidade = 2;
        public Bomba(String nome, String color, Posicao position, boolean escondida, boolean mobilidade, int quantidade) {
        this.nome = nome;
        this.color = color;
        this.position = position;
        this.escondida = escondida;
        this.mobilidade = mobilidade;
        
    }

    public Bomba() {
    }

    public static int getQuantidade() {
        return quantidade;
    }
    
    @Override
    public void addPecas(Tabuleiro tabuleiro,String cor, boolean escondida){
        int aux = 3, randomX;
        boolean flag = false;
        Random geradorX = new Random();
        ////TRY CATCH AQUI
        Posicao positionBand = new Posicao();
        Posicao positionBomb = new Posicao();
        for(int i =0;i < tabuleiro.ListIniciais.size(); i++)
            if(tabuleiro.getPecaInicial(i).getNome().equals("BA")&&tabuleiro.getPecaInicial(i).getColor().equals(cor))
                positionBand = tabuleiro.getPecaInicial(i).getPosition();
        for(int i =0;i < tabuleiro.ListIniciais.size(); i++)
            if(tabuleiro.getPecaInicial(i).getNome().equals("BO")&&tabuleiro.getPecaInicial(i).getColor().equals(cor))
                flag = true;
        
        if(flag){
            do{
                randomX = geradorX.nextInt(((positionBand.getX() +1)-(positionBand.getX()- 1))+ 1)+(positionBand.getX()-1);}
            while(randomX == positionBand.getX() || randomX >4 || randomX < 0);
            positionBomb.setX(randomX);
            positionBomb.setY(positionBand.getY());
        }
        else{
            if(positionBand.getY()==4)
                aux = 3;
            else
                aux = 1;
            positionBomb.setX(positionBand.getX());
            positionBomb.setY(aux);}
        
        this.position = positionBomb;
        this.nome = "BO";
        this.color = cor;
        this.escondida = escondida;
        tabuleiro.ListIniciais.add(this);
        tabuleiro.setPosicao(this, position);}
}
