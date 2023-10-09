package onboarding;

import java.util.List;

class Problem1 {
    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;

        int pobiMaxValue = findMaxValue(pobi);
        int crongMaxValue = findMaxValue(crong);
        if(pobiMaxValue == -1 || crongMaxValue==-1)
            return -1;

        if(pobiMaxValue==crongMaxValue)
            return 0;
        return Integer.compare(pobiMaxValue,crongMaxValue);
    }

    public static int findMaxValue(List<Integer> person){

        int left=person.get(0);
        int right=person.get(1);

        if(left%2==0 || right!=left+1){//왼쪽이 짝수인 인 경우, 오른쪽이 왼쪽 +1이 아닌 경우, 예외처리.
            return -1;
        }

        int leftLargest = findLargest(left);
        int rightLargest = findLargest(right);

        return leftLargest > rightLargest ?  leftLargest: rightLargest;
    }

    public static int findLargest(int num){//각 자리수의 합과 곱 중 큰 것을 선택하는 함수.
        int sum=0;
        int mul=1;
        while(num>=1){
            sum+=num%10;
            mul*=num%10;
            num=num/10;
        }

        return sum > mul ? sum: mul;
    }
}