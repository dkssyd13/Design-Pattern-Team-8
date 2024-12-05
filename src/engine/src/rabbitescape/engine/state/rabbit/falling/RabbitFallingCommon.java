package rabbitescape.engine.state.rabbit.falling;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Block.Shape.FLAT;

// TODO : 주석 삭제
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
//        if ( RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT == t.rabbit.state )
        if ( t.rabbit.state instanceof RabbitDyingOfFallingSlopeRiseLeftState )
        { // part 2 of animation always comes next
//            return RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT_2;
            return new RabbitDyingOfFallingSlopeRiseLeft2State();
        }

//        if ( RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT == t.rabbit.state )
        if ( t.rabbit.state instanceof RabbitDyingOfFalling2SlopeRiseLeftState )
        { // part 2 of animation always comes next
//            return RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT_2;
            return new RabbitDyingOfFalling2SlopeRiseLeft2State();
        }

//        if ( RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT == t.rabbit.state )
        if ( t.rabbit.state instanceof RabbitDyingOfFallingSlopeRiseRightState )
        { // part 2 of animation always comes next
//            return RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT_2;
            return new RabbitDyingOfFallingSlopeRiseRight2State();
        }

//        if ( RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT == t.rabbit.state )
        if (t.rabbit.state instanceof RabbitDyingOfFalling2SlopeRiseRightState)
        {
//            return RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT_2;
            return new RabbitDyingOfFalling2SlopeRiseRight2State();
        }

        if ( !triggered )
        {
//            if ( heightFallen > fatalHeight )
            if ( falling.getHeightFallen() > falling.getFatalHeight() )
            {
//                if ( heightFallen % 2 == 0 )
                if ( falling.getHeightFallen() % 2 == 0 )
                {
//                    return RABBIT_DYING_OF_FALLING;
                    return new RabbitDyingOfFallingState();
                }
                else
                {
//                    return RABBIT_DYING_OF_FALLING_2;
                    return new RabbitDyingOfFalling2State();
                }
            }
            return null;
        }

//        if ( ( heightFallen + 1 > fatalHeight )              // Going to die
        if ( ( falling.getHeightFallen() + 1 > falling.getFatalHeight() )              // Going to die
                && (                                               // during step
                        t.isFlat( t.block2Below() )
                            || t.blockBelow() != null ))
        {
            if( BehaviourTools.isRightRiseSlope( t.blockBelow() ) )
            {
//                return RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT;
                return new RabbitDyingOfFallingSlopeRiseRightState();
            }
            else if( BehaviourTools.isLeftRiseSlope( t.blockBelow() ) )
            {
//                return RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT;
                return new RabbitDyingOfFallingSlopeRiseLeftState();
            }
            else
            {
//                return State.RABBIT_FALLING_1_TO_DEATH;
                return new RabbitFalling1ToDeathState(); // (State에) 필요 X
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
//                        RABBIT_FALLING_1_ONTO_RISE_RIGHT,
                        new RabbitFalling1OntoRiseRightState(),
//                        RABBIT_FALLING_1_ONTO_RISE_LEFT
                        new RabbitFalling1OntoRiseLeftState()
                    );
                }
                else // Must be a slope in the opposite direction
                {
                    return t.rl(
//                        RABBIT_FALLING_1_ONTO_LOWER_RIGHT,
//                        RABBIT_FALLING_1_ONTO_LOWER_LEFT
                        new RabbitFalling1OntoLowerRightState(),
                        new RabbitFalling1OntoLowerLeftState()
                    );
                }
            }

            Block twoBelow = t.block2Below();
            if ( twoBelow != null )
            {
//                if (   heightFallen + 1 > fatalHeight
                if (   falling.getHeightFallen() + 1 > falling.getFatalHeight()
                    && BehaviourTools.isRightRiseSlope( twoBelow ) )
                {
//                    return RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT;
                    return new RabbitDyingOfFalling2SlopeRiseRightState();
                }
                if (   falling.getHeightFallen() + 1 > falling.getFatalHeight()
                    && BehaviourTools.isLeftRiseSlope( twoBelow ) )
                {
//                    return RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT;
                    return new RabbitDyingOfFalling2SlopeRiseLeftState();
                }
                if ( t.isFlat( twoBelow ) ) // Flat block
                {
//                    return State.RABBIT_FALLING_1; // (State에) 필요 X
                    return new RabbitFalling1State(); // (State에) 필요 X
                }
                if( t.isUpSlope( twoBelow ) )
                {
                    return t.rl(
//                        RABBIT_FALLING_ONTO_RISE_RIGHT,
//                        RABBIT_FALLING_ONTO_RISE_LEFT
                        new RabbitFallingOntoRiseRightState(),
                        new RabbitFallingOntoRiseLeftState()
                    );
                }
                else
                {
                    return t.rl(
//                        RABBIT_FALLING_ONTO_LOWER_RIGHT,
//                        RABBIT_FALLING_ONTO_LOWER_LEFT
                        new RabbitFallingOntoLowerRightState(),
                        new RabbitFallingOntoLowerLeftState()
                    );
                }
            }
            else
            {
//                return State.RABBIT_FALLING; // (State에) 필요 X
                return new RabbitFallingState(); // (State에) 필요 X
            }
        }
    }

}
