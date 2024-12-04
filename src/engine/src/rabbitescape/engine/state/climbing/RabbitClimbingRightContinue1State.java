package rabbitescape.engine.state.climbing;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Climbing;
import rabbitescape.engine.state.State;
import rabbitescape.engine.textworld.Chars;

public class RabbitClimbingRightContinue1State extends RabbitClimbingCommon
{
    @Override
    public State newState( BehaviourTools t, Behaviour behaviour )
    {
        return newStateCont1( t, ( Climbing ) behaviour);
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        if( t.rabbitIsClimbing() )
        { // Can't be both on a wall and on a slope.
            rabbit.onSlope = false;
        }

        ((Climbing)behaviour).setAbilityActive( true );
        return true;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y - 1, 'F' );
    }
}
