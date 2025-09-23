package string;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

class CompareVersion_165 {
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("[.]");
        String[] arr2 = version2.split("[.]");
        int n1 = arr1.length;
        int n2 = arr2.length;

        int i = 0, j = 0;
        while (i < n1 || j < n2) {
            int v1 = i < n1 ? Integer.parseInt(arr1[i]) : 0;
            int v2 = j < n2 ? Integer.parseInt(arr2[j]) : 0;
            if (v1 < v2) return -1;
            else if (v1 > v2) return 1;
            i++;
            j++;
        }

        return 0;
    }

    public int compareVersion2(String version1, String version2) {
        String[] arr1 = version1.split("[.]");
        String[] arr2 = version2.split("[.]");
        int len = Math.max(arr1.length, arr2.length);
        for (int i = 0; i < len; i++) {
            int v1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
            int v2 = i < arr2.length ? Integer.parseInt(arr2[i]) : 0;
            if (v1 > v2) return 1;
            if (v1 < v2) return -1;
        }
        return 0;
    }

    public int compareVersion3(String version1, String version2) {
        String[] arr1 = version1.split("[.]");
        String[] arr2 = version2.split("[.]");

        int len = Math.max(arr1.length, arr2.length);
        int[] first = new int[len];
        for (int i = 0; i < arr1.length; i++) {
            first[i] = Integer.parseInt(arr1[i]);
        }

        int[] second = new int[len];
        for (int i = 0; i < arr2.length; i++) {
            second[i] = Integer.parseInt(arr2[i]);
        }

        return Integer.compare(Arrays.compare(first, second), 0);
    }
}