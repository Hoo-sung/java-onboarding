package onboarding;

public class Problem2 {

    public static String solution(String cryptogram) {
        String answer=cryptogram;

       for(int i=0;i<cryptogram.length();i++) {
           String s = removeOneCycle(answer);
           answer = s;
       }
        return answer;
    }

    public static String removeOneCycle(String object){

        String answer="";
        for(int i=0;i<object.length();i++){
            if(i==object.length()-1) {//마지막 까지 간 경우
                answer += object.charAt(i);
                break;
            }
            if(object.charAt(i)!=object.charAt(i+1)){
                answer+=object.charAt(i);
            }
            else
                i++;
        }
        return answer;
    }

}
