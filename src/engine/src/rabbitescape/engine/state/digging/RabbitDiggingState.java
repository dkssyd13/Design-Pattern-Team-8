package rabbitescape.engine.state.digging;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitDiggingState extends RabbitDiggingCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit )
    {
        world.changes.removeBlockAt( rabbit.x, rabbit.y + 1 );
        ++rabbit.y;
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, 'D' );
    }
}
