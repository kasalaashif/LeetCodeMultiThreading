class NumArray {
    int[] prefixSum;
    public NumArray(int[] nums) {
        int length = nums.length;
        prefixSum = new int[length+1];
        prefixSum[0] = 0;
        for(int i=0; i<length; i++)
            prefixSum[i+1] += prefixSum[i] + nums[i];
    }
    
    public int sumRange(int left, int right) {
        return prefixSum[right+1] - prefixSum[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */