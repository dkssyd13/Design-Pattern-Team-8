package rabbitescape.engine.state.rabbit.lowering;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class RabbitLoweringAndRisingRightState extends RabbitLoweringCommon{
    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        ++rabbit.x;
        rabbit.onSlope = true;
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_LOWERING_AND_RISING_RIGHT";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x + 1, change.y, ',' );
    }
}
