package rabbitescape.engine.state.rabbit.climbing;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

import static rabbitescape.engine.Direction.opposite;

public class RabbitClimbingRightBangHeadState extends RabbitClimbingCommon
{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.dir = opposite( rabbit.dir );
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'F' );
    }
}
