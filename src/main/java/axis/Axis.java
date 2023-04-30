package axis;


public interface Axis {
    public void AddToAxis(int axisRate);
    public void SubtractFromAxis(int axisRate);
    public void GoToAxisPosition(int axisRate, int positionToGo) throws Exception;
    public int GetAxisPos();
    public void SetAxisPos(int position);
}
