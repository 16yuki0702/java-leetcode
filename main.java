// 1. Two Sum
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.containsKey(target - nums[i])) {
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);
        }
        return new int[]{};
    }
}

// 2. Add Two Numbers
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
// recursive approach
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addList(l1, l2, 0);
    }
    private ListNode addList(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            if (carry > 0) {
                return new ListNode(carry);
            } else {
                return null;
            }
        }
        int val = carry + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
        ListNode n = new ListNode(val % 10);
        n.next = addList(l1 == null ? null : l1.next, l2 == null ? null : l2.next, val / 10);
        return n;
    }
}
// iterative approach
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        return dummy.next;
    }
}
*/

// 3. Longest Substring Without Repeating Characters
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> m = new HashSet<>();
        int res = 0, idx = 0, marker = 0;
        for (int i = 0; i < s.length(); i++) {
            while (m.contains(s.charAt(i))) {
                m.remove(s.charAt(marker));
                marker++;
                idx--;
            }
            idx++;
            m.add(s.charAt(i));
            res = res < idx ? idx : res;
        }
        return res;
    }
}
// hashmap solution
/*
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int start = -1, max = 0;
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (m.containsKey(s.charAt(i)) && start < m.get(s.charAt(i))) {
                start = m.get(s.charAt(i));
            }
            max = Math.max(max, i - start);
            m.put(s.charAt(i), i);
        }
        return max;
    }
};
*/

// 4. Median of Two Sorted Arrays
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                merged.add(nums1[i]);
                i++;
            } else {
                merged.add(nums2[j]);
                j++;
            }
        }
        while (i < nums1.length) {
            merged.add(nums1[i]);
            i++;
        }
        while (j < nums2.length) {
            merged.add(nums2[j]);
            j++;
        }
        int mid = merged.size() / 2;
        if ((merged.size() % 2) == 0) {
            return (float)(merged.get(mid - 1) + merged.get(mid)) / 2;
        } else {
            return (float)merged.get(mid);
        }
    }
}
/*
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    list.add(nums1[i++]);
                } else {
                    list.add(nums2[j++]);
                }
            } else {
                if (i < nums1.length) {
                    list.add(nums1[i++]);
                } else {
                    list.add(nums2[j++]);
                }
            }
        }
        int listSize = list.size();
        int target = listSize / 2;
        if (listSize % 2 == 0) {
            return (list.get(target) + list.get(target - 1)) / 2.0;
        } else {
            return (double)list.get(target);
        }
    }
}
*/

// 5. Longest Palindromic Substring
class Solution {
    private String findMax(String s, String max, int i, int j) {
        String sub = "";
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            sub = s.substring(i, j + 1);
            i--;
            j++;
        }
        return sub.length() > max.length() ? sub : max;
    }
    public String longestPalindrome(String s) {
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            max = findMax(s, max, i, i);
            max = findMax(s, max, i, i + 1);
        }
        return max;
    }
}
/*
class Solution {
    public String longestPalindrome(String s) {
        int i = 0, resStart = 0, resEnd = 0;
        while (i < s.length()) {
            int left = i;
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                i++;
            }
            int right = i++;
            while (0 <= left && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (resEnd - resStart < right - left) {
                resEnd = right;
                resStart = left + 1;
            }
        }
        return s.substring(resStart, resEnd);
    }
}
*/

// 6. ZigZag Conversion
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        int d = 1, row = 0;
        for (char c : s.toCharArray()) {
            list.get(row).append(c);
            row += d;
            if (row == numRows - 1) {
                d = -1;
            } else if (row == 0) {
                d = 1;
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : list) {
            res.append(sb.toString());
        }
        return res.toString();
    }
}

// 7. Reverse Integer
class Solution {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
            if (Math.abs(res) > Integer.MAX_VALUE) {
                return 0;
            }
        }
        return (int)res;
    }
}

// 8. String to Integer (atoi)
class Solution {
    public int myAtoi(String s) {
        Boolean start = false;
        int sign = 1;
        long res = 0;
        for (char c: s.toCharArray()) {
            if ('0' <= c && c <= '9') {
                start = true;
                res = res * 10 + (c - '0');
                if (res > Integer.MAX_VALUE) {
                    break;
                }
            } else if (!start && c == ' ') {
                continue;
            } else if (!start && c == '+') {
                start = true;
            } else if (!start && c == '-') {
                sign = -1;
                start = true;
            } else {
                break;
            }
        }
        res *= sign;
        if (res < Integer.MIN_VALUE) {
            res = Integer.MIN_VALUE;
        } else if (res > Integer.MAX_VALUE) {
            res = Integer.MAX_VALUE;
        }
        return (int)res;
    }
}

// 9. Palindrome Number
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reverted = 0;
        while (x > reverted) {
            reverted = reverted * 10 + x % 10;
            x /= 10;
        }
        return x == reverted || x == reverted / 10;
    }
}

// 10. Regular Expression Matching
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        Boolean firstMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2, p.length()))
                || (firstMatch && isMatch(s.substring(1, s.length()), p));
        }
        return firstMatch && isMatch(s.substring(1, s.length()), p.substring(1, p.length()));
    }
}

// 11. Container With Most Water
class Solution {
    public int maxArea(int[] height) {
        int max = 0, left = 0, right = height.length - 1;
        while (left < right) {
            int x = right - left, y = 0;
            if (height[left] < height[right]) {
                y = height[left];
                left++;
            } else {
                y = height[right];
                right--;
            }
            max = x * y > max ? x * y : max;
        }
        return max;
    }
}

// 12. Integer to Roman
class Solution {
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        int[] m = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] n = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < 13; i++) {
            int j = num / m[i];
            num %= m[i];
            while (j > 0) {
                res.append(n[i]);
                j--;
            }
        }
        return res.toString();
    }
}

// 13. Roman to Integer
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> table = new HashMap<>();
        table.put('I', 1);
        table.put('V', 5);
        table.put('X', 10);
        table.put('L', 50);
        table.put('C', 100);
        table.put('D', 500);
        table.put('M', 1000);
        int res = table.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i--) {
            res = table.get(s.charAt(i)) < table.get(s.charAt(i + 1))
                ? res - table.get(s.charAt(i)) : res + table.get(s.charAt(i));
        }
        return res;
    }
}

// 14. Longest Common Prefix
class Solution {
    private String cmp(String s1, String s2) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
            buf.append(s1.charAt(i));
        }
        return buf.toString();
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        String p = strs[0];
        for (String s: strs) {
            p = cmp(p, s);
        }
        return p;
    }
}
/*
class Solution {
    public String longestCommonPrefix(String[] strs) {
        String res = strs[0];
        StringBuilder curr = new StringBuilder();
        for (int i = 1; i < strs.length; i++) {
            if (res.isEmpty()) {
                return "";
            }
            String tmp = strs[i];
            int j = 0, k = 0;
            while (j < res.length() && k < tmp.length() && res.charAt(j) == tmp.charAt(k)) {
                curr.append(res.charAt(j));
                j++;
                k++;
            }
            res = curr.toString();
            curr.setLength(0);
        }
        return res;
    }
}
*/

// 15. 3Sum
class Solution {
    void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
    void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = left;
        swap(nums, (int)(left + right) / 2, right);
        for (int i = left; i < right; i++) {
            if (nums[i] < nums[right]) {
                swap(nums, i, p);
                p++;
            }
        }
        swap(nums, p, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                    right--;
                    continue;
                }
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[left]);
                    l.add(nums[right]);
                    res.add(l);
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
/*
class Solution {
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    private void qSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2, p = l;
        swap(nums, m, r);
        for (int i = l; i < r; i++) {
            if (nums[i] < nums[r]) {
                swap(nums, i, p++);
            }
        }
        swap(nums, p, r);
        qSort(nums, l, p - 1);
        qSort(nums, p + 1, r);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        qSort(nums, 0, nums.length - 1);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length; i++) {
            if (0 < i && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (0 < sum) {
                    r--;
                } else {
                    res.add(
                        new ArrayList<>(
                            Arrays.asList(nums[i], nums[l], nums[r])
                        )
                    );
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return res;
    }
}
*/

// 16. 3Sum Closest
class Solution {
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    private void qSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2, p = l;
        swap(nums, m, r);
        for (int i = l; i < r; i++) {
            if (nums[i] < nums[r]) {
                swap(nums, p++, i);
            }
        }
        swap(nums, p, r);
        qSort(nums, l, p - 1);
        qSort(nums, p + 1, r);
    }
    public int threeSumClosest(int[] nums, int target) {
        qSort(nums, 0, nums.length - 1);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    l++;
                } else if (sum > target){
                    r--;
                }
                res = Math.abs(target - sum) < Math.abs(target - res) ? sum : res;
            }
        }
        return res;
    }
}

// 17. Letter Combinations of a Phone Number
class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> m = new HashMap<>();
        m.put('2', "abc");
        m.put('3', "def");
        m.put('4', "ghi");
        m.put('5', "jkl");
        m.put('6', "mno");
        m.put('7', "pqrs");
        m.put('8', "tuv");
        m.put('9', "wxyz");
        List<String> res = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            String curr = m.get(c);
            List<String> tmp = new ArrayList<>();
            for (char cc : curr.toCharArray()) {
                if (res.isEmpty()) {
                    tmp.add(Character.toString(cc));
                } else {
                    for (String s : res) {
                        tmp.add(s + Character.toString(cc));
                    }
                }
            }
            res = tmp;
        }
        return res;
    }
}

// 18. 4Sum
class Solution {
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
    private void qSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2, p = l;
        swap(nums, m, r);
        for (int i = l; i < r; i++) {
            if (nums[i] < nums[r]) {
                swap(nums, i, p++);
            }
        }
        swap(nums, p, r);
        qSort(nums, l, p - 1);
        qSort(nums, p + 1, r);
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        qSort(nums, 0, nums.length - 1);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (0 < i && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (i + 1 < j && nums[j] == nums[j - 1]) {
                    continue;
                }
                int l = j + 1, r = nums.length - 1;
                while (l < r) {
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[l] + (long)nums[r];
                    if (sum < target) {
                        l++;
                    } else if (target < sum) {
                        r--;
                    } else {
                        res.add(
                            new ArrayList<>(
                                Arrays.asList(nums[i], nums[j], nums[l], nums[r])
                            )
                        );
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    }
                }
            }
        }
        return res;
    }
}

// 19. Remove Nth Node From End of List
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, dummy = new ListNode(-1, head);
        ListNode slow = dummy;
        while (n-- != 0) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}

// 20. Valid Parentheses
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (top == '(' && c == ')') {
                    continue;
                } else if (top == '{' && c == '}') {
                    continue;
                } else if (top == '[' && c == ']') {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

// 21. Merge Two Sorted Lists
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                curr.next = list2;
                break;
            }
            if (list2 == null) {
                curr.next = list1;
                break;
            }
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}

// 22. Generate Parentheses
class Solution {
    private void backTrack(List<String> res, String curr, int open, int close, int max) {
        if (curr.length() == max * 2) {
            res.add(curr);
            return;
        }
        if (open < max) {
            backTrack(res, curr + "(", open + 1, close, max);
        }
        if (close < open) {
            backTrack(res, curr + ")", open, close + 1, max);
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backTrack(res, "", 0, 0, n);
        return res;
    }
}

// 23. Merge k Sorted Lists
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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (ListNode l : lists) {
            while (l != null) {
                q.add(l.val);
                l = l.next;
            }
        }
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (!q.isEmpty()) {
            curr.next = new ListNode(q.poll());
            curr = curr.next;
        }
        return dummy.next;
    }
}

