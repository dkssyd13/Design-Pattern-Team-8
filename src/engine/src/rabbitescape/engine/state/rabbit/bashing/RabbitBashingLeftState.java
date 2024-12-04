package rabbitescape.engine.state.rabbit.bashing;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitBashingLeftState extends RabbitBashingCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.slopeBashHop = false;
        world.changes.removeBlockAt( destX( rabbit ), rabbit.y );
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x - 1, change.y, 'W' );
    }
}
