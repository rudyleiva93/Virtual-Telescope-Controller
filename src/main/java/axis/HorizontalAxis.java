package axis;


public class HorizontalAxis implements Axis{
    private int position;

    public HorizontalAxis(int position){
        this.position = position;
    }

    public void AddToAxis(int axisRate) {
        position += axisRate;
        SetAxisPos(position);
    }

    public void GoToAxisPosition(int axisRate, int positionToGo){
        while(GetAxisPos() != positionToGo){
            if(positionToGo < 0){

                position -= axisRate;
                SetAxisPos(position);
            }
            else if (positionToGo > 0){

                position += axisRate;
                SetAxisPos(position);
            }else{
                //If user enters zero
            }
        }
    }

    public void SubtractFromAxis(int axisRate) {
        position -= axisRate;
        SetAxisPos(position);
    }

    public int GetAxisPos() {
        return position;
    }

    public void SetAxisPos(int position) {
        this.position = position;
    }
}
