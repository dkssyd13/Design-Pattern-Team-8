package rabbitescape.engine;


import rabbitescape.engine.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the monolothic enum of rabbit and token states.
 */
public class ChangeDescription
{
//    public enum State
//    {
//        NOTHING, // DONE
//        FIRE_A, // DONE
//        FIRE_A_RISE_RIGHT, // DONE
//        FIRE_A_RISE_LEFT, // DONE
//        FIRE_A_FALLING, // DONE
//        FIRE_A_FALL_TO_RISE_LEFT, // DONE
//        FIRE_A_FALL_TO_RISE_RIGHT, // DONE
//        FIRE_B, // DONE
//        FIRE_B_RISE_RIGHT, // DONE
//        FIRE_B_RISE_LEFT, // DONE
//        FIRE_B_FALLING, // DONE
//        FIRE_B_FALL_TO_RISE_LEFT, // DONE
//        FIRE_B_FALL_TO_RISE_RIGHT, // DONE
//        FIRE_C, // DONE
//        FIRE_C_RISE_RIGHT, // DONE
//        FIRE_C_RISE_LEFT, // DONE
//        FIRE_C_FALLING, // DONE
//        FIRE_C_FALL_TO_RISE_LEFT, // DONE
//        FIRE_C_FALL_TO_RISE_RIGHT, // DONE
//        FIRE_D, // DONE
//        FIRE_D_RISE_RIGHT, // DONE
//        FIRE_D_RISE_LEFT, // DONE
//        FIRE_D_FALLING, // DONE
//        FIRE_D_FALL_TO_RISE_LEFT, // DONE
//        FIRE_D_FALL_TO_RISE_RIGHT, // DONE
//        FIRE_EXTINGUISHING,
//
//        PIPE, // DONE
//
//        RABBIT_BURNING, // DONE
//        RABBIT_BURNING_ON_SLOPE, // DONE
//        RABBIT_WALKING_LEFT, // DONE
//        RABBIT_TURNING_LEFT_TO_RIGHT, // DONE
//        RABBIT_TURNING_LEFT_TO_RIGHT_RISING, // DONE
//        RABBIT_TURNING_LEFT_TO_RIGHT_LOWERING, // DONE
//        RABBIT_WALKING_RIGHT, // DONE
//        RABBIT_TURNING_RIGHT_TO_LEFT, // DONE
//        RABBIT_TURNING_RIGHT_TO_LEFT_RISING, // DONE
//        RABBIT_TURNING_RIGHT_TO_LEFT_LOWERING, // DONE
//        RABBIT_BROLLYCHUTING, // DONE
//        RABBIT_FALLING, // DONE
//        RABBIT_FALLING_1, // DONE
//
//        /** The flat block is two squares below where this starts.*/
//        RABBIT_FALLING_1_TO_DEATH, // DONE
//
//        /** Part 2 of the animation for RABBIT_FALLING_1_TO_DEATH.*/
//        RABBIT_DYING_OF_FALLING_2, // DONE
//
//        /** The rabbit starts on the death square.*/
//        RABBIT_DYING_OF_FALLING, // DONE
//
//        /** The slope is one square below where this starts. */
//        RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT, // DONE
//
//        /** Part 2 of the animation for
//         *  RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT */
//        RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT_2, // DONE
//
//        /** The slope is two squares below where this starts. */
//        RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT, // DONE
//
//        /** Part 2 of RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT. */
//        RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT_2, // DONE
//
//        /** The slope is one square below where this starts. */
//        RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT, // DONE
//
//        /** Part 2 of the animation for
//         *  RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT */
//        RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT_2, // DONE
//
//        /** The slope is two squares below where this starts. */
//        RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT, // DONE
//
//        /** Part 2 of RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT. */
//        RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT_2, // DONE
//
//        RABBIT_FALLING_ONTO_LOWER_RIGHT, // DONE
//        RABBIT_FALLING_ONTO_RISE_RIGHT, // DONE
//        RABBIT_FALLING_ONTO_LOWER_LEFT, // DONE
//        RABBIT_FALLING_ONTO_RISE_LEFT, // DONE
//        RABBIT_FALLING_1_ONTO_LOWER_RIGHT, // DONE
//        RABBIT_FALLING_1_ONTO_RISE_RIGHT, // DONE
//        RABBIT_FALLING_1_ONTO_LOWER_LEFT, // DONE
//        RABBIT_FALLING_1_ONTO_RISE_LEFT, // DONE
//        RABBIT_RISING_RIGHT_START, // DONE
//        RABBIT_RISING_RIGHT_CONTINUE, // DONE
//        RABBIT_RISING_RIGHT_END, // DONE
//        RABBIT_RISING_LEFT_START, // DONE
//        RABBIT_RISING_LEFT_CONTINUE, // DONE
//        RABBIT_RISING_LEFT_END, // DONE
//        RABBIT_LOWERING_RIGHT_START, // DONE
//        RABBIT_LOWERING_RIGHT_CONTINUE, // DONE
//        RABBIT_LOWERING_RIGHT_END, // DONE
//        RABBIT_LOWERING_LEFT_START, // DONE
//        RABBIT_LOWERING_LEFT_CONTINUE, // DONE
//        RABBIT_LOWERING_LEFT_END, // DONE
//        RABBIT_LOWERING_AND_RISING_RIGHT, // DONE
//        RABBIT_LOWERING_AND_RISING_LEFT, // DONE
//        RABBIT_RISING_AND_LOWERING_RIGHT, // DONE
//        RABBIT_RISING_AND_LOWERING_LEFT, // DONE
//        RABBIT_ENTERING_EXIT, // DONE
//        RABBIT_ENTERING_EXIT_CLIMBING_RIGHT, // DONE
//        RABBIT_ENTERING_EXIT_CLIMBING_LEFT, // DONE
//        RABBIT_BASHING_RIGHT, // DONE
//        RABBIT_BASHING_LEFT, // DONE
//        RABBIT_BASHING_UP_RIGHT, // DONE
//        RABBIT_BASHING_UP_LEFT, // DONE
//        RABBIT_BASHING_USELESSLY_RIGHT, // DONE
//        RABBIT_BASHING_USELESSLY_LEFT, // DONE
//        RABBIT_BASHING_USELESSLY_RIGHT_UP, // DONE
//        RABBIT_BASHING_USELESSLY_LEFT_UP, // DONE
//        RABBIT_DIGGING, // DONE
//        RABBIT_DIGGING_USELESSLY, // DONE
//        RABBIT_DIGGING_ON_SLOPE, // DONE
//        RABBIT_DIGGING_2, // DONE
//        RABBIT_BRIDGING_IN_CORNER_RIGHT_1, // DONE
//        RABBIT_BRIDGING_IN_CORNER_RIGHT_2, // DONE
//        RABBIT_BRIDGING_IN_CORNER_RIGHT_3, // DONE
//        RABBIT_BRIDGING_IN_CORNER_LEFT_1, // DONE
//        RABBIT_BRIDGING_IN_CORNER_LEFT_2, // DONE
//        RABBIT_BRIDGING_IN_CORNER_LEFT_3, // DONE
//        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_1, // DONE
//        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_2, // DONE
//        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_3, // DONE
//        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_1, // DONE
//        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_2, // DONE
//        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_3, // DONE
//        RABBIT_BRIDGING_RIGHT_1, // DONE
//        RABBIT_BRIDGING_RIGHT_2, // DONE
//        RABBIT_BRIDGING_RIGHT_3, // DONE
//        RABBIT_BRIDGING_LEFT_1, // DONE
//        RABBIT_BRIDGING_LEFT_2, // DONE
//        RABBIT_BRIDGING_LEFT_3, // DONE
//        RABBIT_BRIDGING_UP_RIGHT_1, // DONE
//        RABBIT_BRIDGING_UP_RIGHT_2, // DONE
//        RABBIT_BRIDGING_UP_RIGHT_3, // DONE
//        RABBIT_BRIDGING_UP_LEFT_1, // DONE
//        RABBIT_BRIDGING_UP_LEFT_2, // DONE
//        RABBIT_BRIDGING_UP_LEFT_3, // DONE
//        RABBIT_BRIDGING_DOWN_UP_RIGHT_1, // DONE
//        RABBIT_BRIDGING_DOWN_UP_RIGHT_2, // DONE
//        RABBIT_BRIDGING_DOWN_UP_RIGHT_3, // DONE
//        RABBIT_BRIDGING_DOWN_UP_LEFT_1, // DONE
//        RABBIT_BRIDGING_DOWN_UP_LEFT_2, // DONE
//        RABBIT_BRIDGING_DOWN_UP_LEFT_3, // DONE
//        RABBIT_CLIMBING_LEFT_START, // DONE
//        RABBIT_CLIMBING_LEFT_CONTINUE_1, // DONE
//        RABBIT_CLIMBING_LEFT_CONTINUE_2, // DONE
//        RABBIT_CLIMBING_LEFT_END, // DONE
//        RABBIT_CLIMBING_LEFT_BANG_HEAD, // DONE
//        RABBIT_CLIMBING_RIGHT_START, // DONE
//        RABBIT_CLIMBING_RIGHT_CONTINUE_1, // DONE
//        RABBIT_CLIMBING_RIGHT_CONTINUE_2, // DONE
//        RABBIT_CLIMBING_RIGHT_END, // DONE
//        RABBIT_CLIMBING_RIGHT_BANG_HEAD, // DONE
//
//        RABBIT_DROWNING, // DONE
//
//        RABBIT_BLOCKING, // DONE
//        RABBIT_BLOCKING_RISE_RIGHT,// DONE
//        RABBIT_BLOCKING_RISE_LEFT,// DONE
//        RABBIT_OUT_OF_BOUNDS, // DONE
//        RABBIT_EXPLODING, // DONE
//        RABBIT_CRASHING, // DONE
//        RABBIT_WAITING_LEFT, // DONE
//        RABBIT_WAITING_RIGHT, // DONE
//        TOKEN_BASH_STILL, // DONE
//        TOKEN_BASH_FALLING, // DONE
//        TOKEN_BASH_FALL_TO_SLOPE, // DONE
//        TOKEN_BASH_ON_SLOPE, // DONE
//
//        TOKEN_DIG_STILL, // DONE
//        TOKEN_DIG_FALLING, // DONE
//        TOKEN_DIG_FALL_TO_SLOPE, // DONE
//        TOKEN_DIG_ON_SLOPE, // DONE
//
//        TOKEN_BRIDGE_STILL, // DONE
//        TOKEN_BRIDGE_FALLING, // DONE
//        TOKEN_BRIDGE_FALL_TO_SLOPE, // DONE
//        TOKEN_BRIDGE_ON_SLOPE, // DONE
//
//        TOKEN_BLOCK_STILL, // DONE
//        TOKEN_BLOCK_FALLING, // DONE
//        TOKEN_BLOCK_FALL_TO_SLOPE, // DONE
//        TOKEN_BLOCK_ON_SLOPE, // DONE
//
//        TOKEN_CLIMB_STILL, // DONE
//        TOKEN_CLIMB_FALLING, // DONE
//        TOKEN_CLIMB_FALL_TO_SLOPE, // DONE
//        TOKEN_CLIMB_ON_SLOPE, // DONE
//
//        TOKEN_EXPLODE_FALLING, // DONE
//        TOKEN_EXPLODE_STILL, // DONE
//        TOKEN_EXPLODE_FALL_TO_SLOPE, // DONE
//        TOKEN_EXPLODE_ON_SLOPE, // DONE
//
//        TOKEN_BROLLY_FALLING, // DONE
//        TOKEN_BROLLY_STILL, // DONE
//        TOKEN_BROLLY_FALL_TO_SLOPE, // DONE
//        TOKEN_BROLLY_ON_SLOPE, // DONE
//
//        WATER_REGION, // DONE
//        WATER_REGION_HALF, // DONE
//        WATER_REGION_EMPTY, // DONE
//        WATER_REGION_FALLING, // DONE
//
//        ENTRANCE, // DONE
//        EXIT,
//    }

    public static class Change
    {
        public final int x;
        public final int y;
//        public final State state; // TODO : 주석 삭제
        public final State state;

        public Change( int x, int y, State state )
        {
            this.x = x;
            this.y = y;
            this.state = state;
        }

//        public Change(
//            int x,
//            int y,
//            rabbitescape.engine.state.State statE
//        )
//        {
//            this.x = x;
//            this.y = y;
//            this.state = null;
//            this.statE = statE;
//        }
    }

    public final List<Change> changes = new ArrayList<>();

    public void add( int x, int y, State state )
    {
        changes.add( new Change( x, y, state ) );
    }
//
//    public void add(int x, int y, rabbitescape.engine.state.State state) {
//        changes.add( new Change( x, y, state ) );
//    }
}
