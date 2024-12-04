package rabbitescape.engine.state.rabbit.falling;

import rabbitescape.engine.*;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Block.Shape.FLAT;

abstract class RabbitFallingCommon implements RabbitState
{
    @Override
    public boolean rabbitIsDigging()
    {
        return false;
    }

    @Override
    public boolean rabbitIsFalling()
    {
        return true;
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
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        boolean handled = this.moveRabbit( world, rabbit, behaviour );

        if ( handled )
        {
            // Whenever we fall onto a slope, we are on top of it
            Block thisBlock = world.getBlockAt( rabbit.x, rabbit.y );
            if ( thisBlock != null && thisBlock.shape != FLAT )
            {
                rabbit.onSlope = true;
            }
            else
            {
                rabbit.onSlope = false;
            }
        }

        return handled;
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
