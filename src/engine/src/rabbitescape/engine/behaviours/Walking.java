package rabbitescape.engine.behaviours;


import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.state.rabbit.walking.RabbitWalkingCommon;


public class Walking extends Behaviour
{
    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        return false; // To avoid cancelling other behaviours, return false
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitWalkingCommon.newState( t );
    }

    @Override
    @SuppressWarnings("fallthrough")
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if (state instanceof RabbitState)
        {
            return ((RabbitState) state).behave( world, rabbit, this );
        }else
        {
            throw new AssertionError(
                "Should have handled all states in Walking or before,"
                    + " but we are in state " + state.name()
            );
        }
    }


}
