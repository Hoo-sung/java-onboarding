package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = new ArrayList<>();

        return find_answer(user,friends,visitors);
    }

    public static List<String> find_answer(String user, List<List<String>> friends, List<String> visitors){
        Map<String, List<String>> relations = new HashMap<>();
        Map<String, Integer> scores = new HashMap<>();


        //이제 타임라인에 방문한 홧수만큼 1 증가.
        for(String visitor: visitors){
            scores.put(visitor,scores.getOrDefault(visitor,0)+1);
        }

        //친구 관계 정리.
        for(List<String> friend: friends){

            String person1= friend.get(0);
            String person2= friend.get(1);

            relations.putIfAbsent(person1,new ArrayList<>());
            relations.putIfAbsent(person2, new ArrayList<>());

            relations.get(person1).add(person2);
            relations.get(person2).add(person1);
        }

        //주인공의 관계 출력.

        for(String friend: relations.get(user)){

            int score= scores.getOrDefault(friend,0);
            score +=(relations.get(friend).size()-1)*10;
            scores.put(friend,score);
        }

        // 점수를 내림차순으로 정렬하고, 점수가 같을 경우 이름으로 정렬
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        Collections.sort(sortedScores, (a, b) -> {
            int scoreComparison = Integer.compare(b.getValue(), a.getValue());
            if (scoreComparison == 0) {
                return a.getKey().compareTo(b.getKey());
            }
            return scoreComparison;
        });

        // 상위 5명의 친구 추천
        List<String> recommendedFriends = new ArrayList<>();
        for (int i = 0; i < 5 && i < sortedScores.size(); i++) {
            recommendedFriends.add(sortedScores.get(i).getKey());
        }

        return recommendedFriends;
    }

    public static void main(String[] args) {
        String user = "mrko";
        List<List<String>> friends = Arrays.asList(
                Arrays.asList("donut", "andole"),
                Arrays.asList("donut", "jun"),
                Arrays.asList("donut", "mrko"),
                Arrays.asList("shakevan", "andole"),
                Arrays.asList("shakevan", "jun"),
                Arrays.asList("shakevan", "mrko")
        );
        List<String> visitors = Arrays.asList("bedi", "bedi", "donut", "bedi", "shakevan");

        List<String> result = Problem7.solution(user, friends, visitors);

        // 예상 결과: ["andole", "jun", "bedi"]
        System.out.println(result);
    }
}
