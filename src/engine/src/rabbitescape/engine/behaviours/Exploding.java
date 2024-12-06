package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Token.Type.*;


import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitExplodingState;


public class Exploding extends Behaviour
{
    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );
        return t.pickUpToken( explode, true );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            return new RabbitExplodingState();
        }
        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitExplodingState )
        {
            return ((RabbitExplodingState)state).behave( world, rabbit, this );
        }

        return false;
    }
}
