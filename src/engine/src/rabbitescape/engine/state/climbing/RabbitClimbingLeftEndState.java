package rabbitescape.engine.state.climbing;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Climbing;
import rabbitescape.engine.textworld.Chars;

public class RabbitClimbingLeftEndState extends RabbitClimbingCommon
{

    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        if( t.rabbitIsClimbing() )
        { // Can't be both on a wall and on a slope.
            rabbit.onSlope = false;
        }

        rabbit.x = t.nextX();
        --rabbit.y;
        if ( t.hereIsUpSlope() )
        {
            rabbit.onSlope = true;
        }
        (( Climbing ) behaviour).setAbilityActive( false );
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x - 1, change.y - 1, 'U' );
    }
}
