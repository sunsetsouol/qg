#include "sort.h"
#include <cstdlib>
#include <queue>
using namespace std;

void insertSort(int* a, int n)
{
	int tem;
	for (int i = 1; i < n; i++) {
		tem = a[i];
		int j = i - 1;
		while (j >= 0 && a[j] > tem) {
			a[j+1] = a[j--];
		}
		a[j+1] = tem;
	}
}

void MergeArray(int* a, int begin, int mid, int end, int* temp)
{
	int i = begin;
	int j = mid + 1;
	int cur = begin;
	while (i <= mid+1 && j <= end+1) {
		if (i == mid + 1) {
			while (j <= end) {
				temp[cur++] = a[j++];
			}
			break;
		}
		if (j == end + 1) {
			while (i <= mid) {
				temp[cur++] = a[i++];
			}
			break;
		}
		if (a[i] > a[j]) {
			temp[cur++] = a[j++];
		}
		else {
			temp[cur++] = a[i++];
		}
	}
	for (int i = begin; i <= end; i++) {
		a[i] = temp[i];
	}
	
}

void MergeSort(int* a, int begin, int end, int* temp)
{
	if (begin >= end) {
		return;
	}
	int mid = begin + (end - begin) / 2;
	MergeSort(a, begin, mid, temp);
	MergeSort(a, mid+1, end, temp);
	MergeArray(a, begin, mid, end, temp);
}

void QuickSort_Recursion(int* a, int begin, int end)
{
	int i = begin;
	int j = end;
	int mid = a[i];
	while (i < j) {
		while (a[j] >= mid && i < j) {
			j--;
		}
		while (a[i] <= mid && i<j) {
			i++;
		}
		int tem = a[i];
		a[i] = a[j];
		a[j] = tem;
	}
	a[begin] = a[i];
	a[i] = mid;
	if (i > begin) {
		QuickSort_Recursion(a, begin, i - 1);
	}
	if (j < end) {
		QuickSort_Recursion(a, i + 1, end);
	}
}

void QuickSort(int* a, int size)
{
	queue<int>  q;
	int begin = 0;
	int end = size - 1;
	int mid;
	q.push(begin);
	q.push(end);
	while (!q.empty()) {
		begin = q.front();
		q.pop();
		end = q.front();
		q.pop();
		int i = begin;
		int j = end;
		int m = a[i];
		while (i < j) {
			while (a[j] >= m && i < j) {
				j--;
			}
			while (a[i] <= m && i < j) {
				i++;
			}
			int tem = a[i];
			a[i] = a[j];
			a[j] = tem;
		}
		a[begin] = a[i];
		a[i] = m;
		mid = i;

		if (begin < mid - 1) {
			q.push(begin);
			q.push(mid - 1);
		}
		if (end > mid + 1) {
			q.push(mid + 1);
			q.push(end);
		}

	}
	
}

void QuickSort2(int* a, int begin, int end)
{
	int i = begin;
	int j = end;
	int index = rand() % (end - begin) + begin;
	int mid = a[index];
	while (i < j) {
		while (a[j] >= mid && i < j) {
			j--;
		}
		while (a[i] <= mid && i < j) {
			i++;
		}
		int tem = a[i];
		a[i] = a[j];
		a[j] = tem;
	}
	a[index] = a[i];
	a[i] = mid;
	if (i > begin) {
		QuickSort_Recursion(a, begin, i - 1);
	}
	if (j < end) {
		QuickSort_Recursion(a, i + 1, end);
	}
}

void CountSort(int* a, int size, int max)
{
	vector<int> v(max + 1, 0);
	for (int i = 0; i < size; i++) {
		v[a[i]]++;
	}
	int j = 0;
	for (int i = 1; i <= max; i++) {
		while (v[i] != 0)
		{
			a[j++] = i;
			v[i]--;
		} 
	}
}

int getDigital(int num, int d) {
	for (int i = 0; i < d-1; i++) {
		num /= 10;
	}
	return num % 10;
}

void RadixCountSort(int* a, int size)
{
	int flag = 1;
	int exp = 1;
	int* count = (int*)malloc(sizeof(int) * 10);
	int* output = (int*)malloc(sizeof(int) * size);
	while (flag)
	{
		flag = 0;
		for (int i = 0; i < 10; i++) {
			count[i] = 0;
		}
		for (int i = 0; i < size; i++) {
			int index = getDigital(a[i], exp);
			if (index != 0) {
				flag = 1;
			}
			count[index]++;
		}
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}
		for (int i = size - 1; i >= 0; i--) {
			int j = getDigital(a[i], exp);
			output[count[j] - 1] = a[i];
			count[j]--;
		}
		for (int i = 0; i < size; i++) {
			a[i] = output[i];
		}
		exp ++;
	}
	free(count);
	free(output);
}

void ColorSort(int* a, int size)
{
	int pre = 0;
	int aft = size - 1;
	int cur = 0;
	int tem;
	while (cur < aft ) {
		if (a[cur] == 0 && pre < cur) {
			tem = a[pre];
			a[pre] = a[cur];
			a[cur] = tem;
			pre++;
		}
		else {
			if (a[cur] == 2 && cur<aft) {
				tem = a [aft];
				a[aft] = a[cur];
				a[cur] = tem;
				aft--;
			}
			else {
				cur++;
			}
		}
	}
}

int findK(int* a, int begin, int end, int k)
{
	if (begin >= end) {
		return a[end];
	}
	int i = begin;
	int j = end;
	int mid = a[i];
	while (i < j) {
		while (a[j] >= mid && i < j) {
			j--;
		}
		while (a[i] <= mid && i < j) {
			i++;
		}
		int tem = a[i];
		a[i] = a[j];
		a[j] = tem;
	}
	a[begin] = a[i];
	a[i] = mid;
	int index = -1;
	if (k > 0) {
		if (end - k + 1 == i) {
			index = a[i];
		}
		else {
			if (end - k + 1 > i ) {
				index = findK(a, i + 1, end, k);
			}
			else {
				index = findK(a, begin, i-1, k);
			}
		}
	}
	if (k < 0) {
		if (-k - 1 == i) {
			index = a[i];
		}
		else {
			if (-k - 1 > i) {
				index = findK(a, i + 1, end, k);
			}
			else {
				index = findK(a, begin, i - 1, k);
			}
		}
	}
	return index;
}

void BubbleSort(int* a, int size)
{
	int n = 0, m = size - 1, j, tem, pos1 = 0, pos2 = size - 1;
	for (int i = 0; i < size - 1; i++) {
		for (j = n; j < pos2; j++) {
			if (a[j] > a[j + 1]) {
				tem = a[j];
				a[j] = a[j + 1];
				a[j + 1] = tem;
				pos1 = j ;
			}
		}
		if (pos1 == pos2) {
			break;
		}
		pos2 = pos1;
		for (j = pos2; j > n; j--) {
			if (a[j] < a[j - 1]) {
				tem = a[j];
				a[j] = a[j - 1];
				a[j - 1] = tem;
				m = j ;
			}
		}
		if (n == m) {
			break;
		}
		n = m;
	}
}


