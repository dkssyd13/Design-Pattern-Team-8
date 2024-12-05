package rabbitescape.engine.state.rabbit.walking;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Blocking;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.state.rabbit.lowering.*;
import rabbitescape.engine.state.rabbit.rising.*;
import rabbitescape.engine.state.rabbit.turning.*;
import rabbitescape.engine.util.Position;

public abstract class RabbitWalkingCommon implements RabbitState
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

    static public State newState( BehaviourTools t)
    {
        if ( t.isOnUpSlope() )
        {
            Block aboveNext = t.blockAboveNext();
            Block above = t.blockAbove();
            int nextX = t.nextX();
            int nextY = t.rabbit.y - 1;

            if
            (
                t.isWall( aboveNext )
                    || Blocking.blockerAt( t.world, nextX, nextY )
                    || t.isRoof( above )
                    || ( t.isCresting() &&
                             Blocking.blockerAt( t.world, nextX, t.rabbit.y ) )
            )
            {
                return t.rl(
                    new RabbitTurningRightToLeftRisingState(),
                    new RabbitTurningLeftToRightRisingState()
                );
            }
            else if ( t.isUpSlope( aboveNext ) )
            {
                return t.rl(
                    new RabbitRisingRightContinueState(),
                    new RabbitRisingLeftContinueState()
                );
            }
            else if ( t.isDownSlope( t.blockNext() ) )
            {
                return t.rl(
                    new RabbitRisingAndLoweringRightState(),
                    new RabbitRisingAndLoweringLeftState()
                );
            }
            else
            {
                return t.rl(
                    new RabbitRisingRightEndState(),
                    new RabbitRisingLeftEndState()
                );
            }
        }
        else if ( t.isOnDownSlope() )
        {
            int nextX = t.nextX();
            int nextY = t.rabbit.y + 1;
            Block next = t.blockNext();
            Block belowNext = t.blockBelowNext();

            if (
                t.isWall( next )
                    || Blocking.blockerAt( t.world, nextX, nextY )
                    || ( t.isValleying() &&
                             Blocking.blockerAt( t.world, nextX, t.rabbit.y ) )
            )
            {
                return t.rl(
                    new RabbitTurningRightToLeftLoweringState(),
                    new RabbitTurningLeftToRightLoweringState()
                );
            }
            else if ( t.isUpSlope( next ) )
            {
                return t.rl(
                    new RabbitLoweringAndRisingRightState(),
                    new RabbitLoweringAndRisingLeftState()
                );
            }
            else if ( t.isDownSlope( belowNext ) )
            {
                return t.rl(
                    new RabbitLoweringRightContinueState(),
                    new RabbitLoweringLeftContinueState()
                );
            }
            else
            {
                if ( Blocking.blockerAt( t.world, nextX, t.rabbit.y ) )
                {
                    return t.rl(
                        new RabbitTurningRightToLeftLoweringState(),
                        new RabbitTurningLeftToRightLoweringState()
                    );
                }
                else
                {
                    return t.rl(
                        new RabbitLoweringRightEndState(),
                        new RabbitLoweringLeftEndState()
                    );
                }
            }
        }
        else  // On flat ground now
        {
            int nextX = t.nextX();
            int nextY = t.rabbit.y;
            Block next = t.blockNext();

            if
            (
                t.isWall( next )
                    || Blocking.blockerAt( t.world, nextX, nextY )
            )
            {
                return t.rl(
                    new RabbitTurningRightToLeftState(),
                    new RabbitTurningLeftToRightState()
                );
            }
            else if ( t.isUpSlope( next ) )
            {
                return t.rl(
                    new RabbitRisingRightStartState(),
                    new RabbitRisingLeftStartState()
                );
            }
            else if ( t.isDownSlope( t.blockBelowNext() ) )
            {
                if ( Blocking.blockerAt( t.world, nextX, t.rabbit.y + 1 ) )
                {
                    return t.rl(
                        new RabbitTurningRightToLeftState(),
                        new RabbitTurningLeftToRightState()
                    );
                }
                else
                {
                    return t.rl(
                        new RabbitLoweringRightStartState(),
                        new RabbitLoweringLeftStartState()
                    );
                }
            }
            else
            {
                return t.rl(
                    new RabbitWalkingRightState(),
                    new RabbitWalkingLeftState()
                );
            }
        }
    }
}
