package rabbitescape.engine.state.rabbit.bridging;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.util.Position;

public class RabbitBridgingDownUpLeft2State extends RabbitBridgingCommon
{
    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        ( ( Bridging )behaviour ).setBridgeType( Bridging.BridgeType.DOWN_UP );
        return true;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return new Position( x - 1, y );
    }

    @Override
    public char bridgingStage( ChangeDescription.State state )
    {
        return ']';
    }

    @Override
    public String name()
    {
        return "RABBIT_BRIDGING_DOWN_UP_LEFT_2";
    }
}
