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

// 6. ZigZag Conversion
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> tmp = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            tmp.add(new StringBuilder());
        }
        int d = 1, row = 0;
        for (int i = 0; i < s.length(); i++) {
            tmp.get(row).append(String.valueOf(s.charAt(i)));
            row += d;
            if (row == numRows - 1) {
                d = -1;
            } else if (row == 0) {
                d = 1;
            }
        }
        String res = "";
        for (StringBuilder v: tmp) {
            res += v.toString();
        }
        return res;
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
