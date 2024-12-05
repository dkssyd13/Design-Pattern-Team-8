package rabbitescape.engine.state.rabbit.turning;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Block.Shape.BRIDGE_UP_LEFT;
import static rabbitescape.engine.Block.Shape.BRIDGE_UP_RIGHT;

public abstract class RabbitTurningCommon implements RabbitState
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
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        return false;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return null;
    }

    @Override
    public char bridgingStage( State state )
    {
        return ' ';
    }

    /**
     * If we turn around near a slope, we jump onto it
     */
    protected void checkJumpOntoSlope( World world, Rabbit rabbit )
    {
        Block thisBlock = world.getBlockAt( rabbit.x, rabbit.y );
        if ( isBridge( thisBlock ) )
        {
            Block aboveBlock = world.getBlockAt( rabbit.x, rabbit.y - 1 );
            if ( rabbit.onSlope && isBridge( aboveBlock ) )
            {
                rabbit.y--;
            }
            else
            {
                rabbit.onSlope = true;
            }
        }
    }

    private boolean isBridge( Block block )
    {
        return (
            block != null
                && (
                block.shape == BRIDGE_UP_LEFT
                    || block.shape == BRIDGE_UP_RIGHT
            )
        );
    }
}
