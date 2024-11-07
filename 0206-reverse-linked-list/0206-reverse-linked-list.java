/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null)
        {
            ListNode tempNext = curr.next; //null
            curr.next = prev; //4->3->2->1->null;
            prev = curr; // 5->4->3->2->1->null
            curr = tempNext; // null
        }
        return prev;
    }
}