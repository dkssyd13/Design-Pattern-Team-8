package rabbitescape.engine.state.rabbit.bridging;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Blocking;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.textworld.Chars;

import static rabbitescape.engine.Block.Shape.FLAT;

public abstract class RabbitBridgingCommon implements RabbitState
{

    @Override
    public boolean rabbitIsFalling()
    {
        return false;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        boolean handled = moveRabbit( world, rabbit, behaviour);

        if ( handled )
        {
            // If we're bridging, we're on a slope
            rabbit.onSlope = true;
        }

        return handled;
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
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }

    public static State bridgingState( // TODO : 주석 삭제
        BehaviourTools t,
        int bs,
        int ss,
        Bridging.BridgeType bt
    )
    {
        Block hereBlock = t.blockHere();

        Rabbit rabbit = t.rabbit;
        World world = t.world;

        if ( startingIntoToWall( world, rabbit, bs ) )
        {
            return stateIntoWall( t, rabbit, world, ss );
        }

        boolean slopeUp = isSlopeUp( rabbit, hereBlock );
        int nx = nextX( rabbit );
        int ny = nextY( rabbit, slopeUp );

        Block nextBlock = world.getBlockAt( nx, ny );

        Block belowNextBlock = world.getBlockAt( nx, rabbit.y );
        Block twoAboveHereBlock = world.getBlockAt( rabbit.x, rabbit.y - 2 );
        Block aboveNextBlock = world.getBlockAt( nx, ny - 1 );

        if (
            (
                // Something in the way
                nextBlock != null
                    && nextBlock.riseDir() != rabbit.dir
            ) || (
                Blocking.blockerAt( t.world, nx, ny )
            ) || (
                // Clip land
                belowNextBlock != null
                    && belowNextBlock.riseDir() != rabbit.dir
            ) || (
                // Bang head next
                aboveNextBlock != null
                    && BehaviourTools.isSolid( aboveNextBlock )
            ) || (
                // Bang head here, mid-build
                bs < 3
                    && BehaviourTools.s_isFlat( twoAboveHereBlock )
            )
        )
        {
            return null; // Stop bridging
        }

        boolean slopeDown = (
            ( hereBlock != null )
                && ( hereBlock.riseDir() == Direction.opposite( rabbit.dir ) )
        );

        switch( ss )
        {
            case 3:
            {
                if ( slopeUp )
                {
                    return t.rl(
//                        RABBIT_BRIDGING_UP_RIGHT_1,
//                        RABBIT_BRIDGING_UP_LEFT_1
                        new RabbitBridgingUpRight1State(),
                        new RabbitBridgingUpLeft1State()
                    );
                }
                else if ( slopeDown )
                {
                    return t.rl(
//                        RABBIT_BRIDGING_DOWN_UP_RIGHT_1,
//                        RABBIT_BRIDGING_DOWN_UP_LEFT_1
                        new RabbitBridgingDownUpRight1State(),
                        new RabbitBridgingDownUpLeft1State()
                    );
                }
                else
                {
                    return t.rl(
//                        RABBIT_BRIDGING_RIGHT_1,
//                        RABBIT_BRIDGING_LEFT_1
                        new RabbitBridgingRight1State(),
                        new RabbitBridgingLeft1State()
                    );
                }
            }
            case 2:
            {
                switch( bt )
                {
                    case ALONG:
                    {
                        return t.rl(
//                            RABBIT_BRIDGING_RIGHT_2,
//                            RABBIT_BRIDGING_LEFT_2
                            new RabbitBridgingRight2State(),
                            new RabbitBridgingLeft2State()
                        );
                    }
                    case UP:
                    {
                        return t.rl(
//                            RABBIT_BRIDGING_UP_RIGHT_2,
//                            RABBIT_BRIDGING_UP_LEFT_2
                            new RabbitBridgingUpRight2State(),
                            new RabbitBridgingUpLeft2State()
                        );
                    }
                    case DOWN_UP:
                    {
                        return t.rl(
//                            RABBIT_BRIDGING_DOWN_UP_RIGHT_2,
//                            RABBIT_BRIDGING_DOWN_UP_LEFT_2
                            new RabbitBridgingDownUpRight2State(),
                            new RabbitBridgingDownUpLeft2State()
                        );
                    }
                    default:
                    {
                        throw new AssertionError(
                            "Unexpected bridge type: " + bt );
                    }
                }
            }
            case 1:
            {
                switch( bt )
                {
                    case ALONG:
                    {
                        return t.rl(
//                            RABBIT_BRIDGING_RIGHT_3,
//                            RABBIT_BRIDGING_LEFT_3
                            new RabbitBridgingRight3State(),
                            new RabbitBridgingLeft3State()
                        );
                    }
                    case UP:
                    {
                        return t.rl(
//                            RABBIT_BRIDGING_UP_RIGHT_3,
//                            RABBIT_BRIDGING_UP_LEFT_3
                            new RabbitBridgingUpRight3State(),
                            new RabbitBridgingUpLeft3State()
                        );
                    }
                    case DOWN_UP:
                    {
                        return t.rl(
//                            RABBIT_BRIDGING_DOWN_UP_RIGHT_3,
//                            RABBIT_BRIDGING_DOWN_UP_LEFT_3
                            new RabbitBridgingDownUpRight3State(),
                            new RabbitBridgingDownUpLeft3State()
                        );
                    }
                    default:
                    {
                        throw new AssertionError(
                            "Unexpected bridge type: " + bt );
                    }
                }
            }
            default:
            {
                return null;
            }
        }
    }

