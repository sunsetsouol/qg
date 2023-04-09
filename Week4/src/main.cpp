#include"binary_sort_tree.h"
#include<iostream>
using namespace std;


void show() {
	cout << "排序树" << endl;;
	cout << "1.初始化排序树" << endl;
	cout << "2.插入数据" << endl;
	cout << "3.删除数据）" << endl;
	cout << "4.查找数据" << endl;
	cout << "5.先序遍历非递归" << endl;
	cout << "6.先序遍历递归" << endl;
	cout << "7.中序遍历非递归" << endl;
	cout << "8.中序遍历递归" << endl;
	cout << "9.后序遍历非递归" << endl;
	cout << "10.后续遍历递归" << endl;
	cout << "11.层级遍历" << endl;
	cout << "请输入正确的操作数(1-11))：";
}

void visit(NodePtr n) {
	cout << n->value << endl;
}

//输入数字
int cinNumber() {
	char input[200];
	int num;
	char ch;
	while (1) {
	restart:

		printf("请输入数字：");
		scanf_s("%s", &input, 100);
		int i = 0;
		if (input[0] == '-') {
			i++;
		}
		while (input[i] != '\0' && input[i] != '\n')
		{
			if (input[i] > '9' || input[i] < '0') {
				cout << "输入有误，请重新输入\n";
				goto restart;
			}
			i++;
		}
		if (sscanf_s(input, "%d", &num) == 1)
			break;
		printf("输入有误，请重新输入！\n");
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
				cout << "请输入正确操作数" << endl;
			}

		} while (oper > 11 || oper < 1);

		switch (oper)
		{
		case 1:
			BST_init(&t);
			break;

		case 2:
			if (t == NULL) {
				cout << "树未被初始化" << endl;
				break;
			}
			cout << "请输入要插入的数字" << endl;
			num = cinNumber();
			if (BST_insert(t, num)) {
				cout << "插入成功" << endl;
			}
			else
			{
				cout << "插入失败，树未初始化或数字已存在" << endl;
			}
			break;

		case 3:
			if (t == NULL) {
				cout << "树未被初始化" << endl;
				break;
			}
			cout << "请输入要删除的数字" << endl;
			num = cinNumber();
			if (BST_delete(t, num)) {
				cout << "删除成功" << endl;
			}
			else
			{
				cout << "删除失败，树不存在或数不存在" << endl;
			}
			break;

		case 4:
			if (t == NULL) {
				cout << "树未被初始化" << endl;
				break;
			}
			cout << "请输入要查询的数字" << endl;
			num = cinNumber();
			if (BST_search(t, num)) {
				cout << "该数存在" << endl;
			}
			else
			{
				cout << "该数不存在" << endl;
			}
			break;

		case 5:
			if (!BST_preorderI(t, visit)) {
				cout << "遍历失败，树未被初始化或为空" << endl;
			}
			break;
		case 6:
			if (!t || !BST_preorderR(t->root,visit)) {
				cout << "遍历失败，树未被初始化或为空" << endl;
			}
			break;

		case 7:
			if (!BST_inorderI(t, visit)) {
				cout << "遍历失败，树未被初始化或为空" << endl;
			}
			break;
		case 8:
			if (!t || !BST_inorderR(t->root, visit)) {
				cout << "遍历失败，树未被初始化或为空" << endl;
			}
			break;
		case 9:
			if (!BST_postorderI(t, visit)) {
				cout << "遍历失败，树未被初始化或为空" << endl;
			}
			break;
		case 10:
			if (!t || !BST_postorderR(t->root, visit)) {
				cout << "遍历失败，树未被初始化或为空" << endl;
			}
			break;
		case 11:
			if (!BST_levelOrder(t, visit)) {
				cout << "遍历失败，树未被初始化或为空" << endl;
			}
		default:
			break;
		}

	}

	return 0;
}