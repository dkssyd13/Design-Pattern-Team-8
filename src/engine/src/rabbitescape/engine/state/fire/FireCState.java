package rabbitescape.engine.state.fire;

import rabbitescape.engine.state.State;

public class FireCState extends FireStateCommon
{
    @Override
    public State baseVariantSwitch(
        State a,
        State b,
        State c,
        State d,
        State currentState
    )
    {
        return c;
    }

    @Override
    public String name()
    {
        return "FIRE_C";
    }
}
