package rabbitescape.engine.state.rising;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitRisingRightStartState extends RabbitRisingCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit )
    {
        ++rabbit.x;
        rabbit.onSlope = true;
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x + 1, change.y, '~' );
    }
}
