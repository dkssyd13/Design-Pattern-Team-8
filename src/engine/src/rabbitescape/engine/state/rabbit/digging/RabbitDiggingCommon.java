package rabbitescape.engine.state.rabbit.digging;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Digging;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.util.Position;

public abstract class RabbitDiggingCommon implements RabbitState
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
        return true;
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
    public char bridgingStage( State state )
    {
        return ' ';
    }

    public static State newState( BehaviourTools t, boolean triggered, Digging digging )
    {
        if ( !triggered && digging.getStepsOfDigging() == 0 )
        {
            return null;
        }

        t.rabbit.possiblyUndoSlopeBashHop( t.world );

//        if ( t.rabbit.state == RABBIT_DIGGING )
        if ( t.rabbit.state instanceof RabbitDiggingState )
        {
//            stepsOfDigging = 1;
//            return RABBIT_DIGGING_2;
             digging.setStepsOfDigging( 1 );
            return new RabbitDigging2State();
        }

        if (
            triggered
                || digging.getStepsOfDigging() > 0
        )
        {
            if ( t.rabbit.onSlope && t.blockHere() != null )
            {
//                stepsOfDigging = 1;
//                return RABBIT_DIGGING_ON_SLOPE;
                digging.setStepsOfDigging( 1 );
                return new RabbitDiggingOnSlopeState();
            }
            else if ( t.blockBelow() != null )
            {
                if ( t.blockBelow().material == Block.Material.METAL )
                {
//                    stepsOfDigging = 0;
//                    return RABBIT_DIGGING_USELESSLY;
                    digging.setStepsOfDigging( 0 );
                    return new RabbitDiggingUselesslyState();
                }
                else
                {
//                    stepsOfDigging = 2;
//                    return RABBIT_DIGGING;
                    digging.setStepsOfDigging( 2 );
                    return new RabbitDiggingState();
                }
            }
        }

//        --stepsOfDigging;
        digging.setStepsOfDigging( digging.getStepsOfDigging() - 1 );
        return null;
    }
}
