/**
 * Created by JakobErlandsson on 2018-12-04.
 */
public class Guard {

    int id;
    int[] minuteMostAsleep;
    int[] nAsleep;
    int minutesAsleep;

    Guard(int id){
        this.id = id;
        nAsleep = new int[60];
        minuteMostAsleep = new int[2];
        minutesAsleep = 0;
        for(int i = 0; i < nAsleep.length; i++){
            nAsleep[i] = 0;
        }
    }
}
