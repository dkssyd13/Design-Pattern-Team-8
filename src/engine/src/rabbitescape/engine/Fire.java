package rabbitescape.engine;


import rabbitescape.engine.state.State;
import rabbitescape.engine.state.fire.*;

import java.util.HashMap;
import java.util.Map;


public class Fire extends Thing
{
    public int variant;

    private final State baseVariant;

    public Fire( int x, int y, int variant )
    {
        super( x, y, stateForVariant( variant ) );
        this.variant = variant;
        baseVariant = state;
    }

    private static State stateForVariant( int variant )
    {
        return FireStateCommon.stateForVariant( variant );
    }

    @Override
    public void calcNewState( World world )
    {
        FireStateCommon.calcNewState( world, this );
    }

    @Override
    public void step( World world )
    {
        if (state instanceof FireStateCommon )
        {
            (( FireStateCommon ) state).step(  world, this );
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

    @Override
    public String overlayText()
    {
        return "Fire";
    }

    public State getBaseVariant()
    {
        return baseVariant;
    }

}
