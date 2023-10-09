package onboarding;

public class Problem4 {
    public static String solution(String word) {
        String answer = "";

        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(c>='a'&& c<='z')
                answer+=revert(c,'a');
            else if(c>='A'&& c <='Z')
                answer+=revert(c,'A');
            else
                answer+=c;
        }
        return answer;
    }

    public static char revert(char change, char k){
        char answer=(char) ((k+k+25)-change);
        return answer;
    }
}
