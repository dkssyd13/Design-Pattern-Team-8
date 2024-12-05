package rabbitescape.engine.behaviours;


import static rabbitescape.engine.Direction.*;
import static rabbitescape.engine.Block.Shape.*;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.state.rabbit.turning.RabbitTurningCommon;
import rabbitescape.engine.state.rabbit.walking.RabbitWalkingCommon;


public class Walking extends Behaviour
{
    @Override
    public void cancel()
    {
    }

//    private static class StateCalc
//    {
//        private final BehaviourTools t;
//
//        StateCalc( BehaviourTools t )
//        {
//            this.t = t;
//        }
//
//        public State newState()
//        {
//            if ( t.isOnUpSlope() )
//            {
//                Block aboveNext = t.blockAboveNext();
//                Block above = t.blockAbove();
//                int nextX = t.nextX();
//                int nextY = t.rabbit.y - 1;
//
//                if
//                    (
//                       t.isWall( aboveNext )
//                    || Blocking.blockerAt( t.world, nextX, nextY )
//                    || t.isRoof( above )
//                    || ( t.isCresting() &&
//                         Blocking.blockerAt( t.world, nextX, t.rabbit.y ) )
//                    )
//                {
//                    return rl(
//                        RABBIT_TURNING_RIGHT_TO_LEFT_RISING,
//                        RABBIT_TURNING_LEFT_TO_RIGHT_RISING
//                    );
//                }
//                else if ( t.isUpSlope( aboveNext ) )
//                {
//                    return rl(
//                        RABBIT_RISING_RIGHT_CONTINUE,
//                        RABBIT_RISING_LEFT_CONTINUE
//                    );
//                }
//                else if ( t.isDownSlope( t.blockNext() ) )
//                {
//                    return rl(
//                        RABBIT_RISING_AND_LOWERING_RIGHT,
//                        RABBIT_RISING_AND_LOWERING_LEFT
//                    );
//                }
//                else
//                {
//                    return rl(
//                        RABBIT_RISING_RIGHT_END,
//                        RABBIT_RISING_LEFT_END
//                    );
//                }
//            }
//            else if ( t.isOnDownSlope() )
//            {
//                int nextX = t.nextX();
//                int nextY = t.rabbit.y + 1;
//                Block next = t.blockNext();
//                Block belowNext = t.blockBelowNext();
//
//                if (
//                       t.isWall( next )
//                    || Blocking.blockerAt( t.world, nextX, nextY )
//                    || ( t.isValleying() &&
//                         Blocking.blockerAt( t.world, nextX, t.rabbit.y ) )
//                    )
//                {
//                    return rl(
//                        RABBIT_TURNING_RIGHT_TO_LEFT_LOWERING,
//                        RABBIT_TURNING_LEFT_TO_RIGHT_LOWERING
//                    );
//                }
//                else if ( t.isUpSlope( next ) )
//                {
//                    return rl(
//                        RABBIT_LOWERING_AND_RISING_RIGHT,
//                        RABBIT_LOWERING_AND_RISING_LEFT
//                    );
//                }
//                else if ( t.isDownSlope( belowNext ) )
//                {
//                    return rl(
//                        RABBIT_LOWERING_RIGHT_CONTINUE,
//                        RABBIT_LOWERING_LEFT_CONTINUE
//                    );
//                }
//                else
//                {
//                    if ( Blocking.blockerAt( t.world, nextX, t.rabbit.y ) )
//                    {
//                        return rl(
//                            RABBIT_TURNING_RIGHT_TO_LEFT_LOWERING,
//                            RABBIT_TURNING_LEFT_TO_RIGHT_LOWERING
//                        );
//                    }
//                    else
//                    {
//                        return rl(
//                            RABBIT_LOWERING_RIGHT_END,
//                            RABBIT_LOWERING_LEFT_END
//                        );
//                    }
//                }
//            }
//            else  // On flat ground now
//            {
//                int nextX = t.nextX();
//                int nextY = t.rabbit.y;
//                Block next = t.blockNext();
//
//                if
//                    (
//                       t.isWall( next )
//                    || Blocking.blockerAt( t.world, nextX, nextY )
//                    )
//                {
//                    return rl(
//                        RABBIT_TURNING_RIGHT_TO_LEFT,
//                        RABBIT_TURNING_LEFT_TO_RIGHT
//                    );
//                }
//                else if ( t.isUpSlope( next ) )
//                {
//                    return rl(
//                        RABBIT_RISING_RIGHT_START,
//                        RABBIT_RISING_LEFT_START
//                    );
//                }
//                else if ( t.isDownSlope( t.blockBelowNext() ) )
//                {
//                    if ( Blocking.blockerAt( t.world, nextX, t.rabbit.y + 1 ) )
//                    {
//                        return rl(
//                            RABBIT_TURNING_RIGHT_TO_LEFT,
//                            RABBIT_TURNING_LEFT_TO_RIGHT
//                        );
//                    }
//                    else
//                    {
//                        return rl(
//                            RABBIT_LOWERING_RIGHT_START,
//                            RABBIT_LOWERING_LEFT_START
//                        );
//                    }
//                }
//                else
//                {
//                    return rl(
//                        RABBIT_WALKING_RIGHT,
//                        RABBIT_WALKING_LEFT
//                    );
//                }
//            }
//        }
//
//        private State rl( State rightState, State leftState )
//        {
//            return t.rl( rightState, leftState );
//        }
//    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        return false; // To avoid cancelling other behaviours, return false
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitWalkingCommon.newState( t );
//        return new StateCalc( t ).newState();
    }

    @Override
    @SuppressWarnings("fallthrough")
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if (state instanceof RabbitState)
        {
            return ((RabbitState) state).behave( world, rabbit, this );
        }else
        {
            throw new AssertionError(
                "Should have handled all states in Walking or before,"
                    + " but we are in state " + state.name()
            );
        }
