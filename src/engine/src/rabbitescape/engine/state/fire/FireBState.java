package rabbitescape.engine.state.fire;


import rabbitescape.engine.state.State;

public class FireBState extends FireState
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
        return b;
    }

    @Override
    public String name()
    {
        return "FIRE_B";
    }
}