// 24. Swap Nodes in Pairs
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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode curr = dummy;
        while (head != null && head.next != null) {
            ListNode next = head.next;
            ListNode nnext = next.next;

            curr.next = next;
            next.next = head;
            head.next = nnext;

            curr = head;
            head = nnext;
        }
        return dummy.next;
    }
}

// 25. Reverse Nodes in k-Group
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
    private ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode next;
        ListNode prev = begin;
        ListNode first = curr;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode begin = dummy;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
}
/*
class Solution {
    private int listLength(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        int len = listLength(head);
        for (int i = 0; i < len / k; i++) {
            for (int j = 1; j < k; j++) {
                ListNode tmp = prev.next;
                prev.next = head.next;
                head.next = head.next.next;
                prev.next.next = tmp;
            }
            prev = head;
            head = head.next;
        }
        return dummy.next;
    }
}
*/

// 26. Remove Duplicates from Sorted Array
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}

// 27. Remove Element
class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }
}

// 28. Find the Index of the First Occurrence in a String
class Solution {
    public int strStr(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = i, k = 0;
            while (k < needle.length() && haystack.charAt(j) == needle.charAt(k)) {
                j++;
                k++;
            }
            if (k == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}

// 29. Divide Two Integers
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isNegative = dividend < 0 ^ divisor < 0;

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int quotient = 0, subQuot = 0;

        while (dividend - divisor >= 0) {
            for (subQuot = 0; dividend - (divisor << subQuot << 1) >= 0; subQuot++);
            quotient += 1 << subQuot;
            dividend -= divisor << subQuot;
        }
        return isNegative ? -quotient : quotient;
    }
}

// 30. Substring with Concatenation of All Words
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (words[0].length() * words.length > s.length()) {
            return new ArrayList<>();
        }
        Map<String, Integer> wfreq = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (String str : words) {
            wfreq.put(str, wfreq.getOrDefault(str, 0) + 1);
        }
        int wlen = words[0].length();
        String[] strs = new String[s.length()];

        for (int i = 0; i < wlen; i++) {
            Map<String, Integer> frq = new HashMap<>();
            int begin = i, size = 0;
            for (int j = i; j <= s.length() - wlen; j += wlen) {
                strs[j] = s.substring(j, j + wlen);
                if (wfreq.containsKey(strs[j])) {
                    begin = begin == -1 ? j : begin;
                    frq.put(strs[j], frq.getOrDefault(strs[j], 0) + 1);
                    size++;
                    if (size == words.length) {
                        if (frq.equals(wfreq)) {
                            res.add(begin);
                        }
                        frq.put(strs[begin], frq.get(strs[begin]) - 1);
                        begin += wlen;
                        size--;
                    }
                } else {
                    begin = -1;
                    size = 0;
                    frq.clear();
                }
            }
        }
        return res;
    }
}

// 31. Next Permutation
class Solution {
    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    public void nextPermutation(int[] nums) {
        int i1 = -1, i2 = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                i1 = i;
                break;
            }
        }
        if (i1 == -1) {
            reverse(nums, 0);
        } else {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] > nums[i1]) {
                    i2 = i;
                    break;
                }
            }
            swap(nums, i1, i2);
            reverse(nums, i1 + 1);
        }
    }
}

// 32. Longest Valid Parentheses
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}

// 33. Search in Rotated Sorted Array
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            if (nums[l] == target) {
                return l;
            } else if (nums[r] == target) {
                return r;
            }
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[l] <= nums[m]) {
                if (nums[l] < target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                if (nums[m] < target && target < nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }
}

// 34. Find First and Last Position of Element in Sorted Array
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (target < nums[m]) {
                r = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                if (nums[l] < target) {
                    l++;
                }
                if (target < nums[r]) {
                    r--;
                }
                if (nums[l] == nums[r]) {
                    return new int[]{l, r};
                }
            }
        }
        return new int[]{-1, -1};
    }
}

// 35. Search Insert Position
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (target <= nums[m]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}

// 36. Valid Sudoku
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean rows[][] = new boolean[9][9];
        boolean cols[][] = new boolean[9][9];
        boolean blks[][] = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int c = board[i][j] - '1';
                int p = (i / 3 * 3) + (j / 3);
                if (rows[i][c] || cols[j][c] || blks[p][c]) {
                    return false;
                }
                rows[i][c] = true;
                cols[j][c] = true;
                blks[p][c] = true;
            }
        }
        return true;
    }
}

// 37. Sudoku Solver
class Solution {
    private static final char cs[] = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private boolean inCell(char[][] board, int row, int col, char target) {
        int r = row / 3, c = col / 3;
        for (int i = 0; i < 9; i++) {
            if (board[3 * r + i / 3][3 * c + i % 3] == target) {
                return true;
            }
        }
        return false;
    }
    private boolean inCol(char[][] board, int row, int col, char target) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == target) {
                return true;
            }
        }
        return false;
    }
    private boolean inRow(char[][] board, int row, int col, char target) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == target) {
                return true;
            }
        }
        return false;
    }
    private boolean solve(char[][] board, int row, int col) {
        if (9 == row) {
            return true;
        }
        int nRow = 0, nCol = 0;
        if (9 == col + 1) {
            nRow = row + 1;
            nCol = 0;
        } else {
            nRow = row;
            nCol = col + 1;
        }
        if (board[row][col] != '.') {
            return solve(board, nRow, nCol);
        }
        for (int i = 0; i < 9; i++) {
            char c = cs[i];
            if (inRow(board, row, col, c) || inCol(board, row, col, c) || inCell(board, row, col, c)) {
                continue;
            }
            board[row][col] = c;
            if (solve(board, nRow, nCol)) {
                return true;
            }
        }
        board[row][col] = '.';
        return false;
    }
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }
}

// 38. Count and Say
class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        } else if (n == 2) {
            return "11";
        }
        StringBuilder res = new StringBuilder("11");
        for (int i = 3; i <= n; i++) {
            StringBuilder tmp = new StringBuilder();
            res.append(' ');
            int c = 1;
            for (int j = 1; j < res.length(); j++) {
                if (res.charAt(j) == res.charAt(j - 1)) {
                    c++;
                } else {
                    tmp.append(Integer.toString(c));
                    tmp.append(res.charAt(j - 1));
                    c = 1;
                }
            }
            res = tmp;
        }
        return res.toString();
    }
}

// 39. Combination Sum
class Solution {
    private void backTrack(List<List<Integer>> res, LinkedList<Integer> curr, int[] candidates, int target, int sum, int start) {
        if (target < sum) {
            return;
        }
        if (target == sum) {
            res.add(new LinkedList<>(curr));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            sum += candidates[i];
            curr.push(candidates[i]);
            backTrack(res, curr, candidates, target, sum, i);
            curr.pop();
            sum -= candidates[i];
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        backTrack(res, new LinkedList<>(), candidates, target, 0, 0);
        return res;
    }
}

// 40. Combination Sum II
class Solution {
    private void swap(int[]v, int p1, int p2) {
        int tmp = v[p1];
        v[p1] = v[p2];
        v[p2] = tmp;
    }
    private void qSort(int[] v, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2, p = l;
        swap(v, m, r);
        for (int i = l; i < r; i++) {
            if (v[i] < v[r]) {
                swap(v, p++, i);
            }
        }
        swap(v, p, r);
        qSort(v, l, p - 1);
        qSort(v, p + 1, r);
    }
    private void backTrack(List<List<Integer>> res, LinkedList<Integer> curr, int[] nums, int target, int sum, int start) {
        if (sum == target) {
            res.add(new LinkedList<>(curr));
            return;
        } else if (target < sum) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (start < i && nums[i] == nums[i - 1]) {
                continue;
            }
            sum += nums[i];
            curr.push(nums[i]);
            backTrack(res, curr, nums, target, sum, i + 1);
            curr.pop();
            sum -= nums[i];
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        qSort(candidates, 0, candidates.length - 1);
        backTrack(res, new LinkedList<>(), candidates, target, 0, 0);
        return res;
    }
}

// 41. First Missing Positive
class Solution {
    private void swap(int[] nums, int p1, int p2) {
        int tmp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = tmp;
    }
    private void qSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2, p = l;
        swap(nums, m, r);
        for (int i = l; i < r; i++) {
            if (nums[i] < nums[r]) {
                swap(nums, p++, i);
            }
        }
        swap(nums, p, r);
        qSort(nums, l, p - 1);
        qSort(nums, p + 1, r);
    }
    public int firstMissingPositive(int[] nums) {
        qSort(nums, 0, nums.length -1);
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == res) {
                res++;
            }
        }
        return res;
    }
}

// 42. Trapping Rain Water
class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int maxl = height[l], maxr = height[r], res = 0;
        while (l <= r) {
            if (maxl < maxr) {
                if (height[l] < maxl) {
                    res += maxl - height[l];
                } else {
                    maxl = height[l];
                }
                l++;
            } else {
                if (height[r] < maxr) {
                    res += maxr - height[r];
                } else {
                    maxr = height[r];
                }
                r--;
            }
        }
        return res;
    }
}

// 43. Multiply Strings
class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mul + pos[i + j + 1];
                pos[i + j] += sum / 10;
                pos[i + j + 1] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

// 44. Wildcard Matching
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        int i = 0, j = 0, ast = -1, match = 0;
        while (i < m) {
            if (j < n && p.charAt(j) == '*') {
                match = i;
                ast = j++;
            } else if (j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (ast >= 0) {
                i = ++match;
                j = ast + 1;
            } else {
                return false;
            }
        }
        while (j < n && p.charAt(j) == '*') {
            j++;
        }
        return j == n;
    }
}

// 45. Jump Game II
class Solution {
    public int jump(int[] nums) {
        int step = 0, curr = 0, dest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            dest = Math.max(dest, i + nums[i]);
            if (curr == i) {
                step++;
                curr = dest;
            }
        }
        return step;
    }
}

// 46. Permutations
class Solution {
    private void backTrack(List<List<Integer>> res, List<Integer> curr, int[] nums) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (curr.contains(nums[i])) {
                    continue;
                }
                curr.add(nums[i]);
                backTrack(res, curr, nums);
                curr.remove(curr.size() - 1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res, new ArrayList<>(), nums);
        return res;
    }
}

// 47. Permutations II
class Solution {
    private void backTrack(List<List<Integer>> res, Set<List<Integer>> set, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> curr = toList(nums);
            if (!set.contains(curr)) {
                res.add(curr);
                set.add(curr);
            }
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                backTrack(res, set, nums, start + 1);
                swap(nums, start, i);
            }
        }
    }
    private List<Integer> toList(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int n : nums) {
            list.add(n);
        }
        return list;
    }
    private void swap(int[] nums, int p1, int p2) {
        int tmp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = tmp;
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        backTrack(res, set, nums, 0);
        return res;
    }
}

