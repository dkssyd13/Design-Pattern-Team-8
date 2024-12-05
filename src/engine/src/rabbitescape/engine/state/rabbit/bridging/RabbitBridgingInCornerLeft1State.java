package rabbitescape.engine.state.rabbit.bridging;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.state.State;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.util.Position;

public class RabbitBridgingInCornerLeft1State extends RabbitBridgingCommon{
    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        ( ( Bridging )behaviour ).setBridgeType( Bridging.BridgeType.ALONG );
        return true;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return new Position( x, y );
    }

    @Override
    public char bridgingStage( State state )
    {
        return 'E';
    }

    @Override
    public String name()
    {
        return "RABBIT_BRIDGING_IN_CORNER_LEFT_1";
    }
}
