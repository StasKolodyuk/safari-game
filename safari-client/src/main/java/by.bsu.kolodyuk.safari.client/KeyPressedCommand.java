
package by.bsu.kolodyuk.safari.client;

import java.io.Serializable;

public class KeyPressedCommand  implements Command, Serializable {

    private String type;
    public KeyPressedCommand(String type) {
        this.type = type;
    }

    public void evaluate(BowMan bowMan, GameVariables gameVariables) {
        if(type.equals("LeftArrow"))
            bowMan.moveLeft();
        else if(type.equals("RightArrow"))
            bowMan.moveRight();

    }

}
