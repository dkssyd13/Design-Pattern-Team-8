package rabbitescape.engine.behaviours;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.BehaviourTools;

import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.burning.RabbitBurningCommon;

public class Burning extends Behaviour
{
    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        return world.fireAt( rabbit.x, rabbit.y );
    }

    @Override
    public State newState(
        BehaviourTools t, boolean triggered
        )
    {
        return RabbitBurningCommon.newState( t, triggered );
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitBurningCommon )
        {
            return ( ( RabbitBurningCommon )state ).behave(
                world,
                rabbit,
                this
            );
        } else
        {
            return false;
        }
    }
}
