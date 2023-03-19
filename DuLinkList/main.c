#include "duLinkedList.h"
#include <stdio.h>
#include <stdlib.h>

//遍历输出函数
void* visit(ElemType e) {
	printf("%d\t", e);
}

//控制台操作展示
void operator()
{
	printf("\n\n\n双链表ADT\n\n");
	printf("1.创建空链表\n");
	printf("2.销毁链表\n");
	printf("3.链表头插入数据\n");
	printf("4.链表尾插入数据\n");
	printf("5.删除数据\n");
	printf("6.遍历输出链表\n");
	printf("7.退出\n");
	printf("\n请输入对应的数字(1-7)：");
}

//输入数字
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



int main() {
	int oper = 0;
	int num;
	DuLinkedList head = NULL;
	do {
		operator();
		printf("请输入操作数");
		oper = cinNumber();

		switch (oper)
		{
		case 1:
			//创建空链表
			if (InitList_DuL(&head))
			{
				printf("空链表创建成功\n");
			}
			else
			{
				printf("空链表创建失败\n");
			}
			break;
		case 2:
			//删除链表
			if (head == NULL) {
				printf("不存在链表");
			}
			else
			{
				DestroyList_DuL(&head);
				printf("链表销毁完成\n");
			}
			break;
		case 3:
			//链表头插入数据
			if (head == NULL)
			{
				printf("链表未创建，请先创建链表\n");
			}
			else
			{
				DuLinkedList newNode;
				if (InitList_DuL(&newNode))
				{
					printf("请输入数据：");
					newNode->data = cinNumber();
					if (InsertBeforeList_DuL(head, newNode))
					{
						printf("数据插入成功\n");
					}
					else
					{
						printf("数据插入失败\n");
					}
				}
				else
				{
					printf("数据插入失败\n");
				}
			}
			break;
		case 4:
			//链表尾插入数据
			if (head == NULL)
			{
				printf("链表未创建，请先创建链表\n");
			}
			else
			{
				DuLinkedList newNode;
				if (InitList_DuL(&newNode))
				{
					printf("请输入数据：");
					newNode->data = cinNumber();
					if (InsertAfterList_DuL(head, newNode))
					{
						printf("数据插入成功\n");
					}
					else
					{
						printf("数据插入失败\n");
					}
				}
				else
				{
					printf("数据插入失败\n");
				}
			}
			break;
		case 5:
			//删除数据
			if (head == NULL)
			{
				printf("链表未创建，请先创建链表\n");
			}
			else
			{
				printf("你想删除哪个数据：");
				num = cinNumber();
				if (DeleteList_DuL(head, num))
				{
					printf("数据删除成功\n");
				}
				else
				{
					printf("数据删除失败\n");
				}
			}
			break;
		case 6:
			//遍历输出
			if (head == NULL || head->next == NULL)
			{
				printf("链表不存在或者只存在一个空的头结点\n");
			}
			else
			{
				TraverseList_DuL(head, visit);
			}
			break;
		}
	} while (oper != 7);
	return 0;
}