// 48. Rotate Image
class Solution {
    private void swap(int[][] m, int x1, int y1, int x2, int y2) {
        int tmp = m[x1][y1];
        m[x1][y1] = m[x2][y2];
        m[x2][y2] = tmp;
    }
    public void rotate(int[][] matrix) {
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = x + 1; y < matrix.length; y++) {
                swap(matrix, x, y, y, x);
            }
        }
        int l = 0, r = matrix[0].length - 1;
        while (l < r) {
            for (int i = 0; i < matrix.length; i++) {
                swap(matrix, i, l, i, r);
            }
            l++;
            r--;
        }
    }
}

// 49. Group Anagrams
class Solution {
    private String hash(String s) {
        int[] b = new int[26];
        for (char c : s.toCharArray()) {
            b[c - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            res.append(Integer.valueOf(b[i]).toString());
            res.append("/");
        }
        return res.toString();
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String s : strs) {
            String h = hash(s);
            if (!m.containsKey(h)) {
                m.put(h, new ArrayList<>());
            }
            m.get(h).add(s);
        }
        return new ArrayList<>(m.values());
    }
}

// 50. Pow(x, n)
class Solution {
    private double pow(double x, double res, long n) {
        if (n == 0) {
            return res;
        }
        if ((n & 1) == 1) {
            res *= x;
        }
        return pow(x * x, res, n >> 1);
    }
    public double myPow(double x, int n) {
        long nn = n;
        if (nn == 0) {
            return 1.0;
        }
        if (nn < 0) {
            x = 1 / x;
            nn = -nn;
        }
        return pow(x, 1.0, nn);
    }
}

// 51. N-Queens
class Solution {
    private boolean isDiagonal(char[][] board, int row, int col, int n) {
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return true;
            }
        }
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return true;
            }
        }
        return false;
    }
    private void backTrack(List<List<String>> res, char[][] board, boolean[] cols, int row, int n) {
        if (row == n) {
            List<String> tmp = new ArrayList<>();
            for (char[] cs : board) {
                tmp.add(new String(cs));
            }
            res.add(tmp);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (cols[col] || isDiagonal(board, row, col, n)) {
                continue;
            }
            cols[col] = true;
            board[row][col] = 'Q';
            backTrack(res, board, cols, row + 1, n);
            cols[col] = false;
            board[row][col] = '.';
        }
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        boolean[] cols = new boolean[n];
        backTrack(res, board, cols, 0, n);
        return res;
    }
}

// 52. N-Queens II
class Solution {
    private int res = 0;
    private boolean isDiagonal(char[][] board, int row, int col, int n) {
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return true;
            }
        }
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return true;
            }
        }
        return false;
    }
    private void backTrack(char[][] board, boolean[] cols, int row, int n) {
        if (row == n) {
            this.res++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (cols[col] || isDiagonal(board, row, col, n)) {
                continue;
            }
            cols[col] = true;
            board[row][col] = 'Q';
            backTrack(board, cols, row + 1, n);
            board[row][col] = '.';
            cols[col] = false;
        }
    }
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        boolean[] cols = new boolean[n];
        backTrack(board, cols, 0, n);
        return this.res;
    }
}

// 53. Maximum Subarray
class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE, curr = 0;
        for (int n : nums) {
            curr += n;
            if (curr < n) {
                curr = n;
            }
            res = Math.max(res, curr);
        }
        return res;
    }
}

// 54. Spiral Matrix
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        List<Integer> res = new ArrayList<>();
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (right < left || bottom < top) {
                break;
            }
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }
}

// 55. Jump Game
class Solution {
    public boolean canJump(int[] nums) {
        int curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curr < i) {
                return false;
            }
            curr = Math.max(curr, i + nums[i]);
        }
        return nums.length - 1 <= curr;
    }
}

// 56. Merge Intervals
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] tmp = intervals[i];
            if (curr[1] < tmp[0]) {
                res.add(curr);
                curr = tmp;
            } else if (tmp[1] < curr[0]) {
                res.add(tmp);
            } else {
                curr[0] = Math.min(curr[0], tmp[0]);
                curr[1] = Math.max(curr[1], tmp[1]);
            }
        }
        res.add(curr);
        return res.toArray(new int[0][]);
    }
}

// 57. Insert Interval
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] tmp = intervals[i];
            if (newInterval[1] < tmp[0]) {
                res.add(newInterval);
                newInterval = tmp;
            } else if (tmp[1] < newInterval[0]) {
                res.add(tmp);
            } else {
                newInterval[0] = Math.min(newInterval[0], tmp[0]);
                newInterval[1] = Math.max(newInterval[1], tmp[1]);
            }
        }
        res.add(newInterval);
        return res.toArray(new int[0][]);
    }
}

// 58. Length of Last Word
class Solution {
    public int lengthOfLastWord(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                res++;
            } else if (0 < res) {
                return res;
            }
        }
        return res;
    }
}

// 59. Spiral Matrix II
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int count = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                res[top][i] = count++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res[i][right] = count++;
            }
            right--;
            if (bottom < top) {
                break;
            }
            for (int i = right; i >= left; i--) {
                res[bottom][i] = count++;
            }
            bottom--;
            if (right < left) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                res[i][left] = count++;
            }
            left++;
        }
        return res;
    }
}

// 60. Permutation Sequence
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        List<Integer> num = new ArrayList<>();
        int fact = 1;
        for (int i = 1; i < n; i++) {
            fact *= i;
            num.add(i);
        }
        num.add(n);
        k = k - 1;
        while (true) {
            res.append(Integer.toString(num.get(k / fact)));
            num.remove(k / fact);
            if (num.size() == 0) {
                break;
            }
            k %= fact;
            fact /= num.size();
        }
        return res.toString();
    }
}

// 61. Rotate List
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int count = 0;
        ListNode tmp = head;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        int p = k % count;
        if (p == 0) {
            return head;
        }
        ListNode fast = head, slow = head;
        for (int i = 0; i < p; i++) {
            fast = fast.next;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode res = slow.next;
        slow.next = null;
        fast.next = head;
        return res;
    }
}

// 62. Unique Paths
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

// 63. Unique Paths II
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

// 64. Minimum Path Sum
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

// 65. Valid Number
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean digitSeen = false, dotSeen = false, eSeen = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                digitSeen = true;
            } else if (c == '+' || c == '-') {
                if (0 < i && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (eSeen || !digitSeen) {
                    return false;
                }
                eSeen = true;
                digitSeen = false;
            } else {
                return false;
            }
        }
        return digitSeen;
    }
}

// 66. Plus One
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}

// 67. Add Binary
class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        StringBuilder res = new StringBuilder();
        while (0 <= i || 0 <= j || carry == 1) {
            if (0 <= i) {
                carry += a.charAt(i--) - '0';
            }
            if (0 <= j) {
                carry += b.charAt(j--) - '0';
            }
            res.append(carry % 2);
            carry /= 2;
        }
        return res.reverse().toString();
    }
}

// 68. Text Justification
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (maxWidth == 0) {
            return res;
        }
        int i = 0, j = 0;
        while (j != words.length) {
            int len = -1;
            while (j < words.length && len + words[j].length() + 1 <= maxWidth) {
                len += words[j++].length() + 1;
            }
            int space = maxWidth - len + j - i - 1;
            int k = i;
            while (0 < space) {
                words[k++] += " ";
                space--;
                if (j != words.length && (k == j - 1 || k == j)) {
                    k = i;
                }
                if (j == words.length && k == j) {
                    k = j - 1;
                }
            }
            StringBuilder line = new StringBuilder();
            for (int l = i; l < j; l++) {
                line.append(words[l]);
            }
            res.add(line.toString());
            i = j;
        }
        return res;
    }
}

// 69. Sqrt(x)
class Solution {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int res = x / 2;
        while (true) {
            int tmp = (res + x / res) / 2;
            if (res <= tmp) {
                return res;
            }
            res = tmp;
        }
    }
}

// 70. Climbing Stairs
class Solution {
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int prev = 1, curr = 2;
        for (int i = 3; i <= n; i++) {
            int tmp = prev + curr;
            prev = curr;
            curr = tmp;
        }
        return curr;
    }
}

// 71. Simplify Path
class Solution {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        List<String> res = new ArrayList<>();
        for (String p : paths) {
            if (p.equals("..")) {
                if (!res.isEmpty()) {
                    res.remove(res.size() - 1);
                }
            } else if (!p.equals("") && !p.equals(".")) {
                res.add(p);
            }
        }
        return "/" + String.join("/", res);
    }
}

// 72. Edit Distance
class Solution {
    public int minDistance(String word1, String word2) {
        final int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}

// 73. Set Matrix Zeroes
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean isFirstRow = false, isFirstCol = false;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        isFirstRow = true;
                    }
                    if (j == 0) {
                        isFirstCol = true;
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (isFirstRow) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (isFirstCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

// 74. Search a 2D Matrix
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midv = matrix[mid / n][mid % n];
            if (midv == target) {
                return true;
            } else if (midv < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}

// 75. Sort Colors
class Solution {
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1, i = 0;
        while (i <= r) {
            if (nums[i] == 0) {
                swap(nums, l++, i++);
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, r--);
            }
        }
    }
}

// 76. Minimum Window Substring
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : t.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        int unique = m.size(), start = 0, end = 0;
        int resStart = 0, resLen = Integer.MAX_VALUE;
        while (end < s.length()) {
            char c = s.charAt(end);
            m.put(c, m.getOrDefault(c, 0) - 1);
            if (m.get(c) == 0) {
                unique--;
            }
            while (unique == 0) {
                if (end - start + 1 < resLen) {
                    resLen = end - start + 1;
                    resStart = start;
                }
                char startChar = s.charAt(start);
                m.put(startChar, m.get(startChar) + 1);
                if (m.get(startChar) > 0) {
                    unique++;
                }
                start++;
            }
            end++;
        }
        return resLen == Integer.MAX_VALUE ? "" : s.substring(resStart, resStart + resLen);
    }
}

// 77. Combinations
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        backTrack(res, curr, 1, n, k);
        return res;
    }
    private void backTrack(List<List<Integer>> res, List<Integer> curr, int idx, int n, int k) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = idx; i <= n; i++) {
            curr.add(i);
            backTrack(res, curr, i + 1, n, k);
            curr.remove(curr.size() - 1);
        }
    }
}

