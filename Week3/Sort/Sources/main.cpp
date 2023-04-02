#include "sort.h"
#include<iostream>
#include<time.h>
#include<cstdio>
#include<fstream>
using namespace std;

int cinNumber() {
	char temdata[100];
	int data = 0;

	int i = 0;
	//如果输出非数字则重新输入，如果数字没有对应操作数则重新输入，返回正确操作数
	scanf_s("%s", &temdata, 100);
	while (1)
	{
	here:
		if (temdata[i] > '9' || temdata[i] < '0')
		{
			printf("请输入正确的数字\n");
			scanf_s("%s", &temdata, 100);
			i = 0;
		}
		i++;
		if (temdata[i] == '\0') {
			for (i = 0; temdata[i] != '\0'; i++)
			{
				if (data > 214748364)
				{
					printf("请输入正确的数字\n");
					scanf_s("%s", &temdata, 100);
					i = 0;
					goto here;
				}
				data = data * 10 + temdata[i] - '0';
			}
			break;

		}
	}
	return data;
}

int f() {
	char exp[200];
	int i = 0;
	int mins = 0;
	int data = 0;
	cout << "请输入要找的第几大或小的数字，正数表示第几大，负数表示第几小（例：-3表示找第三小的数）：" << endl;
	scanf_s("%s", &exp, 200);
	while (1)
	{
	here:
		if (exp[i] > '9' || exp[i] < '0')
		{
			cout << "请输入要找的第几大或小的数字，正数表示第几大，负数表示第几小（例：-3表示找第三小的数）：" << endl;
			scanf_s("%s", &exp, 100);
			i = 0;
			mins = 0;
			if (exp[0] == '-') {
				mins = -1;
				i++;
			}
		}
		i++;
		if (exp[i] == '\0') {
			for (i = 0; exp[i] != '\0'; i++)
			{
				if (data > 214748364)
				{
					cout << "数字过大"<<endl;
					scanf_s("%s", &exp, 100);
					i = 0;
					mins = 0;
					if (exp[0] == '-') {
						mins = -1;
						i++;
					}
					goto here;
				}
				data = data * 10 + exp[i] - '0';
			}
			if (data == 0) {
				cout << "不能为0" << endl;
				scanf_s("%s", &exp, 100);
				i = 0;
				mins = 0;
				if (exp[0] == '-') {
					mins = -1;
					i++;
				}
				goto here;
			}
			break;

		}
	}
	return data;
}

void bigData() {
	int* a;
	int* b;

	int oper = 0;
	do {
		cout << "请输入排序方式" << endl;;
		cout << "1.插入排序" << endl;
		cout << "2.归并排序" << endl;
		cout << "3.快速排序（递归版）" << endl;
		cout << "4.快速排序（非递归版）" << endl;
		cout << "5.快速排序（枢轴存放）" << endl;
		cout << "6.计数排序" << endl;
		cout << "7.基数计数排序" << endl;
		cout << "8.颜色排序" << endl;
		cout << "9.找到第k大或小的数" << endl;
		cout << "10.冒泡排序（双向冒泡）" << endl;
		cout << "请输入正确的操作数(1-10))：";
		oper = cinNumber();
		if (oper < 1 || oper > 10) {
			cout << "操作数错误" << endl;
		}
	} while (oper < 1 || oper > 10);
	int digital = 200000;
	int type = 0;
	do {
		cout << "输入层次" << endl;
		cout << "1.10000" << endl;
		cout << "2.50000" << endl;
		cout << "3.200000" << endl;
		cout << "请输入正确操作数（1-3）：";
		type = cinNumber();
		if (type < 1 || type>3) {
			cout << "操作数错误" << endl;
		}
	} while (type < 1 || type>3);

	switch (type)
	{
	case 1:
		digital = 10000;
		break;
	case 2:
		digital = 50000;
		break;
	case 3:
		digital = 200000;
		break;
	default:
		break;
	}
	a = new int[digital];
	b = new int[digital];
	if (oper == 2) {
		for (int i = 0; i < digital; i++) {
			b[i] = rand() % 100 + 1;
		}
	}
	if (oper == 8) {
		for (int i = 0; i < digital; i++) {
			a[i] = rand() % 3;
			/*cout << a[i] << "\t";
			if (i % 10 == 9) {
				cout << endl;
			}*/
		}
	}
	else {
		for (int i = 0; i < digital; i++) {
			a[i] = rand() % 100 + 1;
			/*cout << a[i] << "\t";
			if (i % 10 == 9) {
				cout << endl;
			}*/
		}
	}

	//cout << "---------------------------------------------------------------------------------" << endl;
	clock_t start, end;
	int k;
	switch (oper)
	{
	case 1:
		start = clock();
		insertSort(a, digital);
		end = clock();
		break;
	case 2:
		start = clock();
		MergeSort(a, 0, digital - 1, b);
		end = clock();
		break;
	case 3:
		start = clock();
		QuickSort_Recursion(a, 0, digital - 1);
		end = clock();
		break;
	case 4:
		start = clock();
		QuickSort(a, digital);
		end = clock();
		break;
	case 5:
		start = clock();
		QuickSort2(a, 0, digital - 1);
		end = clock();
		break;
	case 6:
		start = clock();
		CountSort(a, digital, 100);
		end = clock();
		break;
	case 7:
		start = clock();
		RadixCountSort(a, digital);
		end = clock();
		break;
	case 8:
		start = clock();
		ColorSort(a, digital);
		end = clock();
		break;
	case 9:
		k = f();
		while (abs(k) >= digital) {
			cout << "越界，数组长度为" << digital << endl;
			k = f();
		}
		start = clock();
		k = findK(a, 0, digital - 1, k);
		end = clock();
		break;
	case 10:
		start = clock();
		BubbleSort(a, digital);
		end = clock();
		break;
	default:
		break;
	}
	/*if (oper != 9) {
		for (int i = 0; i < digital; i++) {
			cout << a[i] << "\t";
			if (i % 10 == 9) {
				cout << endl;
			}
		}
	}
	else {
		cout << "结果为：" << k << endl;
	}*/
	clock_t time = end - start;
	cout << "排序用时：" << time << "ms" << endl;
	free(a);
	free(b);
}

