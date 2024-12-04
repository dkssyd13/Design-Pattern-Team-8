package rabbitescape.engine.state.rabbit.digging;

import rabbitescape.engine.*;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.util.Position;

abstract class RabbitDiggingCommon implements RabbitState
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
    public boolean rabbitIsDigging()
    {
        return true;
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
