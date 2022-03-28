package com.noble_coding.patateman;

public class Character {
        private Floor floor;
        private CharacterMvt characterMvt;
        private int posX, posY, nbreBombes, bombeDrop, lgExplo;

        public Character(Floor floor,int i){
            characterMvt=new CharacterMvt(this);
            this.floor = floor;
            if(i==1) {
                this.posX = 1;
                this.posY = 1;
            }
            else{
                this.posX = 11;
                this.posY = 11;
            }

            this.nbreBombes = 0;
            this.bombeDrop = 0;
            this.lgExplo = 1;
        }

        public int getPosX() {
            return posX;
        }

        public void setPosX(int posX) {
            this.posX = posX;
        }

        public int getPosY() {
            return posY;
        }

        public void setPosY(int posY) {
            this.posY = posY;
        }

        public int getNbreBombes() {
            return nbreBombes;
        }

        public int getBombeDrop(){
            return this.bombeDrop;
        }
        public void setBombeDrop(int nb){
            this.bombeDrop += nb;
        }
        public Floor getFloor(){
            return this.floor;
        }
        public void setFloor(Floor c){
            this.floor=c;
        }

}

