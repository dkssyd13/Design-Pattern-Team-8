package rabbitescape.engine;

import rabbitescape.engine.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the monolothic enum of rabbit and token states.
 */
public class ChangeDescription
{
    public enum State
    {
        NOTHING,
        FIRE_A,
        FIRE_A_RISE_RIGHT,
        FIRE_A_RISE_LEFT,
        FIRE_A_FALLING,
        FIRE_A_FALL_TO_RISE_LEFT,
        FIRE_A_FALL_TO_RISE_RIGHT,
        FIRE_B,
        FIRE_B_RISE_RIGHT,
        FIRE_B_RISE_LEFT,
        FIRE_B_FALLING,
        FIRE_B_FALL_TO_RISE_LEFT,
        FIRE_B_FALL_TO_RISE_RIGHT,
        FIRE_C,
        FIRE_C_RISE_RIGHT,
        FIRE_C_RISE_LEFT,
        FIRE_C_FALLING,
        FIRE_C_FALL_TO_RISE_LEFT,
        FIRE_C_FALL_TO_RISE_RIGHT,
        FIRE_D,
        FIRE_D_RISE_RIGHT,
        FIRE_D_RISE_LEFT,
        FIRE_D_FALLING,
        FIRE_D_FALL_TO_RISE_LEFT,
        FIRE_D_FALL_TO_RISE_RIGHT,
        FIRE_EXTINGUISHING,

        PIPE,

        RABBIT_BURNING,
        RABBIT_BURNING_ON_SLOPE,
        RABBIT_WALKING_LEFT,
        RABBIT_TURNING_LEFT_TO_RIGHT,
        RABBIT_TURNING_LEFT_TO_RIGHT_RISING,
        RABBIT_TURNING_LEFT_TO_RIGHT_LOWERING,
        RABBIT_WALKING_RIGHT,
        RABBIT_TURNING_RIGHT_TO_LEFT,
        RABBIT_TURNING_RIGHT_TO_LEFT_RISING,
        RABBIT_TURNING_RIGHT_TO_LEFT_LOWERING,
        RABBIT_BROLLYCHUTING,
        RABBIT_FALLING, // DONE
        RABBIT_FALLING_1, // DONE

        /** The flat block is two squares below where this starts.*/
        RABBIT_FALLING_1_TO_DEATH, // DONE

        /** Part 2 of the animation for RABBIT_FALLING_1_TO_DEATH.*/
        RABBIT_DYING_OF_FALLING_2, // DONE

        /** The rabbit starts on the death square.*/
        RABBIT_DYING_OF_FALLING, // DONE

        /** The slope is one square below where this starts. */
        RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT, // DONE

        /** Part 2 of the animation for
         *  RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT */
        RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT_2, // DONE

        /** The slope is two squares below where this starts. */
        RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT, // DONE

        /** Part 2 of RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT. */
        RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT_2, // DONE

        /** The slope is one square below where this starts. */
        RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT, // DONE

        /** Part 2 of the animation for
         *  RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT */
        RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT_2, // DONE

        /** The slope is two squares below where this starts. */
        RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT, // DONE

        /** Part 2 of RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT. */
        RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT_2, // DONE

