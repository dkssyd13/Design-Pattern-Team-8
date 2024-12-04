package rabbitescape.engine.state.rabbit.climbing;

import rabbitescape.engine.*;
import rabbitescape.engine.textworld.Chars;

import static rabbitescape.engine.Direction.opposite;

public class RabbitClimbingLeftBangHeadState extends RabbitClimbingCommon
{

    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        if( t.rabbitIsClimbing() )
        { // Can't be both on a wall and on a slope.
            rabbit.onSlope = false;
        }

        rabbit.dir = opposite( rabbit.dir );
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_CLIMBING_LEFT_BANG_HEAD";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'Y' );
    }
}
