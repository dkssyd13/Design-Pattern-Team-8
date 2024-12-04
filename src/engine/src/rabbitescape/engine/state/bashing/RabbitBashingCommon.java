package rabbitescape.engine.state.bashing;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.StateAndPosition;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.state.RabbitState;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Direction.RIGHT;

abstract class RabbitBashingCommon implements RabbitState {

    @Override
    public boolean rabbitIsFalling()
    {
        return false;
    }

    @Override
    public boolean rabbitIsDigging()
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
        return true;
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

    protected int destX( Rabbit rabbit )
    {
        return ( rabbit.dir == RIGHT ) ? rabbit.x + 1 : rabbit.x - 1;
    }
}
