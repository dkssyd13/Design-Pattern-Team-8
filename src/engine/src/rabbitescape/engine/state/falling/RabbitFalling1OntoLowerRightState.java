package rabbitescape.engine.state.falling;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.textworld.Chars;

public class RabbitFalling1OntoLowerRightState extends RabbitFallingCommon
{
    @Override
    public boolean moveRabbit(
        World world,
        Rabbit rabbit,
        Falling fallingBehavior
    )
    {
        fallingBehavior.setHeightFallen( fallingBehavior.getHeightFallen() + 1 );
        rabbit.y = rabbit.y + 1;
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, 'e' );
    }
}
