package core;

import java.util.ArrayList;

public interface IBoard
{
    static final int NUMBER_OF_DICE = 16;
    static final int GRID = 4;
    
    void populateDice();
    ArrayList shakeDice();
}
