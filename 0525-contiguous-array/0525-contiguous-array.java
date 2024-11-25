class Solution {
    public int findMaxLength(int[] nums) {
        int prefixSum = 0;
        int maxLength = 0;
        HashMap<Integer, Integer> prefixSumArray = new HashMap<>();
        prefixSumArray.put(0,-1);
        for(int i=0; i<nums.length; i++)
        {
            prefixSum += (nums[i] == 1)?1:-1;
            if(prefixSumArray.containsKey(prefixSum))
                maxLength = Math.max(maxLength, i - prefixSumArray.get(prefixSum));
            else    
                prefixSumArray.put(prefixSum, i);
        }
        return maxLength;
    }
}