
package by.bsu.kolodyuk.safari.server;

import java.io.Serializable;

public interface Command{

    public void evaluate(BowMan bowMan, GameVariables gameVariables);

}
