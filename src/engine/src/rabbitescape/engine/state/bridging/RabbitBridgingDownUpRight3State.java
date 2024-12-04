package rabbitescape.engine.state.bridging;

import rabbitescape.engine.*;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Block.Material.EARTH;
import static rabbitescape.engine.Block.Shape.BRIDGE_UP_RIGHT;

public class RabbitBridgingDownUpRight3State extends RabbitBridgingCommon
{
    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        rabbit.x++;
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
        return new Position( x + 1, y );
    }

    @Override
    public char bridgingStage( ChangeDescription.State state )
    {
        return '{';
    }
}
