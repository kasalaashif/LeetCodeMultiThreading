class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0,-1);
        int currentSum=0;
        int maxLength=0;
        for(int i=0; i<nums.length; i++)
        {
            currentSum +=  (nums[i]==1)?1:-1;
            if(prefixSum.containsKey(currentSum))
                maxLength = Math.max(maxLength, i-prefixSum.get(currentSum));
            else
                prefixSum.put(currentSum, i);
        }
        return maxLength;
    }
}