// 78. Subsets
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        backTrack(res, curr, nums, 0);
        return res;
    }
    private void backTrack(List<List<Integer>> res, List<Integer> curr, int[] nums, int idx) {
        res.add(new ArrayList<>(curr));
        for (int i = idx; i < nums.length; i++) {
            curr.add(nums[i]);
            backTrack(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}

// 79. Word Search
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (search(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean search(char[][] board, String word, int i, int j, int k) {
        if (word.length() <= k) {
            return true;
        }
        if (i < 0 || board.length <= i || j < 0 || board[0].length <= j) {
            return false;
        }
        if (board[i][j] == '-' || board[i][j] != word.charAt(k)) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '-';
        boolean res = search(board, word, i + 1, j, k + 1) ||
            search(board, word, i - 1, j, k + 1) ||
            search(board, word, i, j + 1, k + 1) ||
            search(board, word, i, j - 1, k + 1);
        board[i][j] = c;
        return res;
    }
}

// 80. Remove Duplicates from Sorted Array II
class Solution {
    public int removeDuplicates(int[] nums) {
        int p = 2;
        for (int i = p; i < nums.length; i++) {
            if (nums[p - 2] != nums[i]) {
                nums[p++] = nums[i];
            }
        }
        return p;
    }
}

// 81. Search in Rotated Sorted Array II
class Solution {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return true;
            }
            if (nums[l] == nums[m] && nums[m] == nums[r]) {
                l++;
                r--;
                continue;
            }
            if (nums[l] <= nums[m]) {
                if (nums[l] <= target && target <= nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return false;
    }
}

// 82. Remove Duplicates from Sorted List II
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
    private ListNode deleteDup(ListNode head, boolean isDup) {
        if (head == null) {
            return head;
        }
        while (head.next != null && head.val == head.next.val) {
            isDup = true;
            head.next = head.next.next;
        }
        head.next = deleteDup(head.next, false);
        return isDup ? head.next : head;
    }
    public ListNode deleteDuplicates(ListNode head) {
        return deleteDup(head, false);
    }
}

// 83. Remove Duplicates from Sorted List
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = head;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy;
    }
}

// 84. Largest Rectangle in Histogram
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, res = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i <= n; i++) {
            int currHeight = i == n ? 0 : heights[i];
            while (!st.empty() && currHeight < heights[st.peek()]) {
                int top = st.pop();
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                int area = heights[top] * width;
                res = Math.max(res, area);
            }
            st.push(i);
        }
        return res;
    }
}

// 85. Maximal Rectangle
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length, res = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != '1') {
                    continue;
                }
                dp[i][j] = (j == 0) ? 1 : dp[i][j - 1] + 1;
                int width = dp[i][j];
                for (int k = i; k >= 0; k--) {
                    width = Math.min(width, dp[k][j]);
                    res = Math.max(res, width * (i - k + 1));
                }
            }
        }
        return res;
    }
}

// 86. Partition List
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
    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(), greater = new ListNode();
        ListNode i = less, j = greater;
        while (head != null) {
            if (head.val < x) {
                i.next = head;
                i = i.next;
            } else {
                j.next = head;
                j = j.next;
            }
            head = head.next;
        }
        j.next = null;
        i.next = greater.next;
        return less.next;
    }
}

// 87. Scramble String
class Solution {
    private final Map<String, Boolean> m = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2) || s2.length() == 0) {
            return true;
        }
        String key = s1 + "" + s2;
        if (m.containsKey(key)) {
            return m.get(key);
        }
        boolean flag = false;
        for (int i = 1, n = s1.length(); i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                isScramble(s1.substring(i), s2.substring(0, n - i)) ||
                isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i)) ) {
                    flag = true;
                    break;
                }
        }
        m.put(key, flag);
        return flag;
    }
}

// 88. Merge Sorted Array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (0 <= i && 0 <= j) {
            if (nums1[i] <= nums2[j]) {
                nums1[k] = nums2[j--];
            } else {
                nums1[k] = nums1[i--];
            }
            k--;
        }
        while (0 <= j) {
            nums1[k--] = nums2[j--];
        }
    }
}

// 89. Gray Code
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0, target = 1 << n; i < target; i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }
}

// 90. Subsets II
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backTrack(new ArrayList<Integer>(), 0, nums);
        return res;
    }
    private void backTrack(List<Integer> curr, int start, int[] nums) {
        this.res.add(new ArrayList<>(curr));
        if (nums.length <= start) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            curr.add(nums[i]);
            backTrack(curr, i + 1, nums);
            curr.remove(curr.size() - 1);
        }
    }
}

// 91. Decode Ways
class Solution {
    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        return decode(dp, s, 0, s.length());
    }
    private int decode(int[] dp, String s, int start, int end) {
        if (end <= start) {
            return 1;
        }
        if (s.charAt(start) == '0') {
            return 0;
        }
        if (dp[start] > 0) {
            return dp[start];
        }
        int count = 0;
        if ('1' <= s.charAt(start) && s.charAt(start) <= '9') {
            count = decode(dp, s, start + 1, end);
        }
        if (start + 1 < end &&
            (
                (s.charAt(start) == '1' && ('0' <= s.charAt(start + 1) && s.charAt(start + 1) <= '9')) ||
                (s.charAt(start) == '2' && ('0' <= s.charAt(start + 1) && s.charAt(start + 1) <= '6'))
            )
        ) {
            count += decode(dp, s, start + 2, end);
        }
        return dp[start] = count;
    }
}

// 92. Reverse Linked List II
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy, curr = null;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        curr = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode tmp = pre.next;
            pre.next = curr.next;
            curr.next = curr.next.next;
            pre.next.next = tmp;
        }
        return dummy.next;
    }
}

// 93. Restore IP Addresses
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        solve(0, 0, "", s, res);
        return res;
    }
    private void solve(int i, int blk, String ans, String s, List<String> res) {
        if (i == s.length() && blk == 4) {
            res.add(ans.substring(0, ans.length() - 1));
        }
        if (i + 1 <= s.length()) {
            solve(i + 1, blk + 1, ans + s.substring(i, i + 1) + ".", s, res);
        }
        if (i + 2 <= s.length() && isValid(s.substring(i, i + 2))) {
            solve(i + 2, blk + 1, ans + s.substring(i, i + 2) + ".", s, res);
        }
        if (i + 3 <= s.length() && isValid(s.substring(i, i + 3))) {
            solve(i + 3, blk + 1, ans + s.substring(i, i + 3) + ".", s, res);
        }
    }
    private boolean isValid(String s) {
        if (s.charAt(0) == '0') {
            return false;
        }
        int val = Integer.parseInt(s);
        return val <= 255;
    }
}

// 94. Binary Tree Inorder Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inOrder(root);
        return res;
    }
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        res.add(root.val);
        inOrder(root.right);
    }
}

// 95. Unique Binary Search Trees II
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }
    private List<TreeNode> generate(int start, int end) {
        if (end < start) {
            List<TreeNode> tmp = new ArrayList<>();
            tmp.add(null);
            return tmp;
        }
        List<TreeNode> res = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generate(start, i - 1);
            List<TreeNode> right = generate(i + 1, end);
            for (int j = 0; j < left.size(); j++) {
                for (int k = 0; k < right.size(); k++) {
                    res.add(new TreeNode(i, left.get(j), right.get(k)));
                }
            }
        }
        return res;
    }
}

// 96. Unique Binary Search Trees
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}

// 97. Interleaving String
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][];
        for (int i = 0; i <= s1.length(); i++) {
            dp[i] = new boolean[s2.length() + 1];
        }
        dp[0][0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if ((i < s1.length()) && (s3.charAt(i + j) == s1.charAt(i))) {
                    dp[i + 1][j] = dp[i][j] || dp[i + 1][j];
                }
                if ((j < s2.length()) && (s3.charAt(i + j) == s2.charAt(j))) {
                    dp[i][j + 1] = dp[i][j] || dp[i][j + 1];
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

// 98. Validate Binary Search Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }
    private boolean isValid(TreeNode r, TreeNode min, TreeNode max) {
        if (r == null) {
            return true;
        }
        if (min != null && r.val <= min.val) {
            return false;
        }
        if (max != null && max.val <= r.val) {
            return false;
        }
        return isValid(r.left, min, r) && isValid(r.right, r, max);
    }
}

// 99. Recover Binary Search Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode prev = null, first = null, next = null;
    public void recoverTree(TreeNode root) {
        recover(root);
        swap(first, next);
    }
    private void swap(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return;
        }
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
    private void recover(TreeNode r) {
        if (r == null) {
            return;
        }
        recover(r.left);
        if (prev != null && r.val < prev.val) {
            if (first == null) {
                first = prev;
            }
            next = r;
        }
        prev = r;
        recover(r.right);
    }
}

// 100. Same Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

// 101. Symmetric Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isSym(root.left, root.right);
    }
    private boolean isSym(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }
        if (l == null || r == null) {
            return false;
        }
        if (l.val != r.val) {
            return false;
        }
        return isSym(l.left, r.right) && isSym(l.right, r.left);
    }
}

// 102. Binary Tree Level Order Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        level(root, 0);
        return res;
    }
    private void level(TreeNode root, int l) {
        if (root == null) {
            return;
        }
        if (res.size() == l) {
            res.add(new ArrayList<>());
        }
        res.get(l).add(root.val);
        level(root.left, l + 1);
        level(root.right, l + 1);
    }
}

// 103. Binary Tree Zigzag Level Order Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<List<Integer>> res = new ArrayList<>(), list = new ArrayList<>();
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        traversal(root, 0);
        for (int i = 0; i < list.size(); i++) {
            res.add(new ArrayList<>());
            List<Integer> tmp = list.get(i);
            if (i % 2 == 0) {
                for (int j = 0; j < tmp.size(); j++) {
                    res.get(i).add(tmp.get(j));
                }
            } else {
                for (int j = tmp.size() - 1; j >= 0; j--) {
                    res.get(i).add(tmp.get(j));
                }
            }
        }
        return res;
    }
    private void traversal(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (list.size() == level) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
        traversal(root.left, level + 1);
        traversal(root.right, level + 1);
    }
}

// 104. Maximum Depth of Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

// 105. Construct Binary Tree from Preorder and Inorder Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int idx = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length - 1);
    }
    private TreeNode build(int[] preorder, int[] inorder, int l, int r) {
        if (r < l) {
            return null;
        }
        int start = l;
        while (preorder[idx] != inorder[start]) {
            start++;
        }
        idx++;
        TreeNode t = new TreeNode(inorder[start]);
        t.left = build(preorder, inorder, l, start - 1);
        t.right = build(preorder, inorder, start + 1, r);
        return t;
    }
}

// 106. Construct Binary Tree from Inorder and Postorder Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, 0, inorder.length - 1, 0, inorder.length - 1);
    }
    private TreeNode build(int[] in, int[] post, int inStart, int inEnd, int postStart, int postEnd) {
        if (inEnd < inStart) {
            return null;
        }
        TreeNode t = new TreeNode(post[postEnd]);
        int idx = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == post[postEnd]) {
                idx = i;
                break;
            }
        }
        int leftSize = idx - inStart;
        t.left = build(in, post, inStart, idx - 1, postStart, postStart + leftSize - 1);
        t.right = build(in, post, idx + 1, inEnd, postStart + leftSize, postEnd - 1);
        return t;
    }
}

// 107. Binary Tree Level Order Traversal II
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<List<Integer>> tmp = new ArrayList<>(), res = new ArrayList<>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        traverse(root, 0);
        for (int i = tmp.size() - 1; 0 <= i; i--) {
            res.add(tmp.get(i));
        }
        return res;
    }
    private void traverse(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (tmp.size() == level) {
            tmp.add(new ArrayList<>());
        }
        tmp.get(level).add(root.val);
        traverse(root.left, level + 1);
        traverse(root.right, level + 1);
    }
}

// 108. Convert Sorted Array to Binary Search Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    private TreeNode build(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = l + (r - l) / 2;
        TreeNode t = new TreeNode(nums[m]);
        t.left = build(nums, l, m - 1);
        t.right = build(nums, m + 1, r);
        return t;
    }
}

