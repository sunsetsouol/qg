#include "linkedList.h"
#include <stdio.h>
#include <stdlib.h>

//travel的参观函数
void* visit(ElemType e) {
	printf("%d\t", e);
}

//操作界面并让用户输入正确操作数
void operator() {
	printf("\n\n\n单链表ADT\n\n");
	printf("1.创建空链表\n");
	printf("2.销毁链表\n");
	printf("3.插入结点\n");
	printf("4.删除结点\n");
	printf("5.输出链表\n");
	printf("6.查找是否存在数据\n");
	printf("7.反转链表\n");
	printf("8.判断链表是否成环\n");
	printf("9.奇偶调换\n");
	printf("10.查找中间结点\n");
	printf("11.退出并删除链表\n");
	printf("\n请输入对应的数字(1-11)：");
}
//输入数字
int cinNumber() {
	char temdata[100];
	int data = 0;

	int i = 0;
	//如果输出非数字则重新输入，如果数字没有对应操作数则重新输入，返回正确操作数
	scanf_s("%s", &temdata, 100);
	while (1)
	{here:
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
	int oper= 0;
	int num;
	LinkedList head = NULL;
	do {
		operator();
		printf("请输入操作数");
		oper = cinNumber();

		switch (oper)
		{
		case 1:
			//创建空链表
			if (InitList(&head)) {
				printf("链表创建成功");
			}
			else
			{
				printf("链表创建失败");
			}
			break;
		case 2:
			//删除链表
			if (head == NULL) {
				printf("不存在链表");
			}
			else
			{
				DestroyList(&head);
				printf("链表销毁完成\n");
			}
			break;
		case 3:
			//插入数据
			if (head == NULL) {
				printf("请先创建链表");
			}
			else
			{
				printf("请输入数据");
				num = cinNumber();
				if (InsertList(head, num))
				{
					printf("数据插入成功\n");
				}
				else
				{
					printf("数据插入失败\n");
				}
			}
			break;
		case 4:
			//删除数据
			if (head == NULL || head->next == NULL)
			{
				printf("链表不存在或者只存在一个空的头结点\n");
			}
			else
			{
				printf("你想删除哪个数据：");
				num = cinNumber();
				if (DeleteList(head, num))
				{
					printf("数据删除成功\n");
				}
				else
				{
					printf("数据删除失败\n");
				}
			}
			break;
		case 5:
			//遍历输出
			if (head == NULL || head->next == NULL)
			{
				printf("链表不存在或者只存在一个空的头结点\n");
			}
			else
			{
				TraverseList(head, visit);
			}
			break;
		case 6:
			//查找是否存在
			if (head == NULL || head->next == NULL)
			{
				printf("链表不存在或者只存在一个空的头结点\n");
			}
			else
			{
				printf("请输入你要查找的数据：");
				num = cinNumber();
				if (SearchList(head, num))
				{
					printf("该数存在\n");
				}
				else
				{
					printf("该数不存在\n");
				}
			}
			
			break;
		case 7:
			//反转
			if (head == NULL || head->next == NULL)
			{
				printf("链表为空或者链表只含有两个结点\n");
			}
			else
			{
				if (ReverseList(&head))
				{
					printf("反转成功\n");
				}
				else
				{
					printf("反转失败\n");
				}

			}
			break;
		case 8:
			//判断是否成环
			if (head == NULL || head->next == NULL)
			{
				printf("链表为空\n");
			}
			else
			{
				if (IsLoopList(head))
				{
					printf("链表成环\n");
				}
				else
				{
					printf("链表没有成环\n");
				}
			}
			break;
		case 9:
			//奇偶调换
			if (head == NULL || head->next == NULL)
			{
				printf("这是空链表\n");
			}
			else {
				ReverseEvenList(&head);
			}
			break;
		case 10:
			//中间值
			if (head == NULL || head->next == NULL)
			{
				printf("这是空链表\n");
			}
			else
			{
				printf("链表中间的值为%d\n", (FindMidNode(&head))->data);
			}
			break;
		case 11:
			//退出
			DestroyList(&head);
			exit(0);
		default:
			printf("请输入正确操作数");
			break;
		}
	} while (oper != 11);
	
	return 0;


}
