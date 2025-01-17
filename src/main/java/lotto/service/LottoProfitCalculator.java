package lotto.service;

import java.util.Map;

public class LottoProfitCalculator {

    public Double calculateProfit(int purchaseAmount, Map<Integer, Integer> lottoResult) {
        int totalProfit = calculateTotalProfit(lottoResult);

        return ((double) totalProfit / purchaseAmount) * 100;
    }

    private int calculateTotalProfit(Map<Integer, Integer> lottoResult) {
        int totalProfit = 0;

        for (Map.Entry<Integer, Integer> entry : lottoResult.entrySet()) {
            totalProfit += entry.getKey() * entry.getValue();
        }

        return totalProfit;
    }
}
