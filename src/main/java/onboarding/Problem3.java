package onboarding;

public class Problem3 {
    public static int solution(int number) {
        int answer = 0;

        for(int i=1;i<=number;i++){
            answer+=clapTimes(i);
        }
        return answer;
    }

    public static int clapTimes(int num){
        int clapNum=0;
        while(num>=1){
            if(num%10 ==3 || num%10 == 6 || num%10 ==9){
                clapNum++;
            }
            num=num/10;
        }
        return clapNum;
    }
}
