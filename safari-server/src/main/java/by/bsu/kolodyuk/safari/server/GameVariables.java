

package by.bsu.kolodyuk.safari.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameVariables implements Serializable{

    private List<BowMan> bowMans;
    private List<Arrow> arrows;
    private List<Bird> birds;
    private List<Elephant> elephants;

    public GameVariables() {
        bowMans = Collections.synchronizedList(new ArrayList<BowMan>());
        arrows = Collections.synchronizedList(new ArrayList<Arrow>());
        birds = Collections.synchronizedList(new ArrayList<Bird>());
        elephants = Collections.synchronizedList(new ArrayList<Elephant>());
    }
    public GameVariables(List<BowMan> bowMans, List<Arrow> arrows, List<Bird> birds, List<Elephant> elephants) {
        this.bowMans = bowMans;
        this.arrows = arrows;
        this.birds = birds;
        this.elephants = elephants;
    }

    public List<Arrow> getArrows() {
        return arrows;
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public List<BowMan> getBowMans() {
        return bowMans;
    }

    public List<Elephant> getElephants() {
        return elephants;
    }

    public void setArrows(List<Arrow> arrows) {
        this.arrows = arrows;
    }

    public void setBirds(List<Bird> birds) {
        this.birds = birds;
    }

    public void setBowMans(List<BowMan> bowMans) {
        this.bowMans = bowMans;
    }

    public void setElephants(List<Elephant> elephants) {
        this.elephants = elephants;
    }
}