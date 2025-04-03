package axis;


public abstract class Axis {

    int position;

    public Axis(int position){
        this.position = position;
    }

    public void AddToAxis(int axisRate){
        position += axisRate;
        SetAxisPos(position);
    }
    public void SubtractFromAxis(int axisRate){
        position -= axisRate;
        SetAxisPos(position);
    }
    public void GoToAxisPosition(int axisRate, int positionToGo){
        while(position != positionToGo){

            if(positionToGo < 0){
                position -= axisRate;
                SetAxisPos(position);
            } else if (positionToGo > 0) {
                position += axisRate;
                SetAxisPos(axisRate);
            } else {
                //User enter 0
            }
        }
    }
    public int GetAxisPos(){
        return position;
    }
    public void SetAxisPos(int position){
        this.position = position;
    }
}