// 109. Convert Sorted List to Binary Search Tree
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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<Integer> list = new ArrayList<>();
    public TreeNode sortedListToBST(ListNode head) {
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return build(0, list.size() - 1);
    }
    private TreeNode build(int l, int r) {
        if (r < l) {
            return null;
        }
        int m = l + (r - l) / 2;
        TreeNode t = new TreeNode(list.get(m));
        t.left = build(l, m - 1);
        t.right = build(m + 1, r);
        return t;
    }
}

// 110. Balanced Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return isBalanced;
    }
    private int dfs(TreeNode r) {
        if (r == null || !isBalanced) {
            return 0;
        }
        int left = dfs(r.left);
        int right = dfs(r.right);
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(left, right);
    }
}

// 111. Minimum Depth of Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.right != null) {
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        }
        return 1 + Math.max(minDepth(root.left), minDepth(root.right));
    }
}

// 112. Path Sum
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}

// 113. Path Sum II
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> tmp = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        traverse(root, targetSum);
        return res;
    }
    private void traverse(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        tmp.add(root.val);
        if (root.left == null && root.right == null && root.val == target) {
            res.add(new ArrayList<>(tmp));
        }
        traverse(root.left, target - root.val);
        traverse(root.right, target - root.val);
        tmp.remove(tmp.size() - 1);
    }
}

// 114. Flatten Binary Tree to Linked List
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}

// 115. Distinct Subsequences
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=  m; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }
}

// 116. Populating Next Right Pointers in Each Node
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
            connect(root.left);
            connect(root.right);
        }
        return root;
    }
}

// 117. Populating Next Right Pointers in Each Node II
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node c = root, dummy = new Node(0, null, null, null);
        for (Node p = dummy; c != null; c = c.next) {
            if (c.left != null) {
                p.next = c.left;
                p = p.next;
            }
            if (c.right != null) {
                p.next = c.right;
                p = p.next;
            }
        }
        connect(dummy.next);
        dummy.next = null;
        return root;
    }
}

// 118. Pascal's Triangle
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        List<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);
        for (int i = 1; i < numRows; i++) {
            List<Integer> pre = res.get(i - 1);
            List<Integer> curr = new ArrayList<>();
            curr.add(1);
            for (int j = 1; j < i; j++) {
                curr.add(pre.get(j - 1) + pre.get(j));
            }
            curr.add(1);
            res.add(curr);
        }
        return res;
    }
}

// 119. Pascal's Triangle II
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (i == 0 || i == 1) {
                    tmp.add(1);
                } else {
                    if (j == 0 || j == i) {
                        tmp.add(1);
                    } else {
                        tmp.add(res.get(j - 1) + res.get(j));
                    }
                }
            }
            res = tmp;
        }
        return res;
    }
}

// 120. Triangle
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) +
                    Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}

// 121. Best Time to Buy and Sell Stock
class Solution {
    public int maxProfit(int[] prices) {
        int minP = Integer.MAX_VALUE, res = Integer.MIN_VALUE;
        for (int p : prices) {
            minP = Math.min(minP, p);
            res = Math.max(res, p - minP);
        }
        return res;
    }
}

// 122. Best Time to Buy and Sell Stock II
class Solution {
    public int maxProfit(int[] prices) {
        int buy = 0, sell = 0, res = 0, n = prices.length;
        while (buy < n && sell < n) {
            while (buy + 1 < n && prices[buy] > prices[buy + 1]) {
                buy++;
            }
            sell = buy;
            while (sell + 1 < n && prices[sell] < prices[sell + 1]) {
                sell++;
            }
            res += prices[sell] - prices[buy];
            buy = sell + 1;
        }
        return res;
    }
}

// 123. Best Time to Buy and Sell Stock III
class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE, sell1 = 0, sell2 = 0;
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}

// 124. Binary Tree Maximum Path Sum
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        int sum = root.val + left + right;
        res = Math.max(res, sum);
        return root.val + Math.max(left, right);
    }
}

// 125. Valid Palindrome
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !isAlpha(s.charAt(l))) {
                l++;
            }
            while (l < r && !isAlpha(s.charAt(r))) {
                r--;
            }
            if (l < r && s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    private boolean isAlpha(char c) {
        return (48 <= c && c <= 57)
            || (65 <= c && c <= 90)
            || (97 <= c && c <= 122);
    }
}

// 126. Word Ladder II
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Set<String>> reverse = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> nextLevel = new HashSet<>();
        wordSet.remove(beginWord);
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        boolean isEnd = false;
        while (!q.isEmpty()) {
            String w = q.remove();
            for (String next : wordSet) {
                if (isLadder(w, next)) {
                    Set<String> reverseLadders = reverse.computeIfAbsent(next, k -> new HashSet<>());
                    reverseLadders.add(w);
                    if (endWord.equals(next)) {
                        isEnd = true;
                    }
                    nextLevel.add(next);
                }
            }
            if (q.isEmpty()) {
                if (isEnd) {
                    break;
                }
                q.addAll(nextLevel);
                wordSet.removeAll(nextLevel);
                nextLevel.clear();
            }
        }
        if (!isEnd) {
            return res;
        }
        Set<String> path = new LinkedHashSet<>();
        path.add(endWord);
        findPath(endWord, beginWord, reverse, res, path);
        return res;
    }
    private void findPath(String endWord, String beginWord, Map<String, Set<String>> graph,
        List<List<String>> res, Set<String> path) {
        Set<String> next = graph.get(endWord);
        if (next == null) {
            return;
        }
        for (String word : next) {
            path.add(word);
            if (beginWord.equals(word)) {
                List<String> shortestPath = new ArrayList<>(path);
                Collections.reverse(shortestPath);
                res.add(shortestPath);
            } else {
                findPath(word, beginWord, graph, res, path);
            }
            path.remove(word);
        }
    }
    private boolean isLadder(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int diffCount = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) {
                return false;
            }
        }
        return diffCount == 1;
    }
}

// 127. Word Ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Set<String> forwardSet = new HashSet<>(), backwardSet = new HashSet<>();
        forwardSet.add(beginWord);
        backwardSet.add(endWord);
        wordSet.remove(beginWord);
        wordSet.remove(endWord);
        return transform(forwardSet, backwardSet, wordSet);
    }
    private int transform(Set<String> forwardSet, Set<String> backwardSet, Set<String> wordSet) {
        Set<String> newSet = new HashSet<>();
        for (String fs : forwardSet) {
            char[] wordArray = fs.toCharArray();
            for (int i = 0; i < wordArray.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char old = wordArray[i];
                    wordArray[i] = c;
                    String target = String.valueOf(wordArray);
                    if (backwardSet.contains(target)) {
                        return 2;
                    } else if (wordSet.contains(target) && !forwardSet.contains(target)) {
                        wordSet.remove(target);
                        newSet.add(target);
                    }
                    wordArray[i] = old;
                }
            }
        }
        if (newSet.size() == 0) {
            return 0;
        }
        forwardSet = newSet;
        int result = forwardSet.size() > backwardSet.size() ? 
            transform(backwardSet, forwardSet, wordSet) : transform(forwardSet, backwardSet, wordSet);
            return result == 0 ? 0 : result + 1;
    }
}

// 128. Longest Consecutive Sequence
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i] - 1)) {
                continue;
            }
            int curr = nums[i], len = 0;
            while (set.contains(curr)) {
                curr++;
                len++;
            }
            res = Math.max(res, len);
        }
        return res;
    }
}

// 129. Sum Root to Leaf Numbers
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int res = 0;
    public int sumNumbers(TreeNode root) {
        recursive(root, 0);
        return res;
    }
    private void recursive(TreeNode root, int curr) {
        if (root == null) {
            return;
        }
        curr = curr * 10 + root.val;
        if (root.left == null && root.right == null) {
            res += curr;
            return;
        }
        recursive(root.left, curr);
        recursive(root.right, curr);
    }
}

// 130. Surrounded Regions
class Solution {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' &&
                    (i == 0 || j == 0 || i == m - 1 || j == n - 1)) {
                        dfs(board, i, j, m, n);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'V') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void dfs(char[][] b, int i, int j, int m, int n) {
        if (i < 0 || m <= i || j < 0 || n <= j) {
            return;
        }
        if (b[i][j] == 'O') {
            b[i][j] = 'V';
            dfs(b, i + 1, j, m, n);
            dfs(b, i - 1, j, m, n);
            dfs(b, i, j + 1, m, n);
            dfs(b, i, j - 1, m, n);
        }
    }
}

// 131. Palindrome Partitioning
class Solution {
    private List<List<String>> res = new ArrayList<>();
    private List<String> path = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backTrack(0, s);
        return res;
    }
    private void backTrack(int start, String s) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String substr = s.substring(start, end);
            if (isPalindrome(substr)) {
                path.add(substr);
                backTrack(end, s);
                path.remove(path.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

// 132. Palindrome Partitioning II
class Solution {
    public int minCut(String s) {
        int[] dp = new int[s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < s.length(); i++) {
            dp[i] = isPalindrome(s, 0, i) ? 0 : dp[i - 1] + 1;
            for (int j = i; 0 < j; j--) {
                if (dp[i] == 1) {
                    break;
                }
                dp[i] = isPalindrome(s, j, i) ? Math.min(dp[i], dp[j - 1] + 1) : dp[i];
            }
        }
        return dp[s.length() - 1];
    }
    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}

// 133. Clone Graph
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    Map<Integer, Node> m = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        if (m.containsKey(node.val)) {
            return m.get(node.val);
        }
        Node n = new Node(node.val);
        m.put(n.val, n);
        for (Node child : node.neighbors) {
            n.neighbors.add(cloneGraph(child));
        }
        return n;
    }
}

// 134. Gas Station
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0, total = 0, res = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0) {
                res = i + 1;
                sum = 0;
            }
        }
        return 0 <= total ? res : -1;
    }
}

// 135. Candy
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n], right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = 1;
            right[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (ratings[i - 1] < ratings[i]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; 0 <= i; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.max(left[i], right[i]);
        }
        return res;
    }
}

// 136. Single Number
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }
}

// 137. Single Number II
class Solution {
    public int singleNumber(int[] nums) {
        int n = nums.length, p = 0, t = 0;
        long res = 0;
        for (int i = 0; i < 32; i++) {
            t = 0;
            for (int j = 0; j < n; j++) {
                t += nums[j] & 1;
                nums[j] >>= 1;
            }
            t %= 3;
            res = res + (long)(t * Math.pow(2, p));
            p++;
        }
        return (int)res;
    }
}

// 138. Copy List with Random Pointer
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    private Map<Node, Node> m = new HashMap<>();
    public Node copyRandomList(Node head) {
        Node p = head;
        while (p != null) {
            m.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while (p != null) {
            m.get(p).next = m.get(p.next);
            m.get(p).random = m.get(p.random);
            p = p.next;
        }
        return m.get(head);
    }
}

// 139. Word Break
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i <= s.length(); i++) {
            if (!dp[i]) {
                continue;
            }
            for (String w : wordDict) {
                int len = s.length() <= i + w.length() ? s.length() : i + w.length();
                String tmp = s.substring(i, len);
                if (tmp.equals(w)) {
                    dp[i + tmp.length()] = true;
                }
            }
        }
        return dp[s.length()];
    }
}

