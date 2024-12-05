package rabbitescape.engine.state.rabbit.bridging;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Block.Material.EARTH;
import static rabbitescape.engine.Block.Shape.BRIDGE_UP_LEFT;

public class RabbitBridgingDownUpLeft3State extends RabbitBridgingCommon
{
    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.x--;
        world.changes.addBlock(
            new Block(
                rabbit.x,
                rabbit.y,
                EARTH,
                BRIDGE_UP_LEFT,
                0
            )
        );

        return true;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return new Position( x - 1, y );
    }

    @Override
    public char bridgingStage( State state )
    {
        return '}';
    }

    @Override
    public String name()
    {
        return "RABBIT_BRIDGING_DOWN_UP_LEFT_3";
    }
}
