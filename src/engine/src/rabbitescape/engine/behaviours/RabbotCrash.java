package rabbitescape.engine.behaviours;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.BehaviourTools;

import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitCrashingState;

public class RabbotCrash extends Behaviour
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
            for ( Rabbit otherRabbit : world.rabbits )
            {
                if ( otherRabbit.type == Rabbit.Type.RABBIT &&
                    otherRabbit.x == rabbit.x &&
                    otherRabbit.y == rabbit.y
                )
                {
                    world.changes.killRabbit( otherRabbit );
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            return new RabbitCrashingState();
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitCrashingState )
        {
            return ((RabbitCrashingState)state).behave( world, rabbit, this );
        }
        return false;
    }
}
