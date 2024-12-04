package rabbitescape.engine.state.rabbit.falling;


import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitDyingOfFalling2SlopeRiseLeft2State extends RabbitFallingCommon
{
    @Override
    public boolean moveRabbit(
        World world,
        Rabbit rabbit,
        Behaviour behaviour
    )
    {
        world.changes.killRabbit( rabbit );
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT_2";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'X' );
    }

}
