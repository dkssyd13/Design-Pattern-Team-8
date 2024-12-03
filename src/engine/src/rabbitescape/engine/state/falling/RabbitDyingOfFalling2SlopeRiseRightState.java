package rabbitescape.engine.state.falling;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.textworld.Chars;

public class RabbitDyingOfFalling2SlopeRiseRightState extends RabbitFallingCommon
{
    @Override
    public boolean moveRabbit(
        World world,
        Rabbit rabbit,
        Falling fallingBehavior
    )
    {
        fallingBehavior.setHeightFallen( fallingBehavior.getHeightFallen() + 2 );
        rabbit.y = rabbit.y + 2;
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, 'f' );
        chars.set( change.x, change.y + 2, 'x' );
    }

}