//        switch ( state )
//        {
//            case RABBIT_WALKING_LEFT: // DONE
//            {
//                --rabbit.x;
//                rabbit.onSlope = false;
//                return true;
//            }
//            case RABBIT_WALKING_RIGHT: // DONE
//            {
//                ++rabbit.x;
//                rabbit.onSlope = false;
//                return true;
//            }
//            case RABBIT_LOWERING_LEFT_END: // DONE
//            {
//                --rabbit.x;
//                rabbit.onSlope = false;
//                return true;
//            }
//            case RABBIT_RISING_LEFT_START: // DONE
//            case RABBIT_LOWERING_AND_RISING_LEFT: // DONE
//            case RABBIT_RISING_AND_LOWERING_LEFT: // DONE
//            {
//                --rabbit.x;
//                rabbit.onSlope = true;
//                return true;
//            }
//            case RABBIT_LOWERING_RIGHT_END: // DONE
//            {
//                ++rabbit.x;
//                rabbit.onSlope = false;
//                return true;
//            }
//            case RABBIT_RISING_RIGHT_START: // DONE
//            case RABBIT_LOWERING_AND_RISING_RIGHT: // DONE
//            case RABBIT_RISING_AND_LOWERING_RIGHT: // DONE
//            {
//                ++rabbit.x;
//                rabbit.onSlope = true;
//                return true;
//            }
//            case RABBIT_RISING_LEFT_END: // DONE
//            {
//                --rabbit.y;
//                --rabbit.x;
//                rabbit.onSlope = false;
//                return true;
//            }
//            case RABBIT_RISING_LEFT_CONTINUE: // DONE
//            {
//                --rabbit.y;
//                --rabbit.x;
//                rabbit.onSlope = true;
//                return true;
//            }
//            case RABBIT_RISING_RIGHT_END: // DONE
//            {
//                --rabbit.y;
//                ++rabbit.x;
//                rabbit.onSlope = false;
//                return true;
//            }
//            case RABBIT_RISING_RIGHT_CONTINUE:// DONE
//            {
//                --rabbit.y;
//                ++rabbit.x;
//                rabbit.onSlope = true;
//                return true;
//            }
//            case RABBIT_LOWERING_LEFT_CONTINUE: // DONE
//            case RABBIT_LOWERING_LEFT_START: // DONE
//            {
//                ++rabbit.y;
//                --rabbit.x;
//                rabbit.onSlope = true;
//                return true;
//            }
//            case RABBIT_LOWERING_RIGHT_CONTINUE: // DONE
//            case RABBIT_LOWERING_RIGHT_START: // DONE
//            {
//                ++rabbit.y;
//                ++rabbit.x;
//                rabbit.onSlope = true;
//                return true;
//            }
//            case RABBIT_TURNING_LEFT_TO_RIGHT: // DONE
//                rabbit.onSlope = false; // Intentional fall-through
//            case RABBIT_TURNING_LEFT_TO_RIGHT_RISING: // DONE
//            case RABBIT_TURNING_LEFT_TO_RIGHT_LOWERING: // DONE
//            {
//                rabbit.dir = RIGHT;
//                checkJumpOntoSlope( world, rabbit );
//                return true;
//            }
//            case RABBIT_TURNING_RIGHT_TO_LEFT: // DONE
//                rabbit.onSlope = false; // Intentional fall-through
//            case RABBIT_TURNING_RIGHT_TO_LEFT_RISING: // DONE
//            case RABBIT_TURNING_RIGHT_TO_LEFT_LOWERING: // DONE
//            {
//                rabbit.dir = LEFT;
//                checkJumpOntoSlope( world, rabbit );
//                return true;
//            }
//            default:
//            {
//                throw new AssertionError(
//                    "Should have handled all states in Walking or before,"
//                    + " but we are in state " + state.name()
//                );
//            }
//        }
    }

    /**
     * If we turn around near a slope, we jump onto it
     */
    private void checkJumpOntoSlope( World world, Rabbit rabbit ) // TODO : 삭제
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

    private boolean isBridge( Block block ) // TODO : 삭제
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
