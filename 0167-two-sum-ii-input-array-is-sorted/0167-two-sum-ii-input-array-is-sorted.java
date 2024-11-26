class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] answer = new int[2];
        int left=0;
        int right=numbers.length -1;
        for(int i=0; i<numbers.length; i++)
        {
            if(numbers[left] + numbers[right] == target)
            {
                answer[0] = left+1;
                answer[1] = right+1;
                return answer;
            }
            else if(numbers[left] + numbers[right] > target)
                right--;
                else
                    left++;
        }
        return answer;
    }
}