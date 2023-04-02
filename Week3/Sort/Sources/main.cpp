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
	//���������������������룬�������û�ж�Ӧ���������������룬������ȷ������
	scanf_s("%s", &temdata, 100);
	while (1)
	{
	here:
		if (temdata[i] > '9' || temdata[i] < '0')
		{
			printf("��������ȷ������\n");
			scanf_s("%s", &temdata, 100);
			i = 0;
		}
		i++;
		if (temdata[i] == '\0') {
			for (i = 0; temdata[i] != '\0'; i++)
			{
				if (data > 214748364)
				{
					printf("��������ȷ������\n");
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
	cout << "������Ҫ�ҵĵڼ����С�����֣�������ʾ�ڼ��󣬸�����ʾ�ڼ�С������-3��ʾ�ҵ���С��������" << endl;
	scanf_s("%s", &exp, 200);
	while (1)
	{
	here:
		if (exp[i] > '9' || exp[i] < '0')
		{
			cout << "������Ҫ�ҵĵڼ����С�����֣�������ʾ�ڼ��󣬸�����ʾ�ڼ�С������-3��ʾ�ҵ���С��������" << endl;
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
					cout << "���ֹ���"<<endl;
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
				cout << "����Ϊ0" << endl;
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
		cout << "����������ʽ" << endl;;
		cout << "1.��������" << endl;
		cout << "2.�鲢����" << endl;
		cout << "3.�������򣨵ݹ�棩" << endl;
		cout << "4.�������򣨷ǵݹ�棩" << endl;
		cout << "5.�������������ţ�" << endl;
		cout << "6.��������" << endl;
		cout << "7.������������" << endl;
		cout << "8.��ɫ����" << endl;
		cout << "9.�ҵ���k���С����" << endl;
		cout << "10.ð������˫��ð�ݣ�" << endl;
		cout << "��������ȷ�Ĳ�����(1-10))��";
		oper = cinNumber();
		if (oper < 1 || oper > 10) {
			cout << "����������" << endl;
		}
	} while (oper < 1 || oper > 10);
	int digital = 200000;
	int type = 0;
	do {
		cout << "������" << endl;
		cout << "1.10000" << endl;
		cout << "2.50000" << endl;
		cout << "3.200000" << endl;
		cout << "��������ȷ��������1-3����";
		type = cinNumber();
		if (type < 1 || type>3) {
			cout << "����������" << endl;
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
			cout << "Խ�磬���鳤��Ϊ" << digital << endl;
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
		cout << "���Ϊ��" << k << endl;
	}*/
	clock_t time = end - start;
	cout << "������ʱ��" << time << "ms" << endl;
	free(a);
	free(b);
}

void smallData() {

	int oper = 0;
	do {
		cout << "����������ʽ" << endl;;
		cout << "1.��������" << endl;
		cout << "2.�鲢����" << endl;
		cout << "3.�������򣨵ݹ�棩" << endl;
		cout << "4.�������򣨷ǵݹ�棩" << endl;
		cout << "5.�������������ţ�" << endl;
		cout << "6.��������" << endl;
		cout << "7.������������" << endl;
		cout << "8.��ɫ����" << endl;
		cout << "9.�ҵ���k���С����" << endl;
		cout << "10.ð������˫��ð�ݣ�" << endl;
		cout << "��������ȷ�Ĳ�����(1-9))��";
		oper = cinNumber();
		if (oper < 1 || oper > 10) {
			cout << "����������" << endl;
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
			cout << "Խ�磬���鳤��Ϊ" << 100 << endl;
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
	cout << "������ʱ��" << time << "ms" << endl;
	free(a);
	free(b);
}

void fileRead() {
	int* a = new int[200000];
	int* b = new int[200000];
	int cnt = 0;
	ifstream is("./data.txt", ios::in);
	if (!is) {
		cout << "���ļ�ʧ��" << endl;
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
		cout << "����������ʽ" << endl;;
		cout << "1.��������" << endl;
		cout << "2.�鲢����" << endl;
		cout << "3.�������򣨵ݹ�棩" << endl;
		cout << "4.�������򣨷ǵݹ�棩" << endl;
		cout << "5.�������������ţ�" << endl;
		cout << "6.��������" << endl;
		cout << "7.������������" << endl;
		cout << "8.�ҵ���k���С����" << endl;
		cout << "9.ð������˫��ð�ݣ�" << endl;
		cout << "��������ȷ�Ĳ�����(1-9))��";
		oper = cinNumber();
		if (oper < 1 || oper > 10) {
			cout << "����������" << endl;
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
			cout << "Խ�磬���鳤��Ϊ" << cnt << endl;
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
	cout << "������ʱ��" << time << "ms" << endl;
	free(a);
	free(b);
}

void fileWrite() {
	int digital = 200000;
	int type = 0;
	do {
		cout << "������" << endl;
		cout << "1.10000" << endl;
		cout << "2.50000" << endl;
		cout << "3.200000" << endl;
		cout << "��������ȷ��������1-3����";
		type = cinNumber();
		if (type < 1 || type>3) {
			cout << "����������" << endl;
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
	cout << "д���ɹ�" << endl;
}

void fileData() {
	
	int oper;
	int* a = new int[200000];
	do {
		cout << "��ȡ��������ļ�" << endl;
		cout << "1.��ȡ�ļ���������" << endl;
		cout << "2.����������ݵ��ļ�" << endl;
		cout << "��������Ӧ������1-2��" << endl;
		oper = cinNumber();
		if (oper > 2 || oper < 1) {
			cout << "����������" << endl;
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
			cout << "�������������" << endl;
			cout << "1.��ͬ��������" << endl;
			cout << "2.����С���ݣ�100�γ���Ϊ100�����飩" << endl;
			cout << "3.���ɻ��ȡ���ļ�" << endl;
			cout << "��������Ӧ��������1-3����" << endl;
			oper = cinNumber();
			if (oper > 3 || oper < 1) {
				cout << "����������" << endl;
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

