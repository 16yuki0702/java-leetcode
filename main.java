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
