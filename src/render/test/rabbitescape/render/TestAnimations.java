package rabbitescape.render;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static rabbitescape.engine.Rabbit.Type.*;
import static rabbitescape.engine.util.Util.*;

import rabbitescape.engine.Direction;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.Thing;
import rabbitescape.engine.Token;
import rabbitescape.engine.state.*;
import rabbitescape.engine.state.fire.*;
import rabbitescape.engine.state.rabbit.*;
import rabbitescape.engine.state.rabbit.bashing.*;
import rabbitescape.engine.state.rabbit.blocking.*;
import rabbitescape.engine.state.rabbit.bridging.*;
import rabbitescape.engine.state.rabbit.burning.*;
import rabbitescape.engine.state.rabbit.climbing.*;
import rabbitescape.engine.state.rabbit.digging.*;
import rabbitescape.engine.state.rabbit.drowning.RabbitDrowningState;
import rabbitescape.engine.state.rabbit.entering.*;
import rabbitescape.engine.state.rabbit.falling.*;
import rabbitescape.engine.state.rabbit.lowering.*;
import rabbitescape.engine.state.rabbit.rising.*;
import rabbitescape.engine.state.rabbit.turning.*;
import rabbitescape.engine.state.rabbit.waiting.*;
import rabbitescape.engine.state.rabbit.walking.*;
import rabbitescape.engine.state.token.*;
import rabbitescape.engine.state.water.WaterRegionEmptyState;
import rabbitescape.engine.state.water.WaterRegionFallingState;
import rabbitescape.engine.state.water.WaterRegionHalfState;
import rabbitescape.engine.state.water.WaterRegionState;


public class TestAnimations
{
    static class StateUtils {
        private static final List<State> states = Arrays.asList(
            new EntranceState(),
            new ExitState(),
            new NothingState(),
            new PipeState(),
            new FireAFallingState(),
            new FireAFallToRiseLeftState(),
            new FireAFallToRiseRightState(),
            new FireARiseLeftState(),
            new FireARiseRightState(),
            new FireAState(),
            new FireBFallingState(),
            new FireBFallToRiseLeftState(),
            new FireBFallToRiseRightState(),
            new FireBRiseLeftState(),
            new FireBRiseRightState(),
            new FireBState(),
            new FireCFallingState(),
            new FireCFallToRiseLeftState(),
            new FireCFallToRiseRightState(),
            new FireCRiseLeftState(),
            new FireCRiseRightState(),
            new FireCState(),
            new FireDFallingState(),
            new FireDFallToRiseLeftState(),
            new FireDFallToRiseRightState(),
            new FireDRiseLeftState(),
            new FireDRiseRightState(),
            new FireDState(),
            new FireExtinguishingState(),
            new RabbitBrollychutingState(),
            new RabbitCrashingState(),
            new RabbitExplodingState(),
            new RabbitOutOfBoundsState(),
            new RabbitBashingLeftState(),
            new RabbitBashingRightState(),
            new RabbitBashingUpLeftState(),
            new RabbitBashingUpRightState(),
            new RabbitBashingUselesslyLeftState(),
            new RabbitBashingUselesslyLeftUpState(),
            new RabbitBashingUselesslyRightState(),
            new RabbitBashingUselesslyRightUpState(),
            new RabbitBlockingRiseLeftState(),
            new RabbitBlockingRiseRightState(),
            new RabbitBlockingState(),
            new RabbitBridgingDownUpLeft1State(),
            new RabbitBridgingDownUpLeft2State(),
            new RabbitBridgingDownUpLeft3State(),
            new RabbitBridgingDownUpRight1State(),
            new RabbitBridgingDownUpRight2State(),
            new RabbitBridgingDownUpRight3State(),
            new RabbitBridgingInCornerLeft1State(),
            new RabbitBridgingInCornerLeft2State(),
            new RabbitBridgingInCornerLeft3State(),
            new RabbitBridgingInCornerRight1State(),
            new RabbitBridgingInCornerRight2State(),
            new RabbitBridgingInCornerRight3State(),
            new RabbitBridgingInCornerUpLeft1State(),
            new RabbitBridgingInCornerUpLeft2State(),
            new RabbitBridgingInCornerUpLeft3State(),
            new RabbitBridgingInCornerUpRight1State(),
            new RabbitBridgingInCornerUpRight2State(),
            new RabbitBridgingInCornerUpRight3State(),
            new RabbitBridgingLeft1State(),
            new RabbitBridgingLeft2State(),
            new RabbitBridgingLeft3State(),
            new RabbitBridgingRight1State(),
            new RabbitBridgingRight2State(),
            new RabbitBridgingRight3State(),
            new RabbitBridgingUpLeft1State(),
            new RabbitBridgingUpLeft2State(),
            new RabbitBridgingUpLeft3State(),
            new RabbitBridgingUpRight1State(),
            new RabbitBridgingUpRight2State(),
            new RabbitBridgingUpRight3State(),
            new RabbitBurningOnSlopeState(),
            new RabbitBurningState(),
            new RabbitClimbingLeftBangHeadState(),
            new RabbitClimbingLeftContinue1State(),
            new RabbitClimbingLeftContinue2State(),
            new RabbitClimbingLeftEndState(),
            new RabbitClimbingLeftStartState(),
            new RabbitClimbingRightBangHeadState(),
            new RabbitClimbingRightContinue1State(),
            new RabbitClimbingRightContinue2State(),
            new RabbitClimbingRightEndState(),
            new RabbitClimbingRightStartState(),
            new RabbitDigging2State(),
            new RabbitDiggingOnSlopeState(),
            new RabbitDiggingState(),
            new RabbitDiggingUselesslyState(),
            new RabbitDrowningState(),
            new RabbitEnteringExitClimbingLeftState(),
            new RabbitEnteringExitClimbingRightState(),
            new RabbitEnteringExitState(),
            new RabbitDyingOfFalling2SlopeRiseLeft2State(),
            new RabbitDyingOfFalling2SlopeRiseLeftState(),
            new RabbitDyingOfFalling2SlopeRiseRight2State(),
            new RabbitDyingOfFalling2SlopeRiseRightState(),
            new RabbitDyingOfFalling2State(),
            new RabbitDyingOfFallingSlopeRiseLeft2State(),
            new RabbitDyingOfFallingSlopeRiseLeftState(),
            new RabbitDyingOfFallingSlopeRiseRight2State(),
            new RabbitDyingOfFallingSlopeRiseRightState(),
            new RabbitDyingOfFallingState(),
            new RabbitFalling1OntoLowerLeftState(),
            new RabbitFalling1OntoLowerRightState(),
            new RabbitFalling1OntoRiseLeftState(),
            new RabbitFalling1OntoRiseRightState(),
            new RabbitFalling1State(),
            new RabbitFalling1ToDeathState(),
            new RabbitFallingOntoLowerLeftState(),
            new RabbitFallingOntoLowerRightState(),
            new RabbitFallingOntoRiseLeftState(),
            new RabbitFallingOntoRiseRightState(),
            new RabbitFallingState(),
            new RabbitLoweringAndRisingLeftState(),
            new RabbitLoweringAndRisingRightState(),
            new RabbitLoweringLeftContinueState(),
            new RabbitLoweringLeftEndState(),
            new RabbitLoweringLeftStartState(),
            new RabbitLoweringRightContinueState(),
            new RabbitLoweringRightEndState(),
            new RabbitLoweringRightStartState(),
            new RabbitRisingAndLoweringLeftState(),
            new RabbitRisingAndLoweringRightState(),
            new RabbitRisingLeftContinueState(),
            new RabbitRisingLeftEndState(),
            new RabbitRisingLeftStartState(),
            new RabbitRisingRightContinueState(),
            new RabbitRisingRightEndState(),
            new RabbitRisingRightStartState(),
            new RabbitTurningLeftToRightLoweringState(),
            new RabbitTurningLeftToRightRisingState(),
            new RabbitTurningLeftToRightState(),
            new RabbitTurningRightToLeftLoweringState(),
            new RabbitTurningRightToLeftRisingState(),
            new RabbitTurningRightToLeftState(),
            new RabbitWaitingLeftState(),
            new RabbitWaitingRightState(),
            new RabbitWalkingLeftState(),
            new RabbitWalkingRightState(),
            new TokenBashFallingState(),
            new TokenBashFallToSlopeState(),
            new TokenBashOnSlopeState(),
            new TokenBashStillState(),
            new TokenBlockFallingState(),
            new TokenBlockFallToSlopeState(),
            new TokenBlockOnSlopeState(),
            new TokenBlockStillState(),
            new TokenBridgeFallingState(),
            new TokenBridgeFallToSlopeState(),
            new TokenBridgeOnSlopeState(),
            new TokenBridgeStillState(),
            new TokenBrollyFallingState(),
            new TokenBrollyFallToSlopeState(),
            new TokenBrollyOnSlopeState(),
            new TokenBrollyStillState(),
            new TokenClimbFallingState(),
            new TokenClimbFallToSlopeState(),
            new TokenClimbOnSlopeState(),
            new TokenClimbStillState(),
            new TokenDigFallingState(),
            new TokenDigFallToSlopeState(),
            new TokenDigOnSlopeState(),
            new TokenDigStillState(),
            new TokenExplodeFallingState(),
            new TokenExplodeFallToSlopeState(),
            new TokenExplodeOnSlopeState(),
            new TokenExplodeStillState(),
            new WaterRegionEmptyState(),
            new WaterRegionFallingState(),
            new WaterRegionHalfState(),
            new WaterRegionState()
        );

