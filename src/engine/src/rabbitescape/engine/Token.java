package rabbitescape.engine;


import java.util.HashMap;
import java.util.Map;

import rabbitescape.engine.err.RabbitEscapeException;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.token.*;

public class Token extends Thing
{
    public static class UnknownType extends RabbitEscapeException
    {
        public final Type type;

        public UnknownType( Type type )
        {
            this.type = type;
        }

        private static final long serialVersionUID = 1L;
    }

    public static enum Type
    {
        bash,
        dig,
        bridge,
        block,
        climb,
        explode,
        brolly
    }

    public final Type type;

    public Token( int x, int y, Type type )
    {
        super( x, y, switchType( type, false, false, true ) );
        this.type = type;
    }

    public Token( int x, int y, Type type, World world )
    {
        this( x, y, type );
        boolean onSlope = BehaviourTools.isSlope( world.getBlockAt( x, y ) );
        // Can't use calcNewState here since we have just been created, so
        // can't be moving (until a time step passes).
        state = switchType( type, false, false, onSlope );
    }

    private static State switchType(
        Type type, 
        boolean moving,
        boolean slopeBelow, 
        boolean onSlope 
    )
    {
        return TokenState.switchByType(
            type,
            moving,
            slopeBelow,
            onSlope
        );
    }

    @Override
    public void calcNewState( World world )
    {
        Block onBlock = world.getBlockAt( x, y );
        Block belowBlock = world.getBlockAt( x, y + 1 );
        boolean still = (
               BehaviourTools.s_isFlat( belowBlock )
            || ( onBlock != null )
            || BridgeTools.someoneIsBridgingAt( world, x, y )
        );

        state = switchType( type, !still,
            BehaviourTools.isSlope( belowBlock ),
            BehaviourTools.isSlope( onBlock ) );
    }

    @Override
    public void step( World world )
    {
        if ( state instanceof TokenState )
        {
            ( ( TokenState )state ).step( world, this );
        }
    }

    @Override
    public Map<String, String> saveState( boolean runtimeMeta )
    {
        return new HashMap<String, String>();
    }

    @Override
    public void restoreFromState( Map<String, String> state )
    {
    }

    public static String name( Type ability )
    {
        String n = ability.name();
        return n.substring( 0, 1 ).toUpperCase() + n.substring( 1 );
    }

    @Override
    public String overlayText()
    {
        return type.toString();
    }


}
