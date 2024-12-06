package rabbitescape.engine.state.rabbit.bridging;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Block.Material.EARTH;
import static rabbitescape.engine.Block.Shape.BRIDGE_UP_RIGHT;

public class RabbitBridgingInCornerUpRight3State extends RabbitBridgingCommon
{
    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.onSlope = true;
        rabbit.y--;
        world.changes.addBlock(
            new Block(
                rabbit.x,
                rabbit.y,
                EARTH,
                BRIDGE_UP_RIGHT,
                0
            )
        );
        return true;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return new Position( x, y - 1 );
    }

    @Override
    public char bridgingStage( State state )
    {
        return '{';
    }

    @Override
    public String name()
    {
        return "RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_3";
    }
}