package rabbitescape.engine.state.bridging;

import rabbitescape.engine.*;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Block.Material.EARTH;
import static rabbitescape.engine.Block.Shape.BRIDGE_UP_LEFT;

public class RabbitBridginInCornerLeft3State extends RabbitBridgingCommon
{
    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.onSlope = true;
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
        return new Position( x, y );
    }

    @Override
    public char bridgingStage( ChangeDescription.State state )
    {
        return '}';
    }
}
