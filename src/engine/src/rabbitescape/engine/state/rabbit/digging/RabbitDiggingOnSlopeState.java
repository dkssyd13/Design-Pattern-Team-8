package rabbitescape.engine.state.rabbit.digging;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.state.State;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitDiggingOnSlopeState extends RabbitDiggingCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        world.changes.removeBlockAt( rabbit.x, rabbit.y );
        rabbit.onSlope = false;
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_DIGGING_ON_SLOPE";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'D' );
    }
}
