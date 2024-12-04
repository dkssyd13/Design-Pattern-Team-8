package rabbitescape.engine.textworld;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.ChangeDescription.Change;
import rabbitescape.engine.RabbitStates;
import rabbitescape.engine.StateAndPosition;
import rabbitescape.engine.util.Position;

public class ChangeRenderer
{
    public static void render( Chars chars, ChangeDescription desc )
    {
        for ( ChangeDescription.Change change : desc.changes )
        {
            charForChange( change, chars );
        }
    }

    private static void charForChange( Change change, Chars chars )
    {

        if (change.statE != null) { // TODO : 모든 state 클래스 만든 후 올바른 위치에 ( + if 문 제거 == change 클래스 수정)
            change.statE.charForChange(change, chars);
            return;
        }

        // Handle bridging specially
        Position bridgingPos = RabbitStates.whereBridging(
            new StateAndPosition( change.state, change.x, change.y ) );

        if ( bridgingPos != null )
        {
            chars.set(
                bridgingPos.x,
                bridgingPos.y,
                RabbitStates.bridgingStage( change.state )
            );
            return;
        }

        // Everything else is relatively simple
        switch( change.state )
        {
            case NOTHING:
                break;
            case FIRE_A:
            case FIRE_B:
            case FIRE_C:
            case FIRE_D:
            case FIRE_A_RISE_RIGHT:
            case FIRE_B_RISE_RIGHT:
            case FIRE_C_RISE_RIGHT:
            case FIRE_D_RISE_RIGHT:
            case FIRE_A_RISE_LEFT:
            case FIRE_B_RISE_LEFT:
            case FIRE_C_RISE_LEFT:
            case FIRE_D_RISE_LEFT:
                break;
            case FIRE_A_FALLING:
            case FIRE_B_FALLING:
            case FIRE_C_FALLING:
            case FIRE_D_FALLING:
            case FIRE_A_FALL_TO_RISE_RIGHT:
            case FIRE_B_FALL_TO_RISE_RIGHT:
            case FIRE_C_FALL_TO_RISE_RIGHT:
            case FIRE_D_FALL_TO_RISE_RIGHT:
            case FIRE_A_FALL_TO_RISE_LEFT:
            case FIRE_B_FALL_TO_RISE_LEFT:
            case FIRE_C_FALL_TO_RISE_LEFT:
            case FIRE_D_FALL_TO_RISE_LEFT:
                chars.set(  change.x, change.y + 1, 'g' );
                break;
            case FIRE_EXTINGUISHING:
                break;
            case PIPE:
                chars.set( change.x, change.y, 'P' );
                break;
            case RABBIT_BURNING:
            case RABBIT_BURNING_ON_SLOPE:
                chars.set(  change.x, change.y, 'X' );
                break;
            case RABBIT_WALKING_LEFT:
                chars.set( change.x-1, change.y, '<' );
                break;
            case RABBIT_TURNING_LEFT_TO_RIGHT:
                chars.set( change.x, change.y, '|' );
                break;
            case RABBIT_TURNING_LEFT_TO_RIGHT_RISING:
                chars.set( change.x, change.y, '|' );
                break;
            case RABBIT_TURNING_LEFT_TO_RIGHT_LOWERING:
                chars.set( change.x, change.y, '[' );
                break;
            case RABBIT_WALKING_RIGHT:
                chars.set( change.x + 1, change.y, '>' );
                break;
            case RABBIT_TURNING_RIGHT_TO_LEFT:
                chars.set( change.x, change.y, '?' );
                break;
            case RABBIT_TURNING_RIGHT_TO_LEFT_RISING:
                chars.set( change.x, change.y, '?' );
                break;
            case RABBIT_TURNING_RIGHT_TO_LEFT_LOWERING:
                chars.set( change.x, change.y, ']' );
                break;
            case RABBIT_RISING_RIGHT_START: // DONE
                chars.set( change.x + 1, change.y, '~' );
                break;
            case RABBIT_RISING_RIGHT_CONTINUE: // DONE
                chars.set( change.x + 1, change.y - 1, '$' );
                break;
            case RABBIT_RISING_RIGHT_END: // DONE
                chars.set( change.x + 1, change.y - 1, '\'' );
                break;
            case RABBIT_RISING_LEFT_START: // DONE
                chars.set( change.x - 1, change.y, '`' );
                break;
            case RABBIT_RISING_LEFT_CONTINUE: // DONE
                chars.set( change.x - 1, change.y - 1, '^' );
                break;
            case RABBIT_RISING_LEFT_END: // DONE
                chars.set( change.x - 1, change.y - 1, '!' );
                break;
            case RABBIT_LOWERING_RIGHT_START: // DONE
                chars.set( change.x + 1, change.y + 1, '-' );
                break;
            case RABBIT_LOWERING_RIGHT_CONTINUE: // DONE
                chars.set( change.x + 1, change.y + 1, '@' );
                break;
            case RABBIT_LOWERING_RIGHT_END: // DONE
                chars.set( change.x + 1, change.y, '_' );
                break;
            case RABBIT_LOWERING_LEFT_START: // DONE
                chars.set( change.x - 1, change.y + 1, '=' );
                break;
            case RABBIT_LOWERING_LEFT_CONTINUE: // DONE
                chars.set( change.x - 1, change.y + 1, '%' );
                break;
            case RABBIT_LOWERING_LEFT_END: // DONE
                chars.set( change.x - 1, change.y, '+' );
                break;
            case RABBIT_LOWERING_AND_RISING_RIGHT: // DONE
                chars.set( change.x + 1, change.y, ',' );
                break;
            case RABBIT_LOWERING_AND_RISING_LEFT: // DONE
                chars.set( change.x - 1, change.y, '.' );
                break;
            case RABBIT_RISING_AND_LOWERING_RIGHT: // DONE
                chars.set( change.x + 1, change.y, '&' );
                break;
            case RABBIT_RISING_AND_LOWERING_LEFT: // DONE
                chars.set( change.x - 1, change.y, 'm' );
                break;
            case RABBIT_BROLLYCHUTING:
                chars.set( change.x, change.y + 1, ':' );
                break;
            case RABBIT_FALLING: // DONE
                chars.set( change.x, change.y + 1, 'f' );
                chars.set( change.x, change.y + 2, 'f' );
                break;
            case RABBIT_FALLING_ONTO_LOWER_RIGHT: // DONE
                chars.set( change.x, change.y + 1, 'f' );
                chars.set( change.x, change.y + 2, 'e' );
                break;
            case RABBIT_FALLING_ONTO_LOWER_LEFT: // DONE
                chars.set( change.x, change.y + 1, 'f' );
                chars.set( change.x, change.y + 2, 's' );
                break;
            case RABBIT_FALLING_ONTO_RISE_RIGHT: // DONE
                chars.set( change.x, change.y + 1, 'f' );
                chars.set( change.x, change.y + 2, 'h' );
                break;
            case RABBIT_FALLING_ONTO_RISE_LEFT:
                chars.set( change.x, change.y + 1, 'f' );
                chars.set( change.x, change.y + 2, 'a' );
                break;
            case RABBIT_FALLING_1: // DONE
                chars.set( change.x, change.y + 1, 'f' );
                break;
            case RABBIT_FALLING_1_ONTO_LOWER_RIGHT: // DONE
                chars.set( change.x, change.y + 1, 'e' );
                break;
            case RABBIT_FALLING_1_ONTO_LOWER_LEFT: // DONE
                chars.set( change.x, change.y + 1, 's' );
                break;
            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT: // DONE
            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT: // DONE
                chars.set( change.x, change.y + 1, 'f' );
                chars.set( change.x, change.y + 2, 'x' );
                break;
            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT_2: // DONE
            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT_2: // DONE
                chars.set( change.x, change.y, 'X' );
                break;
            case RABBIT_FALLING_1_ONTO_RISE_RIGHT: // DONE
                chars.set( change.x, change.y + 1, 'h' );
                break;
            case RABBIT_FALLING_1_ONTO_RISE_LEFT: // DONE
                chars.set( change.x, change.y + 1, 'a' );
                break;
            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT: // DONE
            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT: // DONE
                chars.set( change.x, change.y + 1, 'x' );
                break;
            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT_2: // DONE
            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT_2: // DONE
                chars.set( change.x, change.y, 'y' );
                break;
            case RABBIT_FALLING_1_TO_DEATH: // DONE
                chars.set( change.x, change.y + 1, 'x' );
                break;
            case RABBIT_DYING_OF_FALLING_2: // DONE
                chars.set( change.x, change.y, 'y' );
                break;
            case RABBIT_DYING_OF_FALLING: // DONE
                chars.set( change.x, change.y, 'X' );
                break;
            case RABBIT_ENTERING_EXIT: // DONE
                chars.set( change.x, change.y, 'R' );
                break;
            case RABBIT_BASHING_RIGHT: // DONE
                chars.set( change.x + 1, change.y, 'K' );
                break;
            case RABBIT_BASHING_LEFT: // DONE
                chars.set( change.x - 1, change.y, 'W' );
                break;
            case RABBIT_BASHING_UP_RIGHT: // DONE
                chars.set( change.x + 1, change.y - 1, 'K' );
                break;
            case RABBIT_BASHING_UP_LEFT: // DONE
                chars.set( change.x - 1, change.y - 1, 'W' );
                break;
            case RABBIT_BASHING_USELESSLY_RIGHT: // DONE
                chars.set( change.x + 1, change.y, 'I' );
                break;
            case RABBIT_BASHING_USELESSLY_LEFT: // DONE
                chars.set( change.x - 1, change.y, 'J' );
                break;
            case RABBIT_BASHING_USELESSLY_RIGHT_UP: // DONE
                chars.set( change.x + 1, change.y, 'I' );
                break;
            case RABBIT_BASHING_USELESSLY_LEFT_UP: // DONE
                chars.set( change.x - 1, change.y, 'J' );
                break;
            case RABBIT_CLIMBING_LEFT_START: // DONE
                chars.set( change.x, change.y, 'T' );
                break;
            case RABBIT_CLIMBING_LEFT_CONTINUE_1: // DONE
            case RABBIT_CLIMBING_LEFT_CONTINUE_2: // DONE
                chars.set( change.x, change.y - 1, 'Y' );
                break;
            case RABBIT_CLIMBING_LEFT_END: // DONE
                chars.set( change.x - 1, change.y - 1, 'U' );
                break;
            case RABBIT_CLIMBING_LEFT_BANG_HEAD: // DONE
                chars.set( change.x, change.y, 'Y' );
                break;
            case RABBIT_CLIMBING_RIGHT_START: // DONE
                chars.set( change.x, change.y, 'G' );
                break;
            case RABBIT_CLIMBING_RIGHT_CONTINUE_1: // DONE
            case RABBIT_CLIMBING_RIGHT_CONTINUE_2: // DONE
                chars.set( change.x, change.y - 1, 'F' );
                break;
            case RABBIT_CLIMBING_RIGHT_END: // DONE
                chars.set( change.x + 1, change.y - 1, 'L' );
                break;
            case RABBIT_CLIMBING_RIGHT_BANG_HEAD: // DONE
                chars.set( change.x, change.y, 'F' );
                break;
            case RABBIT_DIGGING: // DONE
                chars.set( change.x, change.y + 1, 'D' );
                break;
            case RABBIT_DIGGING_USELESSLY: // DONE
                chars.set( change.x, change.y + 1, 'D' );
                break;
            case RABBIT_DIGGING_ON_SLOPE: // DONE
                chars.set( change.x, change.y, 'D' );
                break;
            case RABBIT_DIGGING_2: // DONE
                chars.set( change.x, change.y, 'D' );
                break;
            case RABBIT_BLOCKING:
            case RABBIT_BLOCKING_RISE_RIGHT:
            case RABBIT_BLOCKING_RISE_LEFT:
                chars.set( change.x, change.y, 'H' );
                break;
            case RABBIT_EXPLODING:
                chars.set( change.x, change.y, 'P' );
                break;
            case RABBIT_DROWNING:
                chars.set( change.x, change.y, 'R' );
                break;
            case RABBIT_CRASHING:
                chars.set( change.x, change.y, 'Z' );
                break;
            case RABBIT_WAITING_LEFT:
            case RABBIT_WAITING_RIGHT:
                chars.set( change.x, change.y, 'z' );
                break;
            case RABBIT_OUT_OF_BOUNDS:
                break;
            case TOKEN_BASH_STILL:
            case TOKEN_BASH_ON_SLOPE:
            case TOKEN_DIG_STILL:
            case TOKEN_DIG_ON_SLOPE:
            case TOKEN_BRIDGE_STILL:
            case TOKEN_BRIDGE_ON_SLOPE:
            case TOKEN_BLOCK_STILL:
            case TOKEN_BLOCK_ON_SLOPE:
            case TOKEN_CLIMB_STILL:
            case TOKEN_CLIMB_ON_SLOPE:
            case TOKEN_EXPLODE_STILL:
            case TOKEN_EXPLODE_ON_SLOPE:
            case TOKEN_BROLLY_STILL:
            case TOKEN_BROLLY_ON_SLOPE:
                break;
            case TOKEN_BASH_FALLING:
            case TOKEN_BASH_FALL_TO_SLOPE:
            case TOKEN_DIG_FALLING:
            case TOKEN_DIG_FALL_TO_SLOPE:
            case TOKEN_BRIDGE_FALLING:
            case TOKEN_BRIDGE_FALL_TO_SLOPE:
            case TOKEN_BLOCK_FALLING:
            case TOKEN_BLOCK_FALL_TO_SLOPE:
            case TOKEN_CLIMB_FALLING:
            case TOKEN_CLIMB_FALL_TO_SLOPE:
            case TOKEN_EXPLODE_FALLING:
            case TOKEN_EXPLODE_FALL_TO_SLOPE:
            case TOKEN_BROLLY_FALLING:
            case TOKEN_BROLLY_FALL_TO_SLOPE:
                chars.set( change.x, change.y + 1, 'f' );
                break;
            case WATER_REGION:
                chars.set( change.x, change.y, 'N' );
                break;
            case WATER_REGION_HALF:
                chars.set( change.x, change.y, 'n' );
                break;
            case WATER_REGION_FALLING:
                chars.set( change.x, change.y, 'n' );
                break;
            case WATER_REGION_EMPTY:
                break;
            case ENTRANCE:
                break;
            case EXIT:
                break;
            default:
                throw new AssertionError(
                    "Unknown Change state: " + change.state.name() );
        }
    }
}
