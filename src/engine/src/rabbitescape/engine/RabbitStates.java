package rabbitescape.engine;

import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.bridging.RabbitBridgingCommon;
import rabbitescape.engine.util.Position;

public class RabbitStates
{
    public static Position whereBridging( StateAndPosition change )
    {
        if ( change.state instanceof RabbitBridgingCommon )
        {
            return ((RabbitBridgingCommon) change.state).whereBridging( change.x, change.y );
        }else
        {
            return null;
        }
    }

    public static char bridgingStage( State state )
    {
        if ( state instanceof RabbitBridgingCommon )
        {
            return ((RabbitBridgingCommon) state).bridgingStage( state );
        }else
        {
            return ' ';
        }
    }
}
