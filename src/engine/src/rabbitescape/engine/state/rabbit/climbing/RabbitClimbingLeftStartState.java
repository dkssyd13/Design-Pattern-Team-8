package rabbitescape.engine.state.rabbit.climbing;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Climbing;
import rabbitescape.engine.state.State;
import rabbitescape.engine.textworld.Chars;


public class RabbitClimbingLeftStartState extends RabbitClimbingCommon
{
    // 기존 newStateStart() 구현
    @Override
    public State newState( BehaviourTools t, Behaviour behaviour )
    {
        return super.newStateStart( t, ( Climbing )behaviour );
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        if( t.rabbitIsClimbing() )
        { // Can't be both on a wall and on a slope.
            rabbit.onSlope = false;
        }

        Climbing climbingBehaviour = ( Climbing )behaviour;
        climbingBehaviour.setAbilityActive( true );
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_CLIMBING_LEFT_START";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'T' );
    }
}
