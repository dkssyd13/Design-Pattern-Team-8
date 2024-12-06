package rabbitescape.engine.state.rabbit.falling;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Block.Shape.FLAT;

public abstract class RabbitFallingCommon implements RabbitState
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
    public char bridgingStage( State state )
    {
        return ' ';
    }

    static public State newState( BehaviourTools t, boolean triggered, Falling falling ){
        if ( t.rabbit.state instanceof RabbitDyingOfFallingSlopeRiseLeftState )
        { // part 2 of animation always comes next
            return new RabbitDyingOfFallingSlopeRiseLeft2State();
        }

        if ( t.rabbit.state instanceof RabbitDyingOfFalling2SlopeRiseLeftState )
        { // part 2 of animation always comes next
            return new RabbitDyingOfFalling2SlopeRiseLeft2State();
        }

        if ( t.rabbit.state instanceof RabbitDyingOfFallingSlopeRiseRightState )
        { // part 2 of animation always comes next
            return new RabbitDyingOfFallingSlopeRiseRight2State();
        }

        if (t.rabbit.state instanceof RabbitDyingOfFalling2SlopeRiseRightState)
        {
            return new RabbitDyingOfFalling2SlopeRiseRight2State();
        }

        if ( !triggered )
        {
            if ( falling.getHeightFallen() > falling.getFatalHeight() )
            {
                if ( falling.getHeightFallen() % 2 == 0 )
                {
                    return new RabbitDyingOfFallingState();
                }
                else
                {
                    return new RabbitDyingOfFalling2State();
                }
            }
            return null;
        }

        if ( ( falling.getHeightFallen() + 1 > falling.getFatalHeight() )              // Going to die
                && (                                               // during step
                        t.isFlat( t.block2Below() )
                            || t.blockBelow() != null ))
        {
            if( BehaviourTools.isRightRiseSlope( t.blockBelow() ) )
            {
                return new RabbitDyingOfFallingSlopeRiseRightState();
            }
            else if( BehaviourTools.isLeftRiseSlope( t.blockBelow() ) )
            {
                return new RabbitDyingOfFallingSlopeRiseLeftState();
            }
            else
            {
                return new RabbitFalling1ToDeathState();
            }
        }
        else
        {
            Block below = t.blockBelow();

            if ( below != null )
            {
                if ( t.isUpSlope( below ) )
                {
                    return t.rl(
                        new RabbitFalling1OntoRiseRightState(),
                        new RabbitFalling1OntoRiseLeftState()
                    );
                }
                else // Must be a slope in the opposite direction
                {
                    return t.rl(
                        new RabbitFalling1OntoLowerRightState(),
                        new RabbitFalling1OntoLowerLeftState()
                    );
                }
            }

            Block twoBelow = t.block2Below();
            if ( twoBelow != null )
            {
                if (   falling.getHeightFallen() + 1 > falling.getFatalHeight()
                    && BehaviourTools.isRightRiseSlope( twoBelow ) )
                {
                    return new RabbitDyingOfFalling2SlopeRiseRightState();
                }
                if (   falling.getHeightFallen() + 1 > falling.getFatalHeight()
                    && BehaviourTools.isLeftRiseSlope( twoBelow ) )
                {
                    return new RabbitDyingOfFalling2SlopeRiseLeftState();
                }
                if ( t.isFlat( twoBelow ) ) // Flat block
                {
                    return new RabbitFalling1State();
                }
                if( t.isUpSlope( twoBelow ) )
                {
                    return t.rl(
                        new RabbitFallingOntoRiseRightState(),
                        new RabbitFallingOntoRiseLeftState()
                    );
                }
                else
                {
                    return t.rl(
                        new RabbitFallingOntoLowerRightState(),
                        new RabbitFallingOntoLowerLeftState()
                    );
                }
            }
            else
            {
                return new RabbitFallingState();
            }
        }
    }

}
