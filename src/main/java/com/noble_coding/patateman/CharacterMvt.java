package com.noble_coding.patateman;


public class CharacterMvt {

    Character perso;

    public CharacterMvt(Character perso){
        this.perso = perso;
    }

    public void PoseBombe(){
        int x = perso.getPosX();
        int y = perso.getPosY();
        if(this.perso.getBombeDrop() > perso.getNbreBombes()){
            return;
        }
        else{
            perso.getFloor().getFloor()[x][y].setBombe(true);
            perso.getFloor().getFloor()[x][y].setAccessible(false);
            this.perso.setBombeDrop(1);
            //  BombeCtrl bombe= new BombeCtrl(new Bombe(this.perso,this.perso.getLgExplo(),this.perso.getCouleur(),this.perso.getCarte(),x,y,0));
            new LegumesCtrl(new Legumes(this.perso,this.perso.getLgExplo(),this.perso.getCouleur(),this.perso.getFloor(),x,y,0)).start();

        }
    }

    public boolean deplacement(String dep, int joueur) {
        int x = perso.getPosX();
        int y = perso.getPosY();

        switch (dep) {

            case "bas":

                System.out.println(perso.getFloor().getFloor()[x + 1][y].isAccessible());
                if (perso.getFloor().getFloor()[x + 1][y].isAccessible()) {
                    perso.setPosX(x + 1);
                    perso.getFloor().getFloor()[x + 1][y].setPlayer(joueur);
                    perso.getFloor().getFloor()[x + 1][y].setAccessible(false);
                    perso.getFloor().getFloor()[x][y].setPlayer(0);
                    if (!perso.getFloor().getFloor()[x][y].isBombe()) {
                        perso.getFloor().getFloor()[x][y].setAccessible(true);
                    }

                break;

            case "haut":

                if (perso.getFloor().getFloor()[x - 1][y].isAccessible()) {
                    perso.setPosX(x - 1);
                    perso.getFloor().getFloor()[x - 1][y].setPlayer(joueur);
                    perso.getFloor().getFloor()[x - 1][y].setAccessible(false);
                    perso.getFloor().getFloor()[x][y].setPlayer(0);
                    if (!perso.getFloor().getFloor()[x][y].isBombe()) {
                        perso.getFloor().getFloor()[x][y].setAccessible(true);
                    }
                break;

            case "gauche":

                if (perso.getFloor().getFloor()[x][y - 1].isAccessible()) {
                    perso.setPosY(y - 1);
                    perso.getFloor().getFloor()[x][y - 1].setPlayer(joueur);
                    perso.getFloor().getFloor()[x][y - 1].setAccessible(false);
                    perso.getFloor().getFloor()[x][y].setPlayer(0);
                    if (!perso.getFloor().getFloor()[x][y].isBombe()) {
                        perso.getFloor().getFloor()[x][y].setAccessible(true);
                    }

                break;

            case "droite":

                if (perso.getFloor().getFloor()[x][y + 1].isAccessible()) {
                    perso.setPosY(y + 1);
                    perso.getFloor().getFloor()[x][y + 1].setPlayer(joueur);
                    perso.getFloor().getFloor()[x][y + 1].setAccessible(false);
                    perso.getFloor().getFloor()[x][y].setPlayer(0);
                    if (!perso.getFloor().getFloor()[x][y].isBombe()) {
                        perso.getFloor().getFloor()[x][y].setAccessible(true);
                    }

                break;

                break;
        }
        return false;
    }

    public Character getPerso() {
        return this.perso;
    }

}

