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
//        switch( change.state ) // TODO : 주석 삭제
//        {
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_3: //DONE
//            case RABBIT_BRIDGING_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_RIGHT_3: // DONE
//                return new Position( change.x + 1, change.y );
//
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_1: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_2: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_3: // DONE
//            case RABBIT_BRIDGING_LEFT_1: // DONE
//            case RABBIT_BRIDGING_LEFT_2: // DONE
//            case RABBIT_BRIDGING_LEFT_3: // DONE
//                return new Position( change.x - 1, change.y );
//
//            case RABBIT_BRIDGING_UP_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_UP_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_UP_RIGHT_3: // DONE
//                return new Position( change.x + 1, change.y - 1 );
//
//            case RABBIT_BRIDGING_UP_LEFT_1: // DONE
//            case RABBIT_BRIDGING_UP_LEFT_2: // DONE
//            case RABBIT_BRIDGING_UP_LEFT_3: // DONE
//                return new Position( change.x - 1, change.y - 1 );
//
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_3: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_1:// DONE
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_2:// DONE
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_3:// DONE
//                return new Position( change.x, change.y );
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_3: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_3: // DONE
//                return new Position( change.x, change.y - 1 );
//            default:
//                return null;
//        }
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
//        switch ( state )
//        {
//            case RABBIT_BRIDGING_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_UP_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_1: // DONE
//                return 'B';
//            case RABBIT_BRIDGING_LEFT_1: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_1: // DONE
//            case RABBIT_BRIDGING_UP_LEFT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_1: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_1: // DONE
//                return 'E';
//            case RABBIT_BRIDGING_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_UP_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_2: // DONE
//                return '[';
//            case RABBIT_BRIDGING_LEFT_2: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_2: // DONE
//            case RABBIT_BRIDGING_UP_LEFT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_2: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_2: // DONE
//                return ']';
//            case RABBIT_BRIDGING_RIGHT_3: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_RIGHT_3: // DONE
//            case RABBIT_BRIDGING_UP_RIGHT_3: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_RIGHT_3: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_3: // DONE
//                return '{';
//            case RABBIT_BRIDGING_LEFT_3: // DONE
//            case RABBIT_BRIDGING_DOWN_UP_LEFT_3: // DONE
//            case RABBIT_BRIDGING_UP_LEFT_3: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_LEFT_3: // DONE
//            case RABBIT_BRIDGING_IN_CORNER_UP_LEFT_3: // DONE
//                return '}';
//            default:
//                return ' ';
//        }
    }
}
