class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> subArray = new HashMap<>();
        int maxLength = 0;
        int currentSum = 0;
        subArray.put(0,-1);
        for(int i=0; i<nums.length; i++)
        {
            currentSum += nums[i]==1?1:-1; 
            if(subArray.containsKey(currentSum))
                maxLength = Math.max(maxLength,i-subArray.get(currentSum));
            else
                subArray.put(currentSum, i);
        }
        return maxLength;
    }
}