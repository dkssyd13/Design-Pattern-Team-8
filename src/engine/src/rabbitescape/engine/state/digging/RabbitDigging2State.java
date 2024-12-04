package rabbitescape.engine.state.digging;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitDigging2State extends RabbitDiggingCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit )
    {
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'D' );
    }
}
