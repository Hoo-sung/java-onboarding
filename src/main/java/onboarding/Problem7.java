package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = new ArrayList<>();
        List<Map.Entry<String, Integer>> scores = find_answer(user, friends, visitors);
        Collections.sort(scores, (entry1, entry2) -> {
            int scoreCompare =  entry2.getValue().compareTo(entry1.getValue());
            if(scoreCompare == 0){
                //점수가 같을 때는 이름 오름차순 정렬.
                return entry1.getKey().compareTo(entry2.getKey());
            }
            return scoreCompare;
        });

        for(Map.Entry<String, Integer> entry : scores){

            if(entry.getValue()!=0){
                //만약에 answer크기가 5개 이상이면 추가하지 않고 종료
                if(answer.size() >=5){
                    break;
                }
                answer.add(entry.getKey());
            }
            else if(entry.getValue() == 0 ){
                break;
            }
        }
        return answer;
    }

    public static List<Map.Entry<String,Integer>> find_answer(String user, List<List<String>> friends, List<String> visitors){
        Map<String, HashSet<String>> relations = new HashMap<>();
        Map<String, Integer> scores = new HashMap<>();


        //이제 타임라인에 방문한 홧수만큼 1 증가.
        for(String visitor: visitors){
            scores.put(visitor,scores.getOrDefault(visitor,0)+1);
        }

        //친구 관계 정리.
        for(List<String> friend: friends){

            String person1= friend.get(0);
            String person2= friend.get(1);

            relations.putIfAbsent(person1, new HashSet<>());
            relations.putIfAbsent(person2, new HashSet<>());

            relations.get(person1).add(person2);
            relations.get(person2).add(person1);
        }

        //주인공의 관계 출력.
        HashSet<String> mainPerson = relations.get(user);

        //user와 함께 아는 친구의 수만큼 10*n으로 점수 매겨야 함.
        for(String key : relations.keySet()){

            if(key.equals(user))
                continue;

            HashSet<String> sub = relations.get(key);// 부 주인공.

            Set<String> intersection = new HashSet<>(mainPerson);
            intersection.retainAll(sub);

            Integer score =0;//초기 점수는 0

            if(!intersection.isEmpty()){//갯수만큼 *10증가 시킨다.

             score = scores.getOrDefault(key,0);
             score+=intersection.size()*10;
             scores.put(key,score);
            }

        }
        return new ArrayList<>(scores.entrySet());
    }
}
