package rabbitescape.engine.state.rabbit.digging;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.state.State;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitDiggingState extends RabbitDiggingCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        world.changes.removeBlockAt( rabbit.x, rabbit.y + 1 );
        ++rabbit.y;
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_DIGGING";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, 'D' );
    }
}
