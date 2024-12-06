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
        change.state.charForChange( change, chars );
    }
}