void smallData() {

	int oper = 0;
	do {
		cout << "请输入排序方式" << endl;;
		cout << "1.插入排序" << endl;
		cout << "2.归并排序" << endl;
		cout << "3.快速排序（递归版）" << endl;
		cout << "4.快速排序（非递归版）" << endl;
		cout << "5.快速排序（枢轴存放）" << endl;
		cout << "6.计数排序" << endl;
		cout << "7.基数计数排序" << endl;
		cout << "8.颜色排序" << endl;
		cout << "9.找到第k大或小的数" << endl;
		cout << "10.冒泡排序（双向冒泡）" << endl;
		cout << "请输入正确的操作数(1-9))：";
		oper = cinNumber();
		if (oper < 1 || oper > 10) {
			cout << "操作数错误" << endl;
		}
	} while (oper < 1 || oper > 10);
	
	int (*a)[100] = new int[100][100];
	int (*b)[100] = new int[100][100];
	if (oper == 2) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				b[i][j] = rand() % 100 + 1;
			}
		}
	}
	if (oper == 8) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				a[i][j] = rand() % 3;
			}
		}
	}
	else {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				a[i][j] = rand() % 100;
			}
		}
	}

	clock_t start, end;
	int k;
	switch (oper)
	{
	case 1:
		start = clock();
		for (int i = 0; i < 100; i++) {
			insertSort(a[i], 100);
		}
		end = clock();
		break;
	case 2:
		start = clock();
		for (int i = 0; i < 100; i++) {
			MergeSort(a[i], 0, 100 - 1, b[i]);
		}
		end = clock();
		break;
	case 3:
		start = clock();
		for (int i = 0; i < 100; i++) {
			QuickSort_Recursion(a[i], 0, 99);
		}
		end = clock();
		break;
	case 4:
		start = clock();
		for (int i = 0; i < 100; i++) {
			QuickSort(a[i], 100);
		}
		end = clock();
		break;
	case 5:
		start = clock();
		for (int i = 0; i < 100; i++) {
			QuickSort2(a[i], 0, 99);
		}
		end = clock();
		break;
	case 6:
		start = clock();
		for (int i = 0; i < 100; i++) {
			CountSort(a[i], 100, 100);
		}
		end = clock();
		break;
	case 7:
		start = clock();
		for (int i = 0; i < 100; i++) {
			RadixCountSort(a[i], 100);
		}
		end = clock();
		break;
	case 8:
		start = clock();
		for (int i = 0; i < 100; i++) {
			ColorSort(a[i], 100);
		}
		end = clock();
		break;
	case 9:
		k = f();
		while (abs(k) >= 100) {
			cout << "越界，数组长度为" << 100 << endl;
			k = f();
		}
		start = clock();
		for (int i = 0; i < 100; i++) {
			findK(a[i], 0, 99, k);
		}
		end = clock();
		break;
	case 10:
		start = clock();
		for (int i = 0; i < 100; i++) {
			BubbleSort(a[i], 100);
		}
		end = clock();
		break;
	default:
		break;
	}
	clock_t time = end - start;
	cout << "排序用时：" << time << "ms" << endl;
	free(a);
	free(b);
}