        public static List<State> values() {
            return Collections.unmodifiableList(states);
        }
    }

    @Test
    public void States_must_have_animations_and_frames_must_have_images()
    {
        Thing token = new Token( 2, 1, Token.Type.block );
        for ( State s: StateUtils.values() )
        {
            token.state = s;

            String reaName = token.stateName();
            if (
                reaName.equals( "nothing" )
                || reaName.equals( "water_region_empty" )
            )
            { // Special case: no rea required.
                continue;
            }

            // Exception here if an animation is missing.
            Animation a = AnimationLoader.load( reaName );

            checkFramesExist( reaName, a );
        }
    }

    @Test
    public void Rabbot_states_must_have_animations_and_frames_must_have_images()
    {
        Function<State, Boolean> isRabbitState = new Function<State, Boolean>()
        {
            @Override
            public Boolean apply( State t )
            {
                return t.name().startsWith( "RABBIT_" );
            }
        };

        Rabbit rabbit = new Rabbit( 1, 2, Direction.LEFT, RABBOT );

        for ( State s: filter(isRabbitState, list(StateUtils.values())) )
        {
            rabbit.state = s;

            // Exception here if an animation is missing.
            Animation a = AnimationLoader.load( rabbit.stateName() );

            checkFramesExist( rabbit.stateName(), a );
        }
    }

    private void checkFramesExist( String reaName, Animation a )
    {

        for ( Frame f: a )
        {
            String resourcePath =
                "/rabbitescape/ui/swing/images32/" +
                f.name + ".png";
            URL url = getClass().getResource( resourcePath );
            boolean fileExists = ( url != null );
            if ( !fileExists )
            {
                System.err.println( "Working Directory:"
                                   + System.getProperty( "user.dir" ) );
                System.err.println( "Missing frame:" + reaName + ":" + resourcePath );
            }
            assertThat( fileExists, is( true ) );
        }
    }
}
