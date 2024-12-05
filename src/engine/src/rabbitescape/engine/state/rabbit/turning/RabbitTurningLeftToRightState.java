package rabbitescape.engine.state.rabbit.turning;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.textworld.Chars;

public class RabbitTurningLeftToRightState extends RabbitTurningCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.onSlope = false; // Intentional fall-through
        rabbit.dir = Direction.RIGHT;
        checkJumpOntoSlope( world, rabbit );
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_TURNING_LEFT_TO_RIGHT";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, '|' );
    }
}
