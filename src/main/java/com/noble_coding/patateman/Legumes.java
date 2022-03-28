package com.noble_coding.patateman;

public class Legumes {
    Character proprietaire;
    int largeurExplo;
    String couleur;
    Floor floor;
    int coordX;
    int coordY;
    int timeEx; //milli sec

    public Legumes(Character c,int lgEx,String couleur,Floor carte,int x, int y,int time){
        this.proprietaire=c;
        this.largeurExplo=lgEx;
        this.couleur=couleur;
        this.floor=floor;
        this.coordX=x;
        this.coordY=y;
        timeEx=time;
    }
    public Character getProprietaire(){
        return this.proprietaire;
    }
    public void setProprietaire(Character p){
        this.proprietaire=p;
    }
    public int getLargeurExplo(){
        return this.largeurExplo;
    }
    public void setLargeurExplo(int i){
        this.largeurExplo=i;
    }
    public String getCouleur(){
        return this.couleur;
    }
    public void setCouleur(String s){
        this.couleur=s;
    }
    public Floor getCarte(){
        return this.floor;
    }
    public void setCarte(Floor c){
        this.floor=c;
    }
    public int getCoordX(){
        return this.coordX;
    }
    public void setCoordX(int x){
        this.coordX=x;
    }
    public int getCoordY(){
        return this.coordY;
    }
    public void setCoordY(int y){
        this.coordY=y;
    }

}
