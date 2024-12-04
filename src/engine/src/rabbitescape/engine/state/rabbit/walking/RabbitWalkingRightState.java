package rabbitescape.engine.state.rabbit.walking;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitWalkingRightState extends RabbitWalkingCommon
{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        ++rabbit.x;
        rabbit.onSlope = false;
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x + 1, change.y, '>' );
    }
}
