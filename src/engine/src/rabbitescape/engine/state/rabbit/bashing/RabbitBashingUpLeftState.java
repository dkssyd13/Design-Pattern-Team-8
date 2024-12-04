package rabbitescape.engine.state.rabbit.bashing;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitBashingUpLeftState extends RabbitBashingCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        world.changes.removeBlockAt( destX( rabbit ), rabbit.y - 1 );
        rabbit.slopeBashHop = true;
        rabbit.y -= 1;
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_BASHING_UP_LEFT";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x - 1, change.y - 1, 'W' );
    }
}