    private static State stateIntoWall(
        BehaviourTools t, Rabbit rabbit, World world, int ss )
    {
        // We are facing a wall.  This makes us a bit keener to
        // bridge.
        Block thisBlock = world.getBlockAt( rabbit.x, rabbit.y );

        boolean slopeUp = isSlopeUp( rabbit, thisBlock );
        int bx = behindX( rabbit );
        int ny = nextY( rabbit, slopeUp );

        // Don't bridge if there is no block behind us (we're not in a hole)
        if ( isSlope( thisBlock ) && world.getBlockAt( bx, ny ) == null )
        {
            return null;
        }

        switch( ss )
        {
            case 3:
            {
                if ( isSlope( thisBlock ) )
                {
                    // Special behaviour where we bridge higher up because we
                    // are already on top of a slope.

                    Block twoAbove = world.getBlockAt( rabbit.x, rabbit.y - 2 );

                    if ( twoAbove == null || twoAbove.isBridge() ) {
                        return t.rl(
//                            RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_1,
//                            RABBIT_BRIDGING_IN_CORNER_UP_LEFT_1
                            new RabbitBridgingInCornerUpRight1State(),
                            new RabbitBridgingInCornerUpLeft1State()
                        );
                    }
                    else
                    {
                        // We would hit our head, so don't bridge.
                        return null;
                    }
                }
                else
                {
                    return t.rl(
//                        RABBIT_BRIDGING_IN_CORNER_RIGHT_1,
//                        RABBIT_BRIDGING_IN_CORNER_LEFT_1
                        new RabbitBridgingInCornerRight1State(),
                        new RabbitBridgingInCornerLeft1State()
                    );
                }
            }
            case 2:
            {
                if ( isSlope( thisBlock ) )
                {
                    return t.rl(
//                        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_2,
//                        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_2
                        new RabbitBridgingInCornerUpRight2State(),
                        new RabbitBridgingInCornerUpLeft2State()
                    );
                }
                else
                {
                    return t.rl(
//                        RABBIT_BRIDGING_IN_CORNER_RIGHT_2,
//                        RABBIT_BRIDGING_IN_CORNER_LEFT_2
                        new RabbitBridgingInCornerRight2State(),
                        new RabbitBridgingInCornerLeft2State()
                    );
                }
            }
            case 1:
            {
                if ( isSlope( thisBlock ) )
                {
                    return t.rl(
//                        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_3,
//                        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_3
                        new RabbitBridgingInCornerUpRight3State(),
                        new RabbitBridgingInCornerUpLeft3State()
                    );
                }
                else
                {
                    return t.rl(
//                        RABBIT_BRIDGING_IN_CORNER_RIGHT_3,
//                        RABBIT_BRIDGING_IN_CORNER_LEFT_3
                        new RabbitBridgingInCornerRight3State(),
                        new RabbitBridgingInCornerLeft3State()
                    );
                }
            }
            default:
            {
                return null;
            }
        }
    }

    private static boolean startingIntoToWall(
        World world,
        Rabbit rabbit,
        int bs
    )
    {
        Block hereBlock = world.getBlockAt( rabbit.x, rabbit.y );

        boolean slopeUp = isSlopeUp( rabbit, hereBlock );
        int nx = nextX( rabbit );
        int ny = nextY( rabbit, slopeUp );

        Block nextBlock = world.getBlockAt( nx, ny );

        return (
            bs == 3
        )
            &&
            (
                nextBlock != null
                    &&
                    (
                        nextBlock.riseDir() != rabbit.dir
                            || nextBlock.shape == FLAT
                    )
            );
    }


    private static boolean isSlopeUp( Rabbit rabbit, Block hereBlock )
    {
        return ( hereBlock != null )
            && ( hereBlock.riseDir() == rabbit.dir );
    }

    private static int nextY( Rabbit rabbit, boolean slopeUp )
    {
        int ret = rabbit.y;
        ret += slopeUp ? -1 : 0;
        return ret;
    }

    private static int nextX( Rabbit rabbit )
    {
        int ret = rabbit.x;
        ret += rabbit.dir == Direction.RIGHT ? 1 : -1;
        return ret;
    }

    private static int behindX( Rabbit rabbit )
    {
        int ret = rabbit.x;
        ret += rabbit.dir == Direction.RIGHT ? -1 : 1;
        return ret;
    }

    private static boolean isSlope( Block thisBlock )
    {
        return ( thisBlock != null && thisBlock.shape != FLAT );
    }
}
