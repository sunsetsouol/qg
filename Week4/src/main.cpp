#include"binary_sort_tree.h"
#include<iostream>
using namespace std;


void show() {
	cout << "������" << endl;;
	cout << "1.��ʼ��������" << endl;
	cout << "2.��������" << endl;
	cout << "3.ɾ�����ݣ�" << endl;
	cout << "4.��������" << endl;
	cout << "5.��������ǵݹ�" << endl;
	cout << "6.��������ݹ�" << endl;
	cout << "7.��������ǵݹ�" << endl;
	cout << "8.��������ݹ�" << endl;
	cout << "9.��������ǵݹ�" << endl;
	cout << "10.���������ݹ�" << endl;
	cout << "11.�㼶����" << endl;
	cout << "��������ȷ�Ĳ�����(1-11))��";
}

void visit(NodePtr n) {
	cout << n->value << endl;
}

//��������
int cinNumber() {
	char input[200];
	int num;
	char ch;
	while (1) {
	restart:

		printf("���������֣�");
		scanf_s("%s", &input, 100);
		int i = 0;
		if (input[0] == '-') {
			i++;
		}
		while (input[i] != '\0' && input[i] != '\n')
		{
			if (input[i] > '9' || input[i] < '0') {
				cout << "������������������\n";
				goto restart;
			}
			i++;
		}
		if (sscanf_s(input, "%d", &num) == 1)
			break;
		printf("�����������������룡\n");
		memset(input, '\0', sizeof(input));
		while ((ch = getchar()) != '\n' && ch != EOF);
	}
	return num;
}


int main() {
	BinarySortTreePtr t =NULL;
	int oper = 0;
	int num;
	while (true)
	{
		do {
			show();
			oper = cinNumber();
			if (oper > 11 || oper < 1) {
				cout << "��������ȷ������" << endl;
			}

		} while (oper > 11 || oper < 1);

		switch (oper)
		{
		case 1:
			BST_init(&t);
			break;

		case 2:
			if (t == NULL) {
				cout << "��δ����ʼ��" << endl;
				break;
			}
			cout << "������Ҫ���������" << endl;
			num = cinNumber();
			if (BST_insert(t, num)) {
				cout << "����ɹ�" << endl;
			}
			else
			{
				cout << "����ʧ�ܣ���δ��ʼ���������Ѵ���" << endl;
			}
			break;

		case 3:
			if (t == NULL) {
				cout << "��δ����ʼ��" << endl;
				break;
			}
			cout << "������Ҫɾ��������" << endl;
			num = cinNumber();
			if (BST_delete(t, num)) {
				cout << "ɾ���ɹ�" << endl;
			}
			else
			{
				cout << "ɾ��ʧ�ܣ��������ڻ���������" << endl;
			}
			break;

		case 4:
			if (t == NULL) {
				cout << "��δ����ʼ��" << endl;
				break;
			}
			cout << "������Ҫ��ѯ������" << endl;
			num = cinNumber();
			if (BST_search(t, num)) {
				cout << "��������" << endl;
			}
			else
			{
				cout << "����������" << endl;
			}
			break;

		case 5:
			if (!BST_preorderI(t, visit)) {
				cout << "����ʧ�ܣ���δ����ʼ����Ϊ��" << endl;
			}
			break;
		case 6:
			if (!t || !BST_preorderR(t->root,visit)) {
				cout << "����ʧ�ܣ���δ����ʼ����Ϊ��" << endl;
			}
			break;

		case 7:
			if (!BST_inorderI(t, visit)) {
				cout << "����ʧ�ܣ���δ����ʼ����Ϊ��" << endl;
			}
			break;
		case 8:
			if (!t || !BST_inorderR(t->root, visit)) {
				cout << "����ʧ�ܣ���δ����ʼ����Ϊ��" << endl;
			}
			break;
		case 9:
			if (!BST_postorderI(t, visit)) {
				cout << "����ʧ�ܣ���δ����ʼ����Ϊ��" << endl;
			}
			break;
		case 10:
			if (!t || !BST_postorderR(t->root, visit)) {
				cout << "����ʧ�ܣ���δ����ʼ����Ϊ��" << endl;
			}
			break;
		case 11:
			if (!BST_levelOrder(t, visit)) {
				cout << "����ʧ�ܣ���δ����ʼ����Ϊ��" << endl;
			}
		default:
			break;
		}

	}

	return 0;
}