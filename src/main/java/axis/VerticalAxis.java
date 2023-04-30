package axis;


public class VerticalAxis implements Axis{
    private int position;

    public VerticalAxis(int position){
        this.position = position;
    }

    public void AddToAxis(int axisRate) {
        position += axisRate;
        SetAxisPos(position);

    }

    public void GoToAxisPosition(int axisRate, int positionToGo) throws Exception{

        if(axisRate == 0){
            throw new Exception("Please enter a value greater than zero");
        }

            while(GetAxisPos() != positionToGo){
                if(GetAxisPos() > positionToGo){
                    position -= axisRate;
                }
                else {
                    position =+ axisRate;
                }
                this.wait(1000);

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

    public void move (int axis_rate, boolean isAdd){

        if(isAdd){

            position += axis_rate;
        }
        else{

            position -= axis_rate;
        }
    }

    public void move (int amountToMove){

        position =+ amountToMove;
        SetAxisPos(position);

    }
}


