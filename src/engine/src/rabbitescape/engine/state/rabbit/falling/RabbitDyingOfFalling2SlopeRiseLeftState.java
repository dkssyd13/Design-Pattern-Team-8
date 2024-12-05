package rabbitescape.engine.state.rabbit.falling;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.state.State;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.textworld.Chars;

/** The slope is two squares below where this starts. */
public class RabbitDyingOfFalling2SlopeRiseLeftState extends RabbitFallingCommon
{
    @Override
    public boolean moveRabbit(
        World world,
        Rabbit rabbit,
        Behaviour behaviour
    )
    {
        Falling fallingBehavior = (Falling) behaviour;
        fallingBehavior.setHeightFallen( fallingBehavior.getHeightFallen() + 2 );
        rabbit.y = rabbit.y + 2;
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, 'f' );
        chars.set( change.x, change.y + 2, 'x' );
    }

}
