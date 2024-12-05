package rabbitescape.engine.state.fire;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Fire;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class FireBFallingState extends FireState{
    @Override
    public void step( World world, Fire fire )
    {
        ++fire.y;

        if ( fire.y >= world.size.height )
        {
            world.changes.removeFire( fire );
        }
        return;
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set(  change.x, change.y + 1, 'g' );
    }

    @Override
    public String name()
    {
        return "FIRE_B_FALLING";
    }
}
