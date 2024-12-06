package rabbitescape.engine.state.token;

import rabbitescape.engine.Token;
import rabbitescape.engine.World;
import rabbitescape.engine.state.State;

public abstract class TokenState implements State
{
    public abstract void step( World world, Token token );

    static public State switchByType(
        Token.Type type,
        boolean moving,
        boolean slopeBelow,
        boolean onSlope
    )
    {
        switch( type )
        {
            case bash:   return chooseState(
                moving,
                slopeBelow,
                onSlope,
                new TokenBashFallingState(),
                new TokenBashStillState(),
                new TokenBashFallToSlopeState(),
                new TokenBashOnSlopeState()
            );

            case dig:    return chooseState(
                moving,
                slopeBelow,
                onSlope,
                new TokenDigFallingState(),
                new TokenDigStillState(),
                new TokenDigFallToSlopeState(),
                new TokenDigOnSlopeState()
            );

            case bridge: return chooseState(
                moving,
                slopeBelow,
                onSlope,
                new TokenBridgeFallingState(),
                new TokenBridgeStillState(),
                new TokenBridgeFallToSlopeState(),
                new TokenBridgeOnSlopeState()
            );

            case block: return chooseState(
                moving,
                slopeBelow,
                onSlope,
                new TokenBlockFallingState(),
                new TokenBlockStillState(),
                new TokenBlockFallToSlopeState(),
                new TokenBlockOnSlopeState()
            );

            case climb: return chooseState(
                moving,
                slopeBelow,
                onSlope,
                new TokenClimbFallingState(),
                new TokenClimbStillState(),
                new TokenClimbFallToSlopeState(),
                new TokenClimbOnSlopeState()
            );

            case explode: return chooseState(
                moving,
                slopeBelow,
                onSlope,
                new TokenExplodeFallingState(),
                new TokenExplodeStillState(),
                new TokenExplodeFallToSlopeState(),
                new TokenExplodeOnSlopeState()
            );

            case brolly: return chooseState(
                moving,
                slopeBelow,
                onSlope,
                new TokenBrollyFallingState(),
                new TokenBrollyStillState(),
                new TokenBrollyFallToSlopeState(),
                new TokenBrollyOnSlopeState()
            );

            default: throw new Token.UnknownType( type );
        }
    }

    private static State chooseState(
        boolean moving,
        boolean slopeBelow,
        boolean onSlope,
        State falling,
        State onFlat,
        State fallingToSlope,
        State onSlopeState
    )
    {
        if ( onSlope )
        {
            return onSlopeState;
        }
        if ( !moving )
        {
            return onFlat;
        }
        if ( slopeBelow )
        {
            return fallingToSlope;
        }
        return falling;
    }
}
