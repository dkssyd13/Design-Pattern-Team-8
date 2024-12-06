package rabbitescape.engine.state.fire;

import rabbitescape.engine.state.State;

public class FireDState extends FireStateCommon
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
        return d;
    }

    @Override
    public String name()
    {
        return "FIRE_D";
    }
}
