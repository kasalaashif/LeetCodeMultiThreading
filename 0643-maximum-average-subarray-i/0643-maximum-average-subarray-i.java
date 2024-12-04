class Solution {
    public double findMaxAverage(int[] nums, int k) {

        int windowSum = 0;
        for(int i=0; i<k; i++)
            windowSum += nums[i];
        int maxWindow=windowSum;
        for(int i =0; i<nums.length-k; i++)
        {
            windowSum += nums[k+i] - nums[i];
            maxWindow = Math.max(maxWindow, windowSum);
        }

        return (double) maxWindow/k;
    }

}