package rabbitescape.engine.behaviours;

import rabbitescape.engine.*;
import rabbitescape.engine.state.ExitState;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.entering.RabbitEnteringExitCommon;

public class Exiting extends Behaviour
{
    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        if ( rabbit.type == Rabbit.Type.RABBOT )
        {
            return false;  // Rabbots ignore exits
        }

        for ( Thing thing : world.things )
        {
            if (
                   ( thing instanceof Exit )
                && ( thing.x == rabbit.x && thing.y == rabbit.y )
            )
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return ExitState.newState( t, triggered, this );
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitEnteringExitCommon )
        {
            return ( ( RabbitEnteringExitCommon ) state ).behave( world, rabbit, this );
        }
        else
        {
            return false;
        }
    }
}
