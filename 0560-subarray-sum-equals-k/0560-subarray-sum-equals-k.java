class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> subarrays = new HashMap<>();
        subarrays.put(0,1);
        int currSum =0;
        int total = 0;
        for(int i=0; i<nums.length; i++)
        {
            currSum += nums[i];
            if(subarrays.containsKey(currSum-k))
            {
                total += subarrays.get(currSum-k);

            }
            subarrays.put(currSum, subarrays.getOrDefault(currSum,0)+1);
        }
        return total;
    }
}