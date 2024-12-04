package rabbitescape.engine.state.rising;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.state.RabbitState;
import rabbitescape.engine.util.Position;

abstract class RabbitRisingCommon implements RabbitState
{
    @Override
    public boolean rabbitIsDigging()
    {
        return false;
    }

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
        Behaviour behaviour
    )
    {
        return false;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return null;
    }

    @Override
    public char bridgingStage( ChangeDescription.State state )
    {
        return ' ';
    }


}
