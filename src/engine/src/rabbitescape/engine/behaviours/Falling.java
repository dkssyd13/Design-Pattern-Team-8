package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Block.Shape.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.state.rabbit.digging.RabbitDiggingState;
import rabbitescape.engine.state.rabbit.falling.*;

// TODO : 주석 삭제
public class Falling extends Behaviour
{
    private int heightFallen = 0;

    private final Climbing climbing;
    private final Brollychuting brollychuting;
    private final int fatalHeight;

    public Falling( 
        Climbing climbing, 
        Brollychuting brollychuting,
        int fatalHeight 
    )
    {
        this.climbing = climbing;
        this.brollychuting = brollychuting;
        this.fatalHeight = fatalHeight;
    }

    public boolean isFallingToDeath()
    {
        return heightFallen > fatalHeight ;
    }

    @Override
    public void cancel()
    {
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        boolean handled = moveRabbit( world, rabbit, state );

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

    private boolean moveRabbit( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitFallingCommon )
        {
            return ( ( RabbitFallingCommon )state ).moveRabbit( world, rabbit, this );
        }else
        {
            heightFallen = 0;
            return false;
        }
//        switch ( state )
//        {
//            case RABBIT_DYING_OF_FALLING: // DONE
//            case RABBIT_DYING_OF_FALLING_2: // DONE
//            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT_2: // DONE
//            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT_2: // DONE
//            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT_2: // DONE
//            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT_2: // DONE
//            {
//                world.changes.killRabbit( rabbit );
//                return true;
//            }
//            case RABBIT_FALLING: //DONE
//            case RABBIT_FALLING_ONTO_LOWER_RIGHT: // DONE
//            case RABBIT_FALLING_ONTO_LOWER_LEFT: // DONE
//            case RABBIT_FALLING_ONTO_RISE_RIGHT: // DONE
//            case RABBIT_FALLING_ONTO_RISE_LEFT: // DONE
//            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT: // DONE
//            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT: // DONE
//            {
//                heightFallen += 2;
//                rabbit.y = rabbit.y + 2;
//                return true;
//            }
//            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT: // DONE
//            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT: // DONE
//            case RABBIT_FALLING_1_TO_DEATH: // DONE
//            case RABBIT_FALLING_1: // DONE
//            case RABBIT_FALLING_1_ONTO_LOWER_RIGHT: // DONE
//            case RABBIT_FALLING_1_ONTO_LOWER_LEFT: // DONE
//            case RABBIT_FALLING_1_ONTO_RISE_RIGHT: // DONE
//            case RABBIT_FALLING_1_ONTO_RISE_LEFT: // DONE
//            {
//                heightFallen += 1;
//                rabbit.y = rabbit.y + 1;
//                return true;
//            }
//            default:
//            {
//                heightFallen = 0;
//                return false;
//            }
//        }
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        if (   climbing.abilityActive
//            || rabbit.state == RABBIT_DIGGING
            || rabbit.state instanceof RabbitDiggingState
            || brollychuting.hasBrolly() )
        {
            return false;
        }

        BehaviourTools t = new BehaviourTools( rabbit, world );

        //noinspection RedundantIfStatement
        if ( t.isFlat( t.blockBelow() ) )
        {
            return false;
        }

        if (
               rabbit.onSlope
            && !t.blockHereJustRemoved()
        )
        {
            return false;
        }

        return true;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitFallingCommon.newState( t, triggered, this );
    }
//    {
////        if ( RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT == t.rabbit.state )
//        if ( t.rabbit.state instanceof RabbitDyingOfFallingSlopeRiseLeftState )
//        { // part 2 of animation always comes next
////            return RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT_2;
//            return new RabbitDyingOfFallingSlopeRiseLeft2State();
//        }
//
////        if ( RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT == t.rabbit.state )
//        if ( t.rabbit.state instanceof RabbitDyingOfFalling2SlopeRiseLeftState )
//        { // part 2 of animation always comes next
////            return RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT_2;
//            return new RabbitDyingOfFalling2SlopeRiseLeft2State();
//        }
//
////        if ( RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT == t.rabbit.state )
//        if ( t.rabbit.state instanceof RabbitDyingOfFallingSlopeRiseRightState )
//        { // part 2 of animation always comes next
////            return RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT_2;
//            return new RabbitDyingOfFallingSlopeRiseRight2State();
//        }
//
////        if ( RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT == t.rabbit.state )
//        if (t.rabbit.state instanceof RabbitDyingOfFalling2SlopeRiseRightState)
//        {
////            return RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT_2;
//            return new RabbitDyingOfFalling2SlopeRiseRight2State();
//        }
//
//        if ( !triggered )
//        {
//            if ( heightFallen > fatalHeight )
//            {
//                if ( heightFallen % 2 == 0 )
//                {
////                    return RABBIT_DYING_OF_FALLING;
//                    return new RabbitDyingOfFallingState();
//                }
//                else
//                {
////                    return RABBIT_DYING_OF_FALLING_2;
//                    return new RabbitDyingOfFalling2State();
//                }
//            }
//            return null;
//        }
//
//        if (
//               ( heightFallen + 1 > fatalHeight )              // Going to die
//            && (                                               // during step
//                   t.isFlat( t.block2Below() )
//                || t.blockBelow() != null
//            )
//        )
//        {
//            if( BehaviourTools.isRightRiseSlope( t.blockBelow() ) )
//            {
////                return RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT;
//                return new RabbitDyingOfFallingSlopeRiseRightState();
//            }
//            else if( BehaviourTools.isLeftRiseSlope( t.blockBelow() ) )
//            {
////                return RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT;
//                return new RabbitDyingOfFallingSlopeRiseLeftState();
//            }
//            else
//            {
////                return State.RABBIT_FALLING_1_TO_DEATH;
//                return new RabbitFalling1ToDeathState(); // (State에) 필요 X
//            }
//        }
//        else
//        {
//            Block below = t.blockBelow();
//
//            if ( below != null )
//            {
//                if ( t.isUpSlope( below ) )
//                {
//                    return t.rl(
////                        RABBIT_FALLING_1_ONTO_RISE_RIGHT,
//                        new RabbitFalling1OntoRiseRightState(),
////                        RABBIT_FALLING_1_ONTO_RISE_LEFT
//                        new RabbitFalling1OntoRiseLeftState()
//                    );
//                }
//                else // Must be a slope in the opposite direction
//                {
//                    return t.rl(
////                        RABBIT_FALLING_1_ONTO_LOWER_RIGHT,
////                        RABBIT_FALLING_1_ONTO_LOWER_LEFT
//                        new RabbitFalling1OntoLowerRightState(),
//                        new RabbitFalling1OntoLowerLeftState()
//                    );
//                }
//            }
//
//            Block twoBelow = t.block2Below();
//            if ( twoBelow != null )
//            {
//                if (   heightFallen + 1 > fatalHeight
//                    && BehaviourTools.isRightRiseSlope( twoBelow ) )
//                {
////                    return RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT;
//                    return new RabbitDyingOfFalling2SlopeRiseRightState();
//                }
//                if (   heightFallen + 1 > fatalHeight
//                    && BehaviourTools.isLeftRiseSlope( twoBelow ) )
//                {
////                    return RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT;
//                    return new RabbitDyingOfFalling2SlopeRiseLeftState();
//                }
//                if ( t.isFlat( twoBelow ) ) // Flat block
//                {
////                    return State.RABBIT_FALLING_1; // (State에) 필요 X
//                    return new RabbitFalling1State(); // (State에) 필요 X
//                }
//                if( t.isUpSlope( twoBelow ) )
//                {
//                    return t.rl(
////                        RABBIT_FALLING_ONTO_RISE_RIGHT,
////                        RABBIT_FALLING_ONTO_RISE_LEFT
//                        new RabbitFallingOntoRiseRightState(),
//                        new RabbitFallingOntoRiseLeftState()
//                    );
//                }
//                else
//                {
//                    return t.rl(
////                        RABBIT_FALLING_ONTO_LOWER_RIGHT,
////                        RABBIT_FALLING_ONTO_LOWER_LEFT
//                        new RabbitFallingOntoLowerRightState(),
//                        new RabbitFallingOntoLowerLeftState()
//                    );
//                }
//            }
//            else
//            {
////                return State.RABBIT_FALLING; // (State에) 필요 X
//                return new RabbitFallingState(); // (State에) 필요 X
//            }
//        }
//    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Falling.heightFallen", heightFallen
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        heightFallen = BehaviourState.restoreFromState(
            saveState, "Falling.heightFallen", heightFallen
        );
    }

    public int getHeightFallen()
    {
        return heightFallen;
    }

    public void setHeightFallen( int heightFallen )
    {
        this.heightFallen = heightFallen;
    }

    public Climbing getClimbing()
    {
        return climbing;
    }

    public Brollychuting getBrollychuting()
    {
        return brollychuting;
    }

    public int getFatalHeight()
    {
        return fatalHeight;
    }
}
