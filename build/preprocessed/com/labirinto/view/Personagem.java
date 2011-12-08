package com.labirinto.view;


import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 *@author Jefferson Rodrigues De Almeida Matricula: 100 854
 */

public class Personagem extends Sprite{
    private int direcao;
    private int estado;
    
    public Personagem() throws IOException{
        super(Image.createImage("SBM2-Bomberman.png"), 16, 25);
        direcao = D;
        estado = JOGANDO;
        setFrameSequence(new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28});
    }

    public void setDirecao(int d){
        if((direcao != d) && (estado == JOGANDO)){
            direcao = d;
        }
    }

    public void setEstado(int e){
        if(estado != e){
            estado = e;
        }
    }

    public int getEstado(){
        return estado;
    }

    public void mover(int passos){
        if(estado == JOGANDO){
            switch(direcao){
                case C:
                    move(0,-passos);
                    anima_cima();
                    break;
                case B:
                    move(0,passos);
                    anima_baixo();
                    break;
                case D:
                    move(passos,0);
                    anima_direita();
                    break;
                case E:
                    move(-passos,0);
                    anima_esquerda();
                    break;
            }
        }

    }

    public void trataEstado(){
        switch(estado){
            case GANHOU:
                anima_ganhou();
                break;
            case PERDEU:
                anima_perdeu();
                break;
            case JOGANDO:
                break;
        }
    }

    public void anima_cima(){
        int f = this.getFrame();
        switch(f){
            case 0:
                setFrame(1);
                break;
            case 1:
                setFrame(2);
                break;
            case 2:
                setFrame(0);
                break;
            default:
                setFrame(0);
                break;
        }
    }

    public void anima_baixo(){
        int f = this.getFrame();
        switch(f){
            case 6:
                setFrame(7);
                break;
            case 7:
                setFrame(8);
                break;
            case 8:
                setFrame(6);
                break;
            default:
                setFrame(6);
                break;
        }
    }

    public void anima_direita(){
        int f = this.getFrame();
        switch(f){
            case 3:
                setFrame(4);
                break;
            case 4:
                setFrame(5);
                break;
            case 5:
                setFrame(3);
                break;
            default:
                setFrame(3);
                break;
        }
    }

    public void anima_ganhou(){
        int f = this.getFrame();
        switch(f){
            case 18:
                setFrame(19);
                break;
            case 19:
                setFrame(20);
                break;
            case 20:
                setFrame(21);
                break;
            case 21:
                setFrame(22);
                break;
            case 22:
                setFrame(23);
                break;
            case 23:
                setFrame(24);
                break;
            case 24:
                setFrame(25);
                break;
            case 25:
                setFrame(26);
                break;
            case 26:
                setFrame(27);
                break;
            case 27:
                setFrame(28);
                break;
            case 28:
                setFrame(18);
                break;
            default:
                setFrame(18);
                break;
        }
    }

    public void anima_perdeu(){
        int f = this.getFrame();
        switch(f){
            case 12:
                setFrame(13);
                break;
            case 13:
                setFrame(14);
                break;
            case 15:
                setFrame(16);
                break;
            case 16:
                setFrame(17);
                break;
            case 17:
                setFrame(12);
                break;
            default:
                setFrame(12);
                break;
        }
    }

    public void anima_esquerda(){
        int f = this.getFrame();
        switch(f){
            case 9:
                setFrame(10);
                break;
            case 10:
                setFrame(11);
                break;
            case 11:
                setFrame(9);
                break;
            default:
                setFrame(9);
                break;
        }
    }    

    public static final int
            C = 0,
            B = 1,
            D = 2,
            E = 3,
            JOGANDO = 4,
            GANHOU = 5,
            PERDEU = 6;
}
