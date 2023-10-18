package onboarding;

import java.util.*;

public class Problem6 {

    public static Map<String, List<String>> duplicated_maps = new HashMap<>();

    public static Set<String> duplicated_email_addresses = new HashSet<>();
    
    public static List<String> solution(List<List<String>> forms) {

        List<String> answer = Collections.emptyList();
        make_Duplicated_Maps(forms);
        make_duplicated_email_lists();
        answer=sortedDuplicatedEmails();
        return answer;
    }

    private static ArrayList<String> sortedDuplicatedEmails() {
        ArrayList<String> sortedEmailAddresses = new ArrayList<>(duplicated_email_addresses);
        Collections.sort(sortedEmailAddresses,(s1, s2) -> s1.compareTo(s2));//오름차순 정렬.
        return sortedEmailAddresses;
    }

    private static void make_duplicated_email_lists() {
        for (Map.Entry<String, List<String>> entry : duplicated_maps.entrySet()) {
            if(entry.getValue().size()>2){
                List<String> emails = entry.getValue();
                duplicated_email_addresses.addAll(emails);
            }
        }
    }

    private static void make_Duplicated_Maps(List<List<String>> forms) {
        for(List<String> form: forms){
            //닉네임을 처음부터 끝까지 2개씩 끊어서 key(2글자) = value(email)로 저장하기.
            String email = form.get(0);
            String nickName = form.get(1);
            find_duplicated_email_addresses(email, nickName);
        }
    }

    private static void find_duplicated_email_addresses(String email, String nickName) {
        for(int i = 0; i< nickName.length()-1; i++){
            String key = nickName.substring(i, i + 2);//2개연달아 있는지?

            if (!duplicated_maps.containsKey(key)) {
                duplicated_maps.put(key, new ArrayList<>());
            }
            duplicated_maps.get(key).add(email);
        }
    }

    public static void main(String[] args) {
        List<List<String>> forms = Arrays.asList(
                Arrays.asList("jm@email.com", "제이엠"),
                Arrays.asList("jason@email.com", "제이슨"),
                Arrays.asList("woniee@email.com", "워니"),
                Arrays.asList("mj@email.com", "엠제이"),
                Arrays.asList("nowm@email.com", "이제엠")
        );

        List<String> result = solution(forms);
        System.out.println(result);
    }
}
