package rabbitescape.engine.state.rabbit.turning;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

import static rabbitescape.engine.Direction.RIGHT;

public class RabbitTurningLeftToRightLoweringState extends RabbitTurningCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.dir = RIGHT;
        checkJumpOntoSlope( world, rabbit );
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_TURNING_LEFT_TO_RIGHT_LOWERING";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, '[' );
    }
}