// 140. Word Break II
class Solution {
    List<String> res = new ArrayList<>();
    Set<String> dict = new HashSet<>();
    StringBuilder curr = new StringBuilder();
    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            dict.add(word);
        }
        backTrack(s, 0);
        return res;
    }
    private void backTrack(String s, int start) {
        if (s.length() == start) {
            res.add(curr.toString().trim());
        }
        for (int i = start; i < s.length(); i++) {
            if (dict.contains(s.substring(start, i + 1))) {
                int len = curr.length();
                curr.append(s.substring(start, i + 1));
                curr.append(" ");
                backTrack(s, i + 1);
                curr.setLength(len);
            }
        }
    }
}

// 141. Linked List Cycle
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

// 142. Linked List Cycle II
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}

// 143. Reorder List
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
    public void reorderList(ListNode head) {
        ListNode curr = head;
        Stack<ListNode> stack = new Stack<>();
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        int count = stack.size() / 2;
        while (count-- != 0) {
            ListNode node = stack.pop();
            ListNode next = head.next;
            head.next = node;
            node.next = next;
            head = next;
        }
        head.next = null;
    }
}

// 144. Binary Tree Preorder Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return res;
    }
    private void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }
}

// 145. Binary Tree Postorder Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<Integer> res = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return res;
    }
    private void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        res.add(root.val);
    }
}

// 146. LRU Cache
class DoubleLinkedList {
    public int key;
    public int value;
    public DoubleLinkedList prev;
    public DoubleLinkedList next;
}
class LRUCache {
    private Map<Integer, DoubleLinkedList> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DoubleLinkedList head, tail;
    private void add(DoubleLinkedList node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }
    private void remove(DoubleLinkedList node) {
        DoubleLinkedList p = node.prev;
        DoubleLinkedList n = node.next;

        p.next = n;
        n.prev = p;
    }
    private void moveToHead(DoubleLinkedList node) {
        this.remove(node);
        this.add(node);
    }
    private DoubleLinkedList pop() {
        DoubleLinkedList res = tail.prev;
        this.remove(res);
        return res;
    }
    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        this.head = new DoubleLinkedList();
        this.head.prev = null;

        this.tail = new DoubleLinkedList();
        this.tail.next = null;

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    public int get(int key) {
        DoubleLinkedList node = this.cache.get(key);
        if (node == null) {
            return -1;
        }
        this.moveToHead(node);
        return node.value;
    }
    public void put(int key, int value) {
        DoubleLinkedList node = this.cache.get(key);
        if (node == null) {
            DoubleLinkedList newNode = new DoubleLinkedList();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.add(newNode);

            ++this.count;
            if (this.count > this.capacity) {
                DoubleLinkedList tail = this.pop();
                this.cache.remove(tail.key);
                --this.count;
            }
        } else {
            node.value = value;
            this.moveToHead(node);
        }
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// 147. Insertion Sort List
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
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, curr = head;
        while (curr != null) {
            if (curr.next != null && curr.next.val < curr.val) {
                while (pre.next != null && pre.next.val < curr.next.val) {
                    pre = pre.next;
                }
                ListNode tmp = pre.next;
                pre.next = curr.next;
                curr.next = curr.next.next;
                pre.next.next = tmp;
                pre = dummy;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}

// 148. Sort List
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            mid = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        mid.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return merget(l1, l2);
    }
    private ListNode merget(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, null);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return dummy.next;
    }
}

// 149. Max Points on a Line
class Solution {
    public int maxPoints(int[][] points) {
        int maxp = 0, coordinates = points.length;
        if (coordinates < 3) {
            return coordinates;
        }
        for (int i = coordinates - 1; 0 < i; i--) {
            int x = points[i][0];
            int y = points[i][1];
            Map<Double, Integer> m = new HashMap<>();
            for (int j = i + 1; j < coordinates; j++) {
                double dx = points[j][0] - x;
                double dy = points[j][1] - y;
                if (dx != 0) {
                    m.merge(dy / dx, 1, Integer::sum);
                } else {
                    m.merge(Double.MAX_VALUE, 1, Integer::sum);
                }
            }
            for (int k = i - 1; 0 <= k; k--) {
                double dx = x - points[k][0];
                double dy = y - points[k][1];
                if (dx != 0) {
                    m.merge(dy / dx, 1, Integer::sum);
                } else {
                    m.merge(Double.MAX_VALUE, 1, Integer::sum);
                }
            }
            maxp = Collections.max(m.values()) + 1 > maxp ?
                    Collections.max(m.values()) + 1 : maxp;
        }
        return maxp;
    }
}

// 150. Evaluate Reverse Polish Notation
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (String t : tokens) {
            if (t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
                int n2 = Integer.valueOf(st.pop());
                int n1 = Integer.valueOf(st.pop());
                if (t.equals("+")) {
                    st.push(n1 + n2);
                } else if (t.equals("-")) {
                    st.push(n1 - n2);
                } else if (t.equals("*")) {
                    st.push(n1 * n2);
                } else if (t.equals("/")) {
                    st.push(n1 / n2);
                }
            } else {
                st.push(Integer.valueOf(t));
            }
        }
        return st.pop();
    }
}

// 151. Reverse Words in a String
class Solution {
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder(), tmp = new StringBuilder();
        for (int i = s.length() - 1; 0 <= i; i--) {
            if (s.charAt(i) == ' ') {
                if (0 < tmp.length()) {
                    if (0 < res.length()) {
                        res.append(' ');
                    }
                    res.append(tmp.reverse().toString());
                    tmp.setLength(0);
                }
                continue;
            }
            tmp.append(s.charAt(i));
        }
        if (0 < tmp.length()) {
            if (0 < res.length()) {
                res.append(' ');
            }
            res.append(tmp.reverse().toString());
        }
        return res.toString();
    }
}

// 152. Maximum Product Subarray
class Solution {
    public int maxProduct(int[] nums) {
        double left = 1, right = 1;
        double res = nums[0];
        for (int l = 0, r = nums.length - 1; l < nums.length; l++, r--) {
            if (left == 0) {
                left = 1;
            }
            if (right == 0) {
                right = 1;
            }
            left *= nums[l];
            right *= nums[r];
            res = Math.max(res, Math.max(left, right));
        }
        return (int)res;
    }
}

// 153. Find Minimum in Rotated Sorted Array
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            if (nums[l] < nums[r]) {
                return nums[l];
            }
            int m = l + (r - l) / 2;
            if (nums[l] <= nums[m]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return nums[r];
    }
}

// 154. Find Minimum in Rotated Sorted Array II
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) {
                l = m + 1;
            } else if (nums[m] < nums[r]) {
                r = m;
            } else {
                r--;
            }
        }
        return nums[l];
    }
}

// 155. Min Stack
class MinStack {
    Stack<Pair<Integer, Integer>> stack;
    public MinStack() {
        this.stack = new Stack<>();
    }
    public void push(int val) {
        if (0 < stack.size()) {
            if (this.stack.peek().getValue() < val) {
                this.stack.push(new Pair(val, this.stack.peek().getValue()));
            } else {
                this.stack.push(new Pair(val, val));
            }
        } else {
            this.stack.push(new Pair(val, val));
        }
    }
    public void pop() {
        this.stack.pop();
    }
    public int top() {
        return this.stack.peek().getKey();
    }
    public int getMin() {
        return this.stack.peek().getValue();
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

// 160. Intersection of Two Linked Lists
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA, t2 = headB;
        while (t1 != t2) {
            t1 = t1 == null ? headB : t1.next;
            t2 = t2 == null ? headA : t2.next;
        }
        return t1;
    }
}

// 162. Find Peak Element
class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            int mr = m + 1;
            if (nums[m] < nums[mr]) {
                l = mr;
            } else {
                r = m;
            }
        }
        return l;
    }
}

// 164. Maximum Gap
class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int high = 0, low = Integer.MAX_VALUE, res = 0;
        for (int n : nums) {
            high = Math.max(high, n);
            low = Math.min(low, n);
        }
        int bsize = Math.max((high - low) / (nums.length - 1), 1);
        List<List<Integer>> bucket = new ArrayList<>();
        for (int i = (high - low) / bsize; 0 <= i; i--) {
            bucket.add(new ArrayList<>());
        }
        for (int n : nums) {
            bucket.get((n - low) / bsize).add(n);
        }
        int currhi = 0;
        for (List<Integer> b : bucket) {
            if (b.isEmpty()) {
                continue;
            }
            int prev = currhi > 0 ? currhi : b.get(0), currlo = b.get(0);
            for (int n : b) {
                currhi = Math.max(currhi, n);
                currlo = Math.min(currlo, n);
            }
            res = Math.max(res, currlo - prev);
        }
        return res;
    }
}

// 165. Compare Version Numbers
class Solution {
    public int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        int t1 = 0, t2 = 0;
        while (i < version1.length() || j < version2.length()) {
            while (i < version1.length() && version1.charAt(i) != '.') {
                t1 = (t1 * 10) + (version1.charAt(i++) - '0');
            }
            while (j < version2.length() && version2.charAt(j) != '.') {
                t2 = (t2 * 10) + (version2.charAt(j++) - '0');
            }
            if (t1 < t2) {
                return -1;
            } else if (t1 > t2) {
                return 1;
            }
            i++;
            j++;
            t1 = 0;
            t2 = 0;
        }
        return 0;
    }
}

// 166. Fraction to Recurring Decimal
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            res.append("-");
        }
        long num = Math.abs((long)numerator);
        long denom = Math.abs((long)denominator);
        long remainder = num % denom;
        res.append(num / denom);
        if (remainder == 0) {
            return res.toString();
        }
        res.append(".");
        HashMap<Long, Integer> m = new HashMap<>();
        while (remainder != 0) {
            if (m.containsKey(remainder)) {
                res.insert(m.get(remainder), "(");
                res.append(")");
                break;
            }
            m.put(remainder, res.length());
            remainder *= 10;
            res.append(remainder / denom);
            remainder %= denom;
        }
        return res.toString();
    }
}

// 167. Two Sum II - Input Array Is Sorted
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[]{};
    }
}

// 168. Excel Sheet Column Title
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber > 0) {
            res.append((char)(((columnNumber - 1) % 26) + 'A'));
            columnNumber = (columnNumber - 1) / 26;
        }
        return res.reverse().toString();
    }
}

// 169. Majority Element
class Solution {
    public int majorityElement(int[] nums) {
        int curr = -1, count = 0;
        for (int n : nums) {
            if (count == 0) {
                curr = n;
            }
            count += curr == n ? 1 : -1;
        }
        return curr;
    }
}

// 171. Excel Sheet Column Number
class Solution {
    public int titleToNumber(String columnTitle) {
        long res = 0, digit = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--, digit *= 26) {
            res += ((int)(columnTitle.charAt(i) - 'A') + 1) * digit;
        }
        return (int)res;
    }
}

// 172. Factorial Trailing Zeroes
class Solution {
    public int trailingZeroes(int n) {
        long res = 0, d = 5;
        while (n > 0) {
            res += n / 5;
            d *= 5;
            n /= 5;
        }
        return (int)res;
    }
}

