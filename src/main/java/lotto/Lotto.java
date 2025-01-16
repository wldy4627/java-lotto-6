package lotto;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }

        Set<Integer> set  = new HashSet<>();
        for (Integer number : numbers) {
            if (!set.add(number)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되지 않아야 합니다.");
            }
        }
    }

    // TODO: 추가 기능 구현

    @Override
    public String toString() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers.toString();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
