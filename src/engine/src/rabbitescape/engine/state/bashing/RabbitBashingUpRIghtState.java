package rabbitescape.engine.state.bashing;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitBashingUpRIghtState extends RabbitBashingCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit )
    {
        world.changes.removeBlockAt( destX( rabbit ), rabbit.y - 1 );
        rabbit.slopeBashHop = true;
        rabbit.y -= 1;
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x + 1, change.y - 1, 'K' );
    }
}