        RABBIT_FALLING_ONTO_LOWER_RIGHT, // DONE
        RABBIT_FALLING_ONTO_RISE_RIGHT, // DONE
        RABBIT_FALLING_ONTO_LOWER_LEFT, // DONE
        RABBIT_FALLING_ONTO_RISE_LEFT, // DONE
        RABBIT_FALLING_1_ONTO_LOWER_RIGHT, // DONE
        RABBIT_FALLING_1_ONTO_RISE_RIGHT, // DONE
        RABBIT_FALLING_1_ONTO_LOWER_LEFT, // DONE
        RABBIT_FALLING_1_ONTO_RISE_LEFT, // DONE
        RABBIT_RISING_RIGHT_START,
        RABBIT_RISING_RIGHT_CONTINUE,
        RABBIT_RISING_RIGHT_END,
        RABBIT_RISING_LEFT_START,
        RABBIT_RISING_LEFT_CONTINUE,
        RABBIT_RISING_LEFT_END,
        RABBIT_LOWERING_RIGHT_START,
        RABBIT_LOWERING_RIGHT_CONTINUE,
        RABBIT_LOWERING_RIGHT_END,
        RABBIT_LOWERING_LEFT_START,
        RABBIT_LOWERING_LEFT_CONTINUE,
        RABBIT_LOWERING_LEFT_END,
        RABBIT_LOWERING_AND_RISING_RIGHT,
        RABBIT_LOWERING_AND_RISING_LEFT,
        RABBIT_RISING_AND_LOWERING_RIGHT,
        RABBIT_RISING_AND_LOWERING_LEFT,
        RABBIT_ENTERING_EXIT,
        RABBIT_ENTERING_EXIT_CLIMBING_RIGHT,
        RABBIT_ENTERING_EXIT_CLIMBING_LEFT,
        RABBIT_BASHING_RIGHT,
        RABBIT_BASHING_LEFT,
        RABBIT_BASHING_UP_RIGHT,
        RABBIT_BASHING_UP_LEFT,
        RABBIT_BASHING_USELESSLY_RIGHT,
        RABBIT_BASHING_USELESSLY_LEFT,
        RABBIT_BASHING_USELESSLY_RIGHT_UP,
        RABBIT_BASHING_USELESSLY_LEFT_UP,
        RABBIT_DIGGING,
        RABBIT_DIGGING_USELESSLY,
        RABBIT_DIGGING_ON_SLOPE,
        RABBIT_DIGGING_2,
        RABBIT_BRIDGING_IN_CORNER_RIGHT_1,
        RABBIT_BRIDGING_IN_CORNER_RIGHT_2,
        RABBIT_BRIDGING_IN_CORNER_RIGHT_3,
        RABBIT_BRIDGING_IN_CORNER_LEFT_1,
        RABBIT_BRIDGING_IN_CORNER_LEFT_2,
        RABBIT_BRIDGING_IN_CORNER_LEFT_3,
        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_1,
        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_2,
        RABBIT_BRIDGING_IN_CORNER_UP_RIGHT_3,
        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_1,
        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_2,
        RABBIT_BRIDGING_IN_CORNER_UP_LEFT_3,
        RABBIT_BRIDGING_RIGHT_1,
        RABBIT_BRIDGING_RIGHT_2,
        RABBIT_BRIDGING_RIGHT_3,
        RABBIT_BRIDGING_LEFT_1,
        RABBIT_BRIDGING_LEFT_2,
        RABBIT_BRIDGING_LEFT_3,
        RABBIT_BRIDGING_UP_RIGHT_1,
        RABBIT_BRIDGING_UP_RIGHT_2,
        RABBIT_BRIDGING_UP_RIGHT_3,
        RABBIT_BRIDGING_UP_LEFT_1,
        RABBIT_BRIDGING_UP_LEFT_2,
        RABBIT_BRIDGING_UP_LEFT_3,
        RABBIT_BRIDGING_DOWN_UP_RIGHT_1,
        RABBIT_BRIDGING_DOWN_UP_RIGHT_2,
        RABBIT_BRIDGING_DOWN_UP_RIGHT_3,
        RABBIT_BRIDGING_DOWN_UP_LEFT_1,
        RABBIT_BRIDGING_DOWN_UP_LEFT_2,
        RABBIT_BRIDGING_DOWN_UP_LEFT_3,
        RABBIT_CLIMBING_LEFT_START,
        RABBIT_CLIMBING_LEFT_CONTINUE_1,
        RABBIT_CLIMBING_LEFT_CONTINUE_2,
        RABBIT_CLIMBING_LEFT_END,
        RABBIT_CLIMBING_LEFT_BANG_HEAD,
        RABBIT_CLIMBING_RIGHT_START,
        RABBIT_CLIMBING_RIGHT_CONTINUE_1,
        RABBIT_CLIMBING_RIGHT_CONTINUE_2,
        RABBIT_CLIMBING_RIGHT_END,
        RABBIT_CLIMBING_RIGHT_BANG_HEAD,

        RABBIT_DROWNING,

        RABBIT_BLOCKING,
        RABBIT_BLOCKING_RISE_RIGHT,
        RABBIT_BLOCKING_RISE_LEFT,
        RABBIT_OUT_OF_BOUNDS,
        RABBIT_EXPLODING,
        RABBIT_CRASHING,
        RABBIT_WAITING_LEFT,
        RABBIT_WAITING_RIGHT,
        TOKEN_BASH_STILL,
        TOKEN_BASH_FALLING,
        TOKEN_BASH_FALL_TO_SLOPE,
        TOKEN_BASH_ON_SLOPE,

        TOKEN_DIG_STILL,
        TOKEN_DIG_FALLING,
        TOKEN_DIG_FALL_TO_SLOPE,
        TOKEN_DIG_ON_SLOPE,

        TOKEN_BRIDGE_STILL,
        TOKEN_BRIDGE_FALLING,
        TOKEN_BRIDGE_FALL_TO_SLOPE,
        TOKEN_BRIDGE_ON_SLOPE,

        TOKEN_BLOCK_STILL,
        TOKEN_BLOCK_FALLING,
        TOKEN_BLOCK_FALL_TO_SLOPE,
        TOKEN_BLOCK_ON_SLOPE,

        TOKEN_CLIMB_STILL,
        TOKEN_CLIMB_FALLING,
        TOKEN_CLIMB_FALL_TO_SLOPE,
        TOKEN_CLIMB_ON_SLOPE,

        TOKEN_EXPLODE_FALLING,
        TOKEN_EXPLODE_STILL,
        TOKEN_EXPLODE_FALL_TO_SLOPE,
        TOKEN_EXPLODE_ON_SLOPE,

        TOKEN_BROLLY_FALLING,
        TOKEN_BROLLY_STILL,
        TOKEN_BROLLY_FALL_TO_SLOPE,
        TOKEN_BROLLY_ON_SLOPE,

        WATER_REGION,
        WATER_REGION_HALF,
        WATER_REGION_EMPTY,
        WATER_REGION_FALLING,

        ENTRANCE,
        EXIT,
    }

    public static class Change
    {
        public final int x;
        public final int y;
        public final State state; // TODO : 삭제 필요
        public final rabbitescape.engine.state.State statE;

        public Change( int x, int y, State state )
        {
            this.x = x;
            this.y = y;
            this.state = state;
            this.statE = null;
        }

        public Change(
            int x,
            int y,
            rabbitescape.engine.state.State statE
        )
        {
            this.x = x;
            this.y = y;
            this.state = null;
            this.statE = statE;
        }
    }

    public final List<Change> changes = new ArrayList<>();

    public void add( int x, int y, State state )
    {
        changes.add( new Change( x, y, state ) );
    }

    public void add(int x, int y, rabbitescape.engine.state.State state) {
        changes.add( new Change( x, y, state ) );
    }
}
