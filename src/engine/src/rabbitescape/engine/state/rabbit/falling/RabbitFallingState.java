package rabbitescape.engine.state.rabbit.falling;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.textworld.Chars;

public class RabbitFallingState extends RabbitFallingCommon {
    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        Falling fallingBehavior = (Falling) behaviour;
        fallingBehavior.setHeightFallen( fallingBehavior.getHeightFallen() + 2 );
        rabbit.y = rabbit.y + 2;
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, 'f' );
        chars.set( change.x, change.y + 2, 'f' );
    }

}
