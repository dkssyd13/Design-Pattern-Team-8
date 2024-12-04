package rabbitescape.engine.state.bridging;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.state.RabbitState;
import rabbitescape.engine.textworld.Chars;

abstract class RabbitBridgingCommon implements RabbitState
{

    @Override
    public boolean rabbitIsFalling()
    {
        return false;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        boolean handled = moveRabbit( world, rabbit, behaviour);

        if ( handled )
        {
            // If we're bridging, we're on a slope
            rabbit.onSlope = true;
        }

        return handled;
    }

    @Override
    public boolean rabbitIsClimbing()
    {
        return false;
    }

    @Override
    public boolean rabbitIsDigging()
    {
        return false;
    }

    @Override
    public boolean rabbitIsBashing()
    {
        return false;
    }

    @Override
    public boolean isBlocking()
    {
        return false;
    }

    @Override
    public boolean isDigging()
    {
        return false;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }

    @Override
    public boolean moveRabbit(
        Bridging.BridgeType bridgeType,
        World world,
        Rabbit rabbit
    )
    {
        return false;
    }
}
