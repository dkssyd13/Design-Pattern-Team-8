package rabbitescape.engine.state.rabbit.bridging;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.util.Position;

public class RabbitBridgingInCornerUpLeft1State extends RabbitBridgingCommon{
    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        ( ( Bridging )behaviour ).setBridgeType( Bridging.BridgeType.ALONG );
        return true;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return new Position( x, y - 1 );
    }

    @Override
    public char bridgingStage( ChangeDescription.State state )
    {
        return 'E';
    }

    @Override
    public String name()
    {
        return "RABBIT_BRIDGING_IN_CORNER_UP_LEFT_1";
    }
}