// 173. Binary Search Tree Iterator
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BSTIterator {
    ArrayList<Integer> list = new ArrayList<>();
    int idx = 0;
    public BSTIterator(TreeNode root) {
        inOrder(root);
    }

    public int next() {
        return list.get(idx++);
    }

    public boolean hasNext() {
        return idx == list.size() ? false : true;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
}
/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

// 174. Dungeon Game
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int hp = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = (hp <= 0 ? 1 : hp);
            }
        }
        return dp[0][0];
    }
}

// 179. Largest Number
class Solution {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        if (strs[0].equals("0")) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }
}

// 187. Repeated DNA Sequences
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten)) {
                repeated.add(ten);
            }
        }
        return new ArrayList<>(repeated);
    }
}

// 188. Best Time to Buy and Sell Stock IV
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int dp[][][] = new int[n + 1][2][k + 1];
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 0) {
                        dp[idx][buy][cap] = Math.max(0 + dp[idx + 1][0][cap], -prices[idx] + dp[idx + 1][1][cap]);
                    }
                    if (buy == 1) {
                        dp[idx][buy][cap] = Math.max(0 + dp[idx + 1][1][cap], prices[idx] + dp[idx + 1][0][cap -1]);
                    }
                }
            }
        }
        return dp[0][0][k];
    }
}

// 189. Rotate Array
class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}

// 190. Reverse Bits
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= n & 1;
            n >>= 1;
        }
        return res;
    }
}

// 191. Number of 1 Bits
class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while (n > 0) {
            res++;
            n = n & (n - 1);
        }
        return res;
    }
}

// 198. House Robber
class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length <= 1) {
            return dp[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }
}

// 199. Binary Tree Right Side View
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 1);
        return res;
    }
    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (res.size() < level) {
            res.add(root.val);
        }
        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
    }
}

// 200. Number of Islands
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || grid.length <= i || j < 0 || grid[0].length <= j) {
            return;
        }
        if (grid[i][j] == '2' || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '2';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}

// 201. Bitwise AND of Numbers Range
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int count = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            count++;
        }
        return left << count;
    }
}

// 202. Happy Number
class Solution {
    public boolean isHappy(int n) {
        if (n == 1 || n == 7) {
            return true;
        } else if (n < 10) {
            return false;
        } else {
            int sum = 0;
            while (0 < n) {
                int tmp = n % 10;
                sum += tmp * tmp;
                n /= 10;
            }
            return isHappy(sum);
        }
    }
}

// 203. Remove Linked List Elements
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode fake = new ListNode(-1, head);
        ListNode curr = fake, prev = fake;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr = curr.next;
                continue;
            }
            prev = curr;
            curr = curr.next;
        }
        return fake.next;
    }
}

// 204. Count Primes
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p < n; p++) {
            if (isPrime[p]) {
                for (int mul = p * p; mul < n; mul += p) {
                    isPrime[mul] = false;
                }
            }
        }
        int count = 0;
        for (boolean p : isPrime) {
            if (p) {
                count++;
            }
        }
        return count;
    }
}

// 205. Isomorphic Strings
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i), tc = t.charAt(i);
            if (charMap.containsKey(sc)) {
                if (charMap.get(sc) != tc) {
                    return false;
                }
            } else if (charMap.containsValue(tc)) {
                return false;
            }
            charMap.put(sc, tc);
        }
        return true;
    }
}

// 206. Reverse Linked List
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
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}

// 207. Course Schedule
class Solution {
    private ArrayList<Integer>[] adjacent;
    private int[] visited;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.adjacent = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjacent[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            adjacent[pre[0]].add(pre[1]);
        }
        this.visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(int node) {
        if (this.visited[node] == 1) {
            return false;
        }
        if (this.visited[node] == 2) {
            return true;
        }
        this.visited[node] = 1;
        for (int n : this.adjacent[node]) {
            if (!dfs(n)) {
                return false;
            }
        }
        this.visited[node] = 2;
        return true;
    }
}

// 208. Implement Trie (Prefix Tree)
class Trie {
    class Node {
        Node[] nodes;
        boolean isEnd;
        Node() {
            nodes = new Node[26];
        }
        private void insert(String word, int idx) {
            if (word.length() <= idx) {
                return;
            }
            int i = word.charAt(idx) - 'a';
            if (nodes[i] == null) {
                nodes[i] = new Node();
            }
            if (idx == word.length() - 1) {
                nodes[i].isEnd = true;
            }
            nodes[i].insert(word, idx + 1);
        }
        private boolean search(String word, int idx) {
            if (word.length() <= idx) {
                return false;
            }
            Node node = nodes[word.charAt(idx) - 'a'];
            if (node == null) {
                return false;
            }
            if (idx == word.length() - 1 && node.isEnd) {
                return true;
            }
            return node.search(word, idx + 1);
        }
        private boolean startWith(String prefix, int idx) {
            if (prefix.length() <= idx) {
                return false;
            }
            Node node = nodes[prefix.charAt(idx) - 'a'];
            if (node == null) {
                return false;
            }
            if (idx == prefix.length() - 1) {
                return true;
            }
            return node.startWith(prefix, idx + 1);
        }
    }
    Node root;
    public Trie() {
        root = new Node();
    }
    public void insert(String word) {
        root.insert(word, 0);
    }
    public boolean search(String word) {
        return root.search(word, 0);
    }
    public boolean startsWith(String prefix) {
        return root.startWith(prefix, 0);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

// 209. Minimum Size Subarray Sum
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0, sum = 0, res = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r++];
            while (target <= sum) {
                res = Math.min(res, r - l);
                sum -= nums[l++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

// 210. Course Schedule II
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] sortedOrder = new int[numCourses];
        int idx = 0;
        if (numCourses <= 0) {
            return new int[0];
        }
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][1], child = prerequisites[i][0];
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }
        while (!sources.isEmpty()) {
            int vertex = sources.poll();
            sortedOrder[idx++] = vertex;
            List<Integer> children = graph.get(vertex);
            for (int child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    sources.add(child);
                }
            }
        }
        if (idx != numCourses) {
            return new int[0];
        }
        return sortedOrder;
    }
}

// 211. Design Add and Search Words Data Structure
class WordDictionary {
    private WordDictionary[] children;
    boolean isEnd;
    public WordDictionary() {
        children = new WordDictionary[26];
        isEnd = false;
    }

    public void addWord(String word) {
        WordDictionary curr = this;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new WordDictionary();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        WordDictionary curr = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (WordDictionary ch : curr.children) {
                    if (ch != null && ch.search(word.substring(i + 1))) {
                        return true;
                    }
                }
                return false;
            }
            if (curr.children[c - 'a'] == null) {
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return curr != null && curr.isEnd;
    }
}
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

// 212. Word Search II
class TrieNode {
    TrieNode[] next = new TrieNode[26];
    String word;
}
class Solution {
    private List<String> res = new ArrayList<>();
    TrieNode root = new TrieNode();
    private TrieNode buildTrie(String[] words) {
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                }
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root);
            }
        }
        return res;
    }
    private void dfs(char[][] board, int i, int j, TrieNode p) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) {
            return;
        }
        p = p.next[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
            p.word = null;
        }
        board[i][j] = '#';
        if (0 < i) {
            dfs(board, i - 1, j, p);
        }
        if (0 < j) {
            dfs(board, i, j - 1, p);
        }
        if (i < board.length - 1) {
            dfs(board, i + 1, j, p);
        }
        if (j < board[0].length - 1) {
            dfs(board, i, j + 1, p);
        }
        board[i][j] = c;
    }
}

// 213. House Robber II
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];

        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);

        dp2[1] = nums[1];

        for (int i = 2; i < nums.length; i++) {
            dp1[i] = Math.max(nums[i] + dp1[i - 2], dp1[i - 1]);
            dp2[i] = Math.max(nums[i] + dp2[i - 2], dp2[i - 1]);
        }
        return Math.max(dp1[nums.length - 2], dp2[nums.length - 1]);
    }
}

// 214. Shortest Palindrome
class Solution {
    public String shortestPalindrome(String s) {
        int count = kmp(new StringBuilder(s).reverse().toString(), s);
        return new StringBuilder(s.substring(count)).reverse().toString() + s;
    }
    private int kmp(String txt, String patt) {
        String newString = patt + "#" + txt;
        int[] pi = new int[newString.length()];
        int i = 1, k = 0;
        while (i < newString.length()) {
            if (newString.charAt(i) == newString.charAt(k)) {
                k++;
                pi[i] = k;
                i++;
            } else {
                if (0 < k) {
                    k = pi[k - 1];
                } else {
                    pi[i] = 0;
                    i++;
                }
            }
        }
        return pi[newString.length() - 1];
    }
}

// 215. Kth Largest Element in an Array
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    private int quickSelect(int[] nums, int left, int right, int target) {
        if (left == right) {
            return nums[left];
        }
        int pivot = nums[left];
        int low = left;
        int high = right;
        while (low <= high) {
            while (low <= high && nums[low] < pivot) {
                low++;
            }
            while (low <= high && nums[high] > pivot) {
                high--;
            }
            if (low <= high) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
                low++;
                high--;
            }
        }
        if (target <= high) {
            return quickSelect(nums, left, high, target);
        } else if (low <= target) {
            return quickSelect(nums, low, right, target);
        } else {
            return nums[target];
        }
    }
 }

// 216. Combination Sum III
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(1, k, n, new ArrayList<>(), result);
        return result;
    }
    private void backTrack(int start, int k, int target, List<Integer> current, List<List<Integer>> result) {
        if (k == 0 && target == 0) {
            result.add(new ArrayList<>(current));
        }
        for (int i = start; i <= 9; i++) {
            if (target < i) {
                break;
            }
            current.add(i);
            backTrack(i + 1, k - 1, target - i, current, result);
            current.remove(current.size() - 1);
        }
    }
}

// 217. Contains Duplicate
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int n : nums) {
            s.add(n);
        }
        return s.size() < nums.length;
    }
}

// 218. The Skyline Problem
class SegmentTree {
    int[] range;
    int height;
    SegmentTree left;
    SegmentTree right;
    public SegmentTree(int low, int high) {
        this.range = new int[]{low, high};
        this.height = 0;
        if (low == high) {
            this.left = null;
            this.right = null;
            return;
        }
        int mid = low + (high - low) / 2;
        this.left = new SegmentTree(low, mid);
        this.right = new SegmentTree(mid + 1, high);
    }
    public void update(int low, int high, int height) {
        if (high < this.range[0] || this.range[1] < low) {
            return;
        }
        if (this.range[0] == this.range[1]) {
            this.height = Math.max(this.height, height);
            return;
        }
        if (height < this.height) {
            return;
        }
        if (this.left != null) {
            this.left.update(low, high, height);
        }
        if (this.right != null) {
            this.right.update(low, high, height);
        }
        this.height = Math.min(this.left.height, this.right.height);
    }
    public int get(int index) {
        if (this.range[0] == this.range[1]) {
            return this.height;
        }
        int result = 0;
        int mid = this.range[0] + (this.range[1] - this.range[0]) / 2;
        if (index <= mid) {
            result = this.left.get(index);
        } else {
            result = this.right.get(index);
        }
        return result;
    }
}

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeSet<Integer> treeset = new TreeSet<>();
        for (int[] building : buildings) {
            treeset.add(building[0]);
            treeset.add(building[1]);
        }
        Map<Integer, Integer> positionToIndex = new HashMap<>();
        Map<Integer, Integer> indexToPosition = new HashMap<>();
        int currIndex = 0;
        for (int position : treeset) {
            positionToIndex.put(position, currIndex);
            indexToPosition.put(currIndex, position);
            currIndex++;
        }
        int numOfPositions = treeset.size();
        SegmentTree segmentTree = new SegmentTree(0, numOfPositions - 1);

        Arrays.sort(buildings, (a, b) -> b[2] - a[2]);
        for (int[] building : buildings) {
            int left = building[0];
            int right = building[1];
            int height = building[2];
            int leftIndex = positionToIndex.get(left);
            int rightIndex = positionToIndex.get(right);
            segmentTree.update(leftIndex, rightIndex - 1, height);
        }

        List<List<Integer>> result = new LinkedList<>();
        int currHeight = 0;
        for (int index = 0; index < numOfPositions; index++) {
            int indexPosition = indexToPosition.get(index);
            int height = segmentTree.get(index);
            if (currHeight == height) {
                continue;
            }
            List<Integer> position = new ArrayList<>();
            position.add(indexPosition);
            position.add(height);
            result.add(position);
            currHeight = height;
        }
        return result;
    }
}

