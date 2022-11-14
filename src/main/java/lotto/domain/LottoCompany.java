package lotto.domain;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 로또 당첨 번호 발표
 */
public class LottoCompany {
    private Lotto winningLotto;
    private int bonusNumber;

    public void draw() {
        System.out.println("당첨 번호를 입력해 주세요.");
        drawWinningNumbers();
        System.out.println("보너스 번호를 입력해 주세요.");
        drawBonusNumber();
    }

    public Lotto getWinningNumbers() {
        return winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void drawWinningNumbers() {
        List<String> winningNumbers = Arrays.asList(readLine().split(","));
        validateNumericWinningNumbers(winningNumbers);
        this.winningLotto = new Lotto(winningNumbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private void drawBonusNumber() {
        String number = readLine();
        validateNumericBonusNumber(number);
        int bonusNumber = Integer.parseInt(number);
        validateRange(bonusNumber);
        validateDuplicate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateNumericWinningNumbers(List<String> numbers) {
        for (String number : numbers) {
            if (!isNumeric(number)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 숫자여야 합니다.");
            }
        }
    }

    private void validateNumericBonusNumber(String number) {
        if (!isNumeric(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }

    private void validateRange(int number) {
        if (number < Constants.MINIMUM_LOTTO_NUMBER || number > Constants.MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1과 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(int number) {
        if (this.winningLotto.getNumbers().contains(number)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 로또 번호와 다른 번호를 입력해야 합니다.");
        }
    }

    private boolean isNumeric(String number) {
        return number.matches("^[0-9]+$");
    }
}
