package onboarding;

import java.util.*;

public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = List.of();

        answer = find_duplicated_email_address(forms);
        Collections.sort(answer);
        return answer;
    }

    public static List<String> find_duplicated_email_address(List<List<String>> forms) {

        Map<String, HashSet<String>> matched_address_hash = new HashMap<>();
        Set<String> duplicated_email_address = new HashSet<>();

        for (List<String> form : forms) {

            String nickName = form.get(1);
            String email = form.get(0);

            for (int i = 0; i < nickName.length() - 1; i++) {

                String sub_str = nickName.substring(i, i + 2);

                HashSet<String> matched_email_address = matched_address_hash.getOrDefault(sub_str, new HashSet<>());
                matched_email_address.add(email);

                if (matched_email_address.size() == 2) {
                    duplicated_email_address.addAll(matched_email_address);
                } else if (matched_email_address.size() > 2) {
                    duplicated_email_address.add(email);
                }

                matched_address_hash.put(sub_str, matched_email_address);
            }
        }

        return new ArrayList<>(duplicated_email_address);
    }

}
