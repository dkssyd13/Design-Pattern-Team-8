package rabbitescape.engine.state.rabbit.turning;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitTurningLeftToRightState extends RabbitTurningCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.onSlope = false; // Intentional fall-through
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, '|' );
    }
}
