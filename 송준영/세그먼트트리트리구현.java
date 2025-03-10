public class 세그먼트트리트리구현 {
    long tree[];
    int treeSize;

    public 세그먼트트리트리구현(int arrSize) {
        /*
         * (자바의 Math.log는 자연로그이므로, 밑을 2로 나누는 효과를 위해 log(2)로 나누기)
            Math.ceil을 쓴 이유는 원소 배열의 길이가 2의 제곱꼴이 아닐때 +1 효과를 내기 위해 올림처리를 함.(h = [logN] + 1)
         */
        int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
        this.treeSize = 1 << (h + 1);

        tree = new long[treeSize];
    }
    
    public long init(long[] arr, int node, int start, int end) {

        // 배열의 시작과 끝이 같다면 leaf 노드이므로
        // 원소 배열 값 그대로 담기
        if (start == end) {
            return tree[node] = arr[start];
        }

        // leaf 노드가 아니면, 자식노드 합 담기
        return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
    }
    
    // node: 현재노드 idx, start: 배열의 시작, end:배열의 끝
    // idx: 변경된 데이터의 idx, diff: 원래 데이터 값과 변경 데이터값의 차이
    public void update(int node, int start, int end, int idx, long diff) {
        // 만약 변경할 index 값이 범위 바깥이면 확인 불필요
        if (idx < start || end < idx)
            return;

        // 차를 저장
        tree[node] += diff;

        // 리프노드가 아니면 아래 자식들도 확인
        if (start != end) {
            update(node * 2, start, (start + end) / 2, idx, diff);
            update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
        }
    }
    
    // node: 현재 노드, start : 배열의 시작, end : 배열의 끝
    // left: 원하는 누적합의 시작, right: 원하는 누적합의 끝
    public long sum(int node, int start, int end, int left, int right){
        // 범위를 벗어나게 되는 경우 더할 필요 없음
        if(left > end || right < start){
            return 0;
        }

        // 범위 내 완전히 포함 시에는 더 내려가지 않고 바로 리턴
        if(left <= start && end <= right){
            return tree[node];
        }

        // 그 외의 경우 좌 / 우측으로 지속 탐색 수행
        return sum(node*2, start, (start+end)/2, left, right)+
                sum(node*2+1, (start+end)/2+1, end, left, right);
    }
}