void fileRead() {
	int* a = new int[200000];
	int* b = new int[200000];
	int cnt = 0;
	ifstream is("./data.txt", ios::in);
	if (!is) {
		cout << "打开文件失败" << endl;
		exit(1);
	}
	while (!is.eof()) {
		is >> a[cnt++];
		if (cnt == 200000) {
			break;
		}
	}
	is.close();
	int oper = 0;
	do {
		cout << "请输入排序方式" << endl;;
		cout << "1.插入排序" << endl;
		cout << "2.归并排序" << endl;
		cout << "3.快速排序（递归版）" << endl;
		cout << "4.快速排序（非递归版）" << endl;
		cout << "5.快速排序（枢轴存放）" << endl;
		cout << "6.计数排序" << endl;
		cout << "7.基数计数排序" << endl;
		cout << "8.找到第k大或小的数" << endl;
		cout << "9.冒泡排序（双向冒泡）" << endl;
		cout << "请输入正确的操作数(1-9))：";
		oper = cinNumber();
		if (oper < 1 || oper > 10) {
			cout << "操作数错误" << endl;
		}
	} while (oper < 1 || oper > 10);
	

	clock_t start, end;
	int k;
	switch (oper)
	{
	case 1:
		start = clock();
		insertSort(a, cnt);
		end = clock();
		break;
	case 2:
		start = clock();
		MergeSort(a, 0, cnt - 1, b);
		end = clock();
		break;
	case 3:
		start = clock();
		QuickSort_Recursion(a, 0, cnt - 1);
		end = clock();
		break;
	case 4:
		start = clock();
		QuickSort(a, cnt);
		end = clock();
		break;
	case 5:
		start = clock();
		QuickSort2(a, 0, cnt - 1);
		end = clock();
		break;
	case 6:
		start = clock();
		CountSort(a, cnt, 100);
		end = clock();
		break;
	case 7:
		start = clock();
		RadixCountSort(a, cnt);
		end = clock();
		break;
	case 8:
		start = clock();
		ColorSort(a, cnt);
		end = clock();
		break;
	case 9:
		k = f();
		while (abs(k) >= cnt) {
			cout << "越界，数组长度为" << cnt << endl;
			k = f();
		}
		start = clock();
		k = findK(a, 0, cnt - 1, k);
		end = clock();
		break;
	case 10:
		start = clock();
		BubbleSort(a, cnt);
		end = clock();
		break;
	default:
		break;
	}
	clock_t time = end - start;
	cout << "排序用时：" << time << "ms" << endl;
	free(a);
	free(b);
}

void fileWrite() {
	int digital = 200000;
	int type = 0;
	do {
		cout << "输入层次" << endl;
		cout << "1.10000" << endl;
		cout << "2.50000" << endl;
		cout << "3.200000" << endl;
		cout << "请输入正确操作数（1-3）：";
		type = cinNumber();
		if (type < 1 || type>3) {
			cout << "操作数错误" << endl;
		}
	} while (type < 1 || type>3);

	switch (type)
	{
	case 1:
		digital = 10000;
		break;
	case 2:
		digital = 50000;
		break;
	case 3:
		digital = 200000;
		break;
	default:
		break;
	}
	int* a = new int[digital];
	int *b = new int[digital];
	for (int i = 0; i < digital; i++) {
		a[i] = rand() % 100 + 1;;
	}
	ofstream os("./data.txt");
	for (int i = 0; i < digital; i++) {
		os << a[i] << " ";
	}
	cout << "写出成功" << endl;
}

void fileData() {
	
	int oper;
	int* a = new int[200000];
	do {
		cout << "读取或输出到文件" << endl;
		cout << "1.读取文件测试数据" << endl;
		cout << "2.输出测试数据到文件" << endl;
		cout << "请输入响应操作（1-2）" << endl;
		oper = cinNumber();
		if (oper > 2 || oper < 1) {
			cout << "操作数错误" << endl;
		}
	} while (oper > 2 || oper < 1);
	switch (oper)
	{
	case 1:
		fileRead();
		break;
	case 2:
		fileWrite();
	default:
		break;
	}
}

int main() {
	int oper;
	while (true)
	{
		do {
			cout << "请输入测试类型" << endl;
			cout << "1.不同大数据量" << endl;
			cout << "2.大量小数据（100次长度为100的数组）" << endl;
			cout << "3.生成或读取到文件" << endl;
			cout << "请输入响应操作数（1-3）：" << endl;
			oper = cinNumber();
			if (oper > 3 || oper < 1) {
				cout << "操作数错误" << endl;
			}
		} while (oper > 3 || oper < 1);
		switch (oper)
		{
		case 1:
			bigData();
			break;
		case 2:
			smallData();
		case 3:
			fileData();
		default:
			break;
		}

	}
	return 0;
}

