package rabbitescape.engine.state.rabbit.turning;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.state.State;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

import static rabbitescape.engine.Direction.LEFT;

public class RabbitTurningRightToLeftState extends RabbitTurningCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.onSlope = false; // Intentional fall-through
        rabbit.dir = LEFT;
        checkJumpOntoSlope( world, rabbit );
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_TURNING_RIGHT_TO_LEFT";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, '?' );
    }
}
