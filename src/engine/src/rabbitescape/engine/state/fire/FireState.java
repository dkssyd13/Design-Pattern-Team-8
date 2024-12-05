package rabbitescape.engine.state.fire;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Fire;
import rabbitescape.engine.World;
import rabbitescape.engine.state.State;
import rabbitescape.engine.textworld.Chars;


public abstract class FireState implements State
{
    public void step( World world, Fire fire ){

    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }

    public State baseVariantSwitch( State a, State b, State c, State d, State currentState ){
        throw new RuntimeException( "Fire not in fire state:" + currentState );
    }

    public static State stateForVariant( int variant ){
        switch ( variant )
        {
            case 0:
                return new FireAState();
            case 1:
                return new FireBState();
            case 2:
                return new FireCState();
            case 3:
                return new FireDState();
        }
        throw new RuntimeException(
            "Variant outside expected range (0 - 3):" + variant );
    }
}
