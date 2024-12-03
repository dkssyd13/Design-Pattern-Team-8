package rabbitescape.engine.state.rising;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.StateAndPosition;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.state.RabbitState;
import rabbitescape.engine.util.Position;

abstract class RabbitRisingCommon implements RabbitState
{
    @Override
    public boolean rabbitIsFalling()
    {
        return false;
    }

    @Override
    public boolean rabbitIsClimbing()
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
    public boolean moveRabbit(
        World world,
        Rabbit rabbit,
        Falling fallingBehavior
    )
    {
        return false;
    }

    @Override
    public Position whereBridging( StateAndPosition change )
    {
        return null;
    }

    @Override
    public char bridgingStage( ChangeDescription.State state )
    {
        return 0;
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