// 219. Contains Duplicate II
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}

// 220. Contains Duplicate III
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (indexDiff < 1 || valueDiff < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long)nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long)valueDiff + 1);
            if (map.containsKey(bucket)
                || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= valueDiff)
                || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= valueDiff)) {
                return true;
            }
            if (map.entrySet().size() >= indexDiff) {
                long lastBucket = ((long)nums[i - indexDiff] - Integer.MIN_VALUE) / ((long)valueDiff + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }
}

// 221. Maximal Square
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}

// 222. Count Complete Tree Nodes
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

// 223. Rectangle Area
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);

        int overlapWidth = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));
        int overlapHeight = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));

        int overlapArea = overlapWidth * overlapHeight;

        return area1 + area2 - overlapArea;
    }
}

// 224. Basic Calculator
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int res = 0, number = 0, sign = 1;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = 10 * number + (int)(c - '0');
            } else if (c == '+') {
                res += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                res += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res += sign * number;
                number = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }
        if (number != 0) {
            res += sign * number;
        }
        return res;
    }
}

// 225. Implement Stack using Queues
class MyStack {
    private Queue<Integer> q;
    public MyStack() {
        q = new LinkedList<>();
    }
    public void push(int x) {
        q.add(x);
        for (int i = 1; i < q.size(); i++) {
            q.add(q.remove());
        }
    }
    public int pop() {
        return q.remove();
    }
    public int top() {
        return q.peek();
    }
    public boolean empty() {
        return q.isEmpty();
    }
}
/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

// 226. Invert Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}

// 227. Basic Calculator II
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char operator = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (isOperator(c) || i == s.length() - 1) {
                if (operator == '+') {
                    stack.push(num);
                } else if (operator == '-') {
                    stack.push(-num);
                } else if (operator == '*') {
                    stack.push(stack.pop() * num);
                } else if (operator == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                operator = c;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}

// 228. Summary Ranges
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (start != nums[i]) {
                res.add("" + start + "->" + nums[i]);
            } else {
                res.add("" + start);
            }
        }
        return res;
    }
}

// 229. Majority Element II
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0;
        int candidate1 = 0, candidate2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count1 == 0 && nums[i] != candidate2) {
                count1 = 1;
                candidate1 = nums[i];
            } else if (count2 == 0 && nums[i] != candidate1) {
                count2 = 1;
                candidate2 = nums[i];
            } else if (candidate1 == nums[i]) {
                count1++;
            } else if (candidate2 == nums[i]) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        List<Integer> res = new ArrayList<>();
        int threshold = nums.length / 3;
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (candidate1 == nums[i]) {
                count1++;
            } else if (candidate2 == nums[i]) {
                count2++;
            }
        }
        if (count1 > threshold) {
            res.add(candidate1);
        }
        if (count2 > threshold) {
            res.add(candidate2);
        }
        return res;
    }
}

// 230. Kth Smallest Element in a BST
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int result = 0;
    private int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        this.count = k;
        recursive(root);
        return this.result;
    }
    private void recursive(TreeNode n) {
        if (n == null || this.count == 0) {
            return;
        }
        recursive(n.left);
        this.count--;
        if (this.count == 0) {
            this.result = n.val;
            return;
        }
        recursive(n.right);
    }
}

// 231. Power of Two
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        return (n % 2 == 0) && isPowerOfTwo(n / 2);
    }
}

// 232. Implement Queue using Stacks
class MyQueue {
    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();
    public MyQueue() {

    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        peek();
        return output.pop();
    }

    public int peek() {
        if (output.empty()) {
            while (!input.empty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

// 233. Number of Digit One
class Solution {
    public int countDigitOne(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10) {
            ones += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? n % m + 1 : 0);
        }
        return ones;
    }
}

// 234. Palindrome Linked List
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
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head, prev = null, tmp = null;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        prev = slow;
        slow = slow.next;
        prev.next = null;
        while (slow != null) {
            tmp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = tmp;
        }
        fast = head;
        slow = prev;
        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
}

// 235. Lowest Common Ancestor of a Binary Search Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int small = Math.min(p.val, q.val);
        int larget = Math.max(p.val, q.val);
        while (root != null) {
            if (root.val > larget) {
                root = root.left;
            } else if (root.val < small) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}

// 236. Lowest Common Ancestor of a Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}

// 237. Delete Node in a Linked List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

// 238. Product of Array Except Self
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int res[] = new int[n];
        Arrays.fill(res, 1);
        int curr = 1;
        for (int i = 0; i < n; i++) {
            res[i] *= curr;
            curr *= nums[i];
        }
        curr = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= curr;
            curr *= nums[i];
        }
        return res;
    }
}

// 239. Sliding Window Maximum
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int ri = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.offer(i);
            if (i >= k - 1) {
                res[ri++] = nums[q.peek()];
            }
        }
        return res;
    }
}

// 240. Search a 2D Matrix II
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}

// 241. Different Ways to Add Parentheses
class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char oper = expression.charAt(i);
            if (oper == '+' || oper == '-' || oper == '*') {
                List<Integer> s1 = diffWaysToCompute(expression.substring(0, i));
                List<Integer> s2 = diffWaysToCompute(expression.substring(i + 1));
                for (int a : s1) {
                    for (int b : s2) {
                        if (oper == '+') {
                            res.add(a + b);
                        } else if (oper == '-') {
                            res.add(a - b);
                        } else if (oper == '*') {
                            res.add(a * b);
                        }
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.parseInt(expression));
        }
        return res;
    }
}

// 242. Valid Anagram
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] alpha = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            alpha[s.charAt(i) - 'a']++;
            alpha[t.charAt(i) - 'a']--;
        }
        for (int i : alpha) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}

// 257. Binary Tree Paths
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<String> res = new ArrayList<String>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root != null) {
            searchBST(root, "");
        }
        return res;
    }
    private void searchBST(TreeNode root, String path) {
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
        }
        if (root.left != null) {
            searchBST(root.left, path + root.val + "->");
        }
        if (root.right != null) {
            searchBST(root.right, path + root.val + "->");
        }
    }
}

// 258. Add Digits
class Solution {
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        } else if (num % 9 == 0) {
            return 9;
        } else {
            return num % 9;
        }
    }
}

// 260. Single Number III
class Solution {
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] res = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                res[index++] = entry.getKey();
            }
        }
        return res;
    }
}

// 263. Ugly Number
class Solution {
    public boolean isUgly(int n) {
        for (int i = 2; i < 6 && 0 < n; i++) {
            while (n % i == 0) {
                n /= i;
            }
        }
        return n == 1;
    }
}

// 264. Ugly Number II
class Solution {
    public int nthUglyNumber(int n) {
        int res[] = new int[n];
        res[0] = 1;
        int idx2 = 0, idx3 = 0, idx5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(factor2, factor3), factor5);
            res[i] = min;
            if (factor2 == min) {
                factor2 = 2 * res[++idx2];
            }
            if (factor3 == min) {
                factor3 = 3 * res[++idx3];
            }
            if (factor5 == min) {
                factor5 = 5 * res[++idx5];
            }
        }
        return res[n - 1];
    }
}

// 268. Missing Number
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = res ^ i;
        }
        for (int i = 0; i < n; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}

// 273. Integer to English Words
class Solution {
    private final String[] belowTen = new String[] {
        "",
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six",
        "Seven",
        "Eight",
        "Nine"
    };
    private final String[] belowTwenty = new String[] {
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen"
    };
    private final String[] belowHundred = new String[] {
        "",
        "Ten",
        "Twenty",
        "Thirty",
        "Forty",
        "Fifty",
        "Sixty",
        "Seventy",
        "Eighty",
        "Ninety"
    };
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return helper(num);
    }
    private String helper(int num) {
        String result = new String();
        if (num < 10) {
            result = belowTen[num];
        } else if (num < 20) {
            result = belowTwenty[num -10];
        } else if (num < 100) {
            result = belowHundred[num/10] + " " + helper(num % 10);
        } else if (num < 1000) {
            result = helper(num/100) + " Hundred " +  helper(num % 100);
        } else if (num < 1000000) {
            result = helper(num/1000) + " Thousand " +  helper(num % 1000);
        } else if (num < 1000000000) {
            result = helper(num/1000000) + " Million " +  helper(num % 1000000);
        } else {
            result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
        }
        return result.trim();
    }
}

// 274. H-Index
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        for (int c : citations) {
            if (c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += buckets[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }
}

// 275. H-Index II
class Solution {
    public int hIndex(int[] citations) {
        int low = 0, high = citations.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (citations[mid] == citations.length - mid) {
                return citations[mid];
            } else if (citations[mid] < citations.length - mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return citations.length - low;
    }
}

// 278. First Bad Version
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 0, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

// 279. Perfect Squares
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }
}

// 282. Expression Add Operators
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        if (num == null || num.length() == 0) {
            return res;
        }
        helper(res, "", num, target, 0, 0, 0);
        return res;
    }
    private void helper(List<String> res, String path, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {
            if (target == eval) {
                res.add(path);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            long curr = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(res, path + curr, num, target, i + 1, curr, curr);
            } else {
                helper(res, path + "+" + curr, num, target, i + 1, eval + curr, curr);
                helper(res, path + "-" + curr, num, target, i + 1, eval - curr, -curr);
                helper(res, path + "*" + curr, num, target, i + 1, eval - multed + multed * curr, multed * curr);
            }
        }
    }
}

// 283. Move Zeroes
class Solution {
    public void moveZeroes(int[] nums) {
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] != 0) {
                int tmp = nums[r];
                nums[r] = nums[l];
                nums[l++] = tmp;
            }
        }
    }
}

// 284. Peeking Iterator
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    private Integer next = null;
    private Iterator<Integer> iter;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    iter = iterator;
        if (iter.hasNext()) {
            next = iter.next();
        }
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    Integer res = next;
        next = iter.hasNext() ? iter.next() : null;
        return res;
	}

	@Override
	public boolean hasNext() {
	    return next != null;
	}
}

// 287. Find the Duplicate Number
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }
        int slow2 = nums[0];
        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }
        return slow;
    }
}
