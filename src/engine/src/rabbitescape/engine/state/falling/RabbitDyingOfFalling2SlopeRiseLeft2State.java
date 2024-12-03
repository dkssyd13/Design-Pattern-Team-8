package rabbitescape.engine.state.falling;


import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.textworld.Chars;

/** Part 2 of RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT. */
public class RabbitDyingOfFalling2SlopeRiseLeft2State extends RabbitFallingCommon
{
    @Override
    public boolean moveRabbit(
        World world,
        Rabbit rabbit,
        Falling fallingBehavior
    )
    {
        world.changes.killRabbit( rabbit );
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'X' );
    }

}
