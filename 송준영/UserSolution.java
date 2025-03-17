import java.util.*;

class UserSolution {

    int N, L, playersPerLeague;
    ArrayList<ArrayList<Player>> currentLeagues;

    // Player 클래스가 Comparable 인터페이스를 구현
    static class Player implements Comparable<Player> {
        int id, ability;
        Player(int id, int ability) {
            this.id = id;
            this.ability = ability;
        }
        // 내림차순 능력, 같으면 오름차순 id 정렬
        @Override
        public int compareTo(Player other) {
            if(this.ability != other.ability) {
                return other.ability - this.ability;
            }
            return this.id - other.id;
        }
    }

    // 초기화: 선수들을 id 순서대로 각 리그에 배정하고, 리그별 정렬 수행
    void init(int N, int L, int mAbility[]) {
        this.N = N;
        this.L = L;
        this.playersPerLeague = N / L;
        currentLeagues = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            currentLeagues.add(new ArrayList<>());
        }
        // 선수 생성 후 id 순서대로 각 리그에 배정
        for (int i = 0; i < N; i++) {
            int leagueId = i / playersPerLeague;
            Player p = new Player(i, mAbility[i]);
            currentLeagues.get(leagueId).add(p);
        }
        // 각 리그 내에서 정렬 (Player의 compareTo에 따라 정렬됨)
        for (int i = 0; i < L; i++) {
            Collections.sort(currentLeagues.get(i));
        }

        for (Player p : currentLeagues.get(0)) {
            System.out.println(p.id + " " + p.ability);
        }
    }

    // 승강제 시뮬레이션: 각 리그에서 이동할 선수(승격, 강등)를 구하고, 새 리그 구성을 업데이트
    // 승격: 각 리그 1~L-1의 최고 선수 (index 0)
    // 강등: 각 리그 0~L-2의 최악 선수 (마지막 인덱스)
    // 단, 0번 리그의 최고 선수와 L-1번 리그의 최악 선수는 이동하지 않음
    // 이동한 선수들의 id 합을 반환함
    int move() {
        long promotionRelegationSum = 0;
        Player[] promotion = new Player[L]; // 각 리그의 승격 대상 (리그 1 이상)
        Player[] relegation = new Player[L];  // 각 리그의 강등 대상 (리그 0~L-2)

        // 승격 대상: 리그 1 ~ L-1의 최고 선수
        for (int i = 1; i < L; i++) {
            promotion[i] = currentLeagues.get(i).get(0);
            promotionRelegationSum += promotion[i].id;
        }
        // 강등 대상: 리그 0 ~ L-2의 최악 선수
        for (int i = 0; i < L - 1; i++) {
            ArrayList<Player> league = currentLeagues.get(i);
            relegation[i] = league.get(league.size() - 1);
            promotionRelegationSum += relegation[i].id;
        }

        // 새 리그 구성: 승강제 이동을 반영하여 각 리그별 선수 목록 업데이트
        ArrayList<ArrayList<Player>> newLeagues = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            newLeagues.add(new ArrayList<>());
        }
        // 리그 0: 기존 리그 0에서 강등 대상 제거 후, 리그 1의 승격 대상을 추가
        {
            ArrayList<Player> list = new ArrayList<>(currentLeagues.get(0));
            list.remove(relegation[0]);
            list.add(promotion[1]);
            newLeagues.set(0, list);
        }
        // 리그 1 ~ L-2번: 기존 리그에서 해당 리그의 승격, 강등 대상을 제거한 후,
        // 위 리그의 강등 대상과 아래 리그의 승격 대상을 추가
        for (int i = 1; i <= L - 2; i++) {
            ArrayList<Player> list = new ArrayList<>(currentLeagues.get(i));
            list.remove(promotion[i]);
            list.remove(relegation[i]);
            list.add(relegation[i - 1]);
            list.add(promotion[i + 1]);
            newLeagues.set(i, list);
        }
        // 리그 L-1: 기존 리그 L-1에서 승격 대상 제거 후, 리그 L-2의 강등 대상을 추가
        {
            ArrayList<Player> list = new ArrayList<>(currentLeagues.get(L - 1));
            list.remove(promotion[L - 1]);
            list.add(relegation[L - 2]);
            newLeagues.set(L - 1, list);
        }
        // 모든 리그를 재정렬 (Player의 compareTo에 따라 정렬됨)
        for (int i = 0; i < L; i++) {
            Collections.sort(newLeagues.get(i));
        }
        // 상태 업데이트: 새 리그 구성으로 교체
        currentLeagues = newLeagues;
        return (int)promotionRelegationSum;
    }

    // 트레이드 시뮬레이션:
    // 각 리그 i (1 ≤ i ≤ L-1)에서, 해당 리그의 최고 선수와 바로 위 리그의 중간급 선수(리그 내 index = playersPerLeague/2)를 교환
    // 단, 0번 리그의 최고 선수와 L-1번 리그의 중간급 선수는 교환 대상이 아님
    // 교환된 선수들의 id 합을 반환함
    int trade() {
        long tradeSum = 0;
        // 각 리그 i (1 ≤ i ≤ L-1)에서 교환 수행
        for (int i = 1; i < L; i++) {
            // 아래 리그의 최고 선수 (정렬 상태이므로 index 0)
            Player lowerBest = currentLeagues.get(i).get(0);
            // 바로 위 리그의 중간급 선수 (playersPerLeague는 홀수이므로 index = playersPerLeague/2)
            Player upperMedian = currentLeagues.get(i - 1).get(playersPerLeague / 2);
            tradeSum += lowerBest.id + upperMedian.id;
        }
        return (int)tradeSum;
    }
}
