package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.bridging.*;

// TODO : 주석 삭제
public class Bridging extends Behaviour
{
    public enum BridgeType
    {
        ALONG,
        UP,
        DOWN_UP
    }

    private int smallSteps = 0;
    private int bigSteps = 0;
    private BridgeType bridgeType = BridgeType.ALONG;

    @Override
    public void cancel()
    {
        bigSteps = 0;
        smallSteps = 0;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        nextStep();

        if ( bigSteps <= 0 )
            // Only pick up a token if we've finished, and we can bridge
        {
            BehaviourTools t = new BehaviourTools( rabbit, world );

            State possibleState = bridgingState( t, 3, 3, bridgeType );

            if ( possibleState != null ) // Only pick up if we can bridge
            {
                return t.pickUpToken( bridge );
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            smallSteps = 3;
            bigSteps = 3;
        }

        State ret = bridgingState( t, bigSteps, smallSteps, bridgeType );

        if ( ret == null )
        {
            bigSteps = 0;
        }

        if ( bigSteps <= 0 )
        {
            smallSteps = 0;
            return null;   // Finished bridging
        }

        return ret;
    }

    private static State bridgingState(
        BehaviourTools t,
        int bs,
        int ss,
        BridgeType bt
    )
    {
        return RabbitBridgingCommon.bridgingState( t, bs, ss, bt );
//        Block hereBlock = t.blockHere();
//
//        Rabbit rabbit = t.rabbit;
//        World world = t.world;
//
//        if ( startingIntoToWall( world, rabbit, bs ) )
//        {
//            return stateIntoWall( t, rabbit, world, ss );
//        }
//
//        boolean slopeUp = isSlopeUp( rabbit, hereBlock );
//        int nx = nextX( rabbit );
//        int ny = nextY( rabbit, slopeUp );
//
//        Block nextBlock = world.getBlockAt( nx, ny );
//
//        Block belowNextBlock = world.getBlockAt( nx, rabbit.y );
//        Block twoAboveHereBlock = world.getBlockAt( rabbit.x, rabbit.y - 2 );
//        Block aboveNextBlock = world.getBlockAt( nx, ny - 1 );
//
//        if (
//            (
//                   // Something in the way
//                   nextBlock != null
//                && nextBlock.riseDir() != rabbit.dir
//            ) || (
//                   Blocking.blockerAt( t.world, nx, ny )
//            ) || (
//                   // Clip land
//                   belowNextBlock != null
//                && belowNextBlock.riseDir() != rabbit.dir
//            ) || (
//                    // Bang head next
//                    aboveNextBlock != null
//                 && BehaviourTools.isSolid( aboveNextBlock )
//            ) || (
//                    // Bang head here, mid-build
//                    bs < 3
//                 && BehaviourTools.s_isFlat( twoAboveHereBlock )
//            )
//        )
//        {
//            return null; // Stop bridging
//        }
//
//        boolean slopeDown = (
//               ( hereBlock != null )
//            && ( hereBlock.riseDir() == Direction.opposite( rabbit.dir ) )
//        );
//
//        switch( ss )
//        {
//            case 3:
//            {
//                if ( slopeUp )
//                {
//                    return t.rl(
////                        RABBIT_BRIDGING_UP_RIGHT_1,
////                        RABBIT_BRIDGING_UP_LEFT_1
//                        new RabbitBridgingUpRight1State(),
//                        new RabbitBridgingUpLeft1State()
//                    );
//                }
//                else if ( slopeDown )
//                {
//                    return t.rl(
////                        RABBIT_BRIDGING_DOWN_UP_RIGHT_1,
////                        RABBIT_BRIDGING_DOWN_UP_LEFT_1
//                        new RabbitBridgingDownUpRight1State(),
//                        new RabbitBridgingDownUpLeft1State()
//                    );
//                }
//                else
//                {
//                    return t.rl(
////                        RABBIT_BRIDGING_RIGHT_1,
////                        RABBIT_BRIDGING_LEFT_1
//                        new RabbitBridgingRight1State(),
//                        new RabbitBridgingLeft1State()
//                    );
//                }
//            }
//            case 2:
//            {
//                switch( bt )
//                {
//                    case ALONG:
//                    {
//                        return t.rl(
////                            RABBIT_BRIDGING_RIGHT_2,
////                            RABBIT_BRIDGING_LEFT_2
//                            new RabbitBridgingRight2State(),
//                            new RabbitBridgingLeft2State()
//                        );
//                    }
//                    case UP:
//                    {
//                        return t.rl(
////                            RABBIT_BRIDGING_UP_RIGHT_2,
////                            RABBIT_BRIDGING_UP_LEFT_2
//                            new RabbitBridgingUpRight2State(),
//                            new RabbitBridgingUpLeft2State()
//                        );
//                    }
//                    case DOWN_UP:
//                    {
//                        return t.rl(
////                            RABBIT_BRIDGING_DOWN_UP_RIGHT_2,
////                            RABBIT_BRIDGING_DOWN_UP_LEFT_2
//                            new RabbitBridgingDownUpRight2State(),
//                            new RabbitBridgingDownUpLeft2State()
//                        );
//                    }
//                    default:
//                    {
//                        throw new AssertionError(
//                            "Unexpected bridge type: " + bt );
//                    }
//                }
//            }
//            case 1:
//            {
//                switch( bt )
//                {
//                    case ALONG:
//                    {
//                        return t.rl(
////                            RABBIT_BRIDGING_RIGHT_3,
////                            RABBIT_BRIDGING_LEFT_3
//                            new RabbitBridgingRight3State(),
//                            new RabbitBridgingLeft3State()
//                        );
//                    }
//                    case UP:
//                    {
//                        return t.rl(
////                            RABBIT_BRIDGING_UP_RIGHT_3,
////                            RABBIT_BRIDGING_UP_LEFT_3
//                            new RabbitBridgingUpRight3State(),
//                            new RabbitBridgingUpLeft3State()
//                        );
//                    }
//                    case DOWN_UP:
//                    {
//                        return t.rl(
////                            RABBIT_BRIDGING_DOWN_UP_RIGHT_3,
////                            RABBIT_BRIDGING_DOWN_UP_LEFT_3
//                            new RabbitBridgingDownUpRight3State(),
//                            new RabbitBridgingDownUpLeft3State()
//                        );
//                    }
//                    default:
//                    {
//                        throw new AssertionError(
//                            "Unexpected bridge type: " + bt );
//                    }
//                }
//            }
//            default:
//            {
//                return null;
//            }
//        }
    }

//    private static State stateIntoWall(
//        BehaviourTools t, Rabbit rabbit, World world, int ss )
//    {
//        // We are facing a wall.  This makes us a bit keener to
//        // bridge.
//        Block thisBlock = world.getBlockAt( rabbit.x, rabbit.y );
//
//        boolean slopeUp = isSlopeUp( rabbit, thisBlock );
//        int bx = behindX( rabbit );
//        int ny = nextY( rabbit, slopeUp );
//
//        // Don't bridge if there is no block behind us (we're not in a hole)
//        if ( isSlope( thisBlock ) && world.getBlockAt( bx, ny ) == null )
//        {
//            return null;
//        }
//
//        switch( ss )
//        {
//            case 3:
//            {
//                if ( isSlope( thisBlock ) )
//                {
//                    // Special behaviour where we bridge higher up because we
//                    // are already on top of a slope.
//
//                    Block twoAbove = world.getBlockAt( rabbit.x, rabbit.y - 2 );
//
//                    if ( twoAbove == null || twoAbove.isBridge() ) {
//                        return t.rl(
////                            RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_1,
////                            RABBIT_BRIDGING_IN_CORNER_UP_LEFT_1
//                            new RabbitBridgingInCornerUpRight1State(),
//                            new RabbitBridgingInCornerUpLeft1State()
//                        );
//                    }
//                    else
//                    {
//                        // We would hit our head, so don't bridge.
//                        return null;
//                    }
//                }
//                else
//                {
//                    return t.rl(
////                        RABBIT_BRIDGING_IN_CORNER_RIGHT_1,
////                        RABBIT_BRIDGING_IN_CORNER_LEFT_1
//                        new RabbitBridgingInCornerRight1State(),
//                        new RabbitBridgingInCornerLeft1State()
//                    );
//                }
//            }
//            case 2:
//            {
//                if ( isSlope( thisBlock ) )
//                {
//                    return t.rl(
////                        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_2,
////                        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_2
//                        new RabbitBridgingInCornerUpRight2State(),
//                        new RabbitBridgingInCornerUpLeft2State()
//                    );
//                }
//                else
//                {
//                    return t.rl(
////                        RABBIT_BRIDGING_IN_CORNER_RIGHT_2,
////                        RABBIT_BRIDGING_IN_CORNER_LEFT_2
//                        new RabbitBridgingInCornerRight2State(),
//                        new RabbitBridgingInCornerLeft2State()
//                    );
//                }
//            }
//            case 1:
//            {
//                if ( isSlope( thisBlock ) )
//                {
//                    return t.rl(
////                        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_3,
////                        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_3
//                        new RabbitBridgingInCornerUpRight3State(),
//                        new RabbitBridgingInCornerUpLeft3State()
//                    );
//                }
//                else
//                {
//                    return t.rl(
////                        RABBIT_BRIDGING_IN_CORNER_RIGHT_3,
////                        RABBIT_BRIDGING_IN_CORNER_LEFT_3
//                        new RabbitBridgingInCornerRight3State(),
//                        new RabbitBridgingInCornerLeft3State()
//                    );
//                }
//            }
//            default:
//            {
//                return null;
//            }
//        }
//    }
//
//    private static boolean startingIntoToWall(
//        World world,
//        Rabbit rabbit,
//        int bs
//    )
//    {
//        Block hereBlock = world.getBlockAt( rabbit.x, rabbit.y );
//
//        boolean slopeUp = isSlopeUp( rabbit, hereBlock );
//        int nx = nextX( rabbit );
//        int ny = nextY( rabbit, slopeUp );
//
//        Block nextBlock = world.getBlockAt( nx, ny );
//
//        return (
//           bs == 3
//        )
//        &&
//        (
//               nextBlock != null
//            &&
//            (
//                   nextBlock.riseDir() != rabbit.dir
//                || nextBlock.shape == FLAT
//            )
//         );
//    }
//
//    private static boolean isSlopeUp( Rabbit rabbit, Block hereBlock )
//    {
//        return ( hereBlock != null )
//          && ( hereBlock.riseDir() == rabbit.dir );
//    }
//
//    private static int nextY( Rabbit rabbit, boolean slopeUp )
//    {
//        int ret = rabbit.y;
//        ret += slopeUp ? -1 : 0;
//        return ret;
//    }
//
//    private static int nextX( Rabbit rabbit )
//    {
//        int ret = rabbit.x;
//        ret += rabbit.dir == Direction.RIGHT ? 1 : -1;
//        return ret;
//    }
//
//    private static int behindX( Rabbit rabbit )
//    {
//        int ret = rabbit.x;
//        ret += rabbit.dir == Direction.RIGHT ? -1 : 1;
//        return ret;
//    }

    private void nextStep()
    {
        --smallSteps;
        if ( smallSteps <= 0 )
        {
            --bigSteps;
            smallSteps = 3;
        }
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        boolean handled = moveRabbit( world, rabbit, state );

        if ( handled )
        {
            // If we're bridging, we're on a slope
            rabbit.onSlope = true;
        }

        return handled;
    }

    private boolean moveRabbit( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitBridgingCommon)
        {
            return ( (RabbitBridgingCommon) state ).moveRabbit( world, rabbit, this );
        }
        return false;
//        switch ( state )
//        {
//            case RABBIT_BRIDGING_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_LEFT_1: // DONE
//            case RABBIT_BRIDGING_LEFT_2: // DONE
//            {
//                bridgeType = BridgeType.ALONG;
//                return true;
//            }
//            case RABBIT_BRIDGING_UP_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_UP_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_UP_LEFT_1: // DONE
//            case RABBIT_BRIDGING_UP_LEFT_2: // DONE
//            {
//                bridgeType = BridgeType.UP;
//                return true;
//            }
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_1: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_2: // DONE
//            {
//                bridgeType = BridgeType.DOWN_UP;
//                return true;
//            }
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_2: // DONE
//            {
//                bridgeType = BridgeType.ALONG;
//                return true;
//            }
//            case RABBIT_BRIDGING_RIGHT_3: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_3: // DONE
//            {
//                rabbit.x++;
//                world.changes.addBlock(
//                    new Block(
//                        rabbit.x,
//                        rabbit.y,
//                        EARTH,
//                        BRIDGE_UP_RIGHT,
//                        0
//                    )
//                );
//
//                return true;
//            }
//            case RABBIT_BRIDGING_LEFT_3: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_3: // DONE
//            {
//                rabbit.x--;
//                world.changes.addBlock(
//                    new Block(
//                        rabbit.x,
//                        rabbit.y,
//                        EARTH,
//                        BRIDGE_UP_LEFT,
//                        0
//                    )
//                );
//
//                return true;
//            }
//            case RABBIT_BRIDGING_UP_RIGHT_3: // DONE
//            {
//                rabbit.x++;
//                rabbit.y--;
//                world.changes.addBlock(
//                    new Block(
//                        rabbit.x,
//                        rabbit.y,
//                        EARTH,
//                        BRIDGE_UP_RIGHT,
//                        0
//                    )
//                );
//
//                return true;
//            }
//            case RABBIT_BRIDGING_UP_LEFT_3: // DONE
//            {
//                rabbit.x--;
//                rabbit.y--;
//                world.changes.addBlock(
//                    new Block(
//                        rabbit.x,
//                        rabbit.y,
//                        EARTH,
//                        BRIDGE_UP_LEFT,
//                        0
//                    )
//                );
//
//                return true;
//            }
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_3: // DONE
//            {
//                rabbit.onSlope = true;
//                world.changes.addBlock(
//                    new Block(
//                        rabbit.x,
//                        rabbit.y,
//                        EARTH,
//                        BRIDGE_UP_RIGHT,
//                        0
//                    )
//                );
//                return true;
//            }
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_3: // DONE
//            {
//                rabbit.onSlope = true;
//                world.changes.addBlock(
//                    new Block(
//                        rabbit.x,
//                        rabbit.y,
//                        EARTH,
//                        BRIDGE_UP_LEFT,
//                        0
//                    )
//                );
//                return true;
//            }
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_3: // DONE
//            {
//                rabbit.onSlope = true;
//                rabbit.y--;
//                world.changes.addBlock(
//                    new Block(
//                        rabbit.x,
//                        rabbit.y,
//                        EARTH,
//                        BRIDGE_UP_RIGHT,
//                        0
//                    )
//                );
//                return true;
//            }
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_3: // DONE
//            {
//                rabbit.onSlope = true;
//                rabbit.y--;
//                world.changes.addBlock(
//                    new Block(
//                        rabbit.x,
//                        rabbit.y,
//                        EARTH,
//                        BRIDGE_UP_LEFT,
//                        0
//                    )
//                );
//                return true;
//            }
//            default:
//            {
//                return false;
//            }
//        }
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfNotDefault(
            saveState,
            "Bridging.bridgeType",
            bridgeType.toString(),
            BridgeType.ALONG.toString()
        );

        BehaviourState.addToStateIfGtZero(
            saveState, "Bridging.bigSteps", bigSteps
        );

        BehaviourState.addToStateIfGtZero(
            saveState, "Bridging.smallSteps", smallSteps
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        bridgeType = BridgeType.valueOf(
            BehaviourState.restoreFromState(
                saveState, "Bridging.bridgeType", bridgeType.toString()
            )
        );

        bigSteps = BehaviourState.restoreFromState(
            saveState, "Bridging.bigSteps", bigSteps
        );

        smallSteps = BehaviourState.restoreFromState(
            saveState, "Bridging.smallSteps", smallSteps
        );

        if ( smallSteps > 0 )
        {
            ++smallSteps;
        }
    }

    public BridgeType getBridgeType()
    {
        return bridgeType;
    }

    public void setBridgeType( BridgeType bridgeType )
    {
        this.bridgeType = bridgeType;
    }
}
