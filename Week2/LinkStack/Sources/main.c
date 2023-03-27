#include "LinkStack.h"
#include<stdlib.h>
#include<stdio.h>

void operator() {
	printf("\n\n\n链栈\n\n");
	printf("1.初始化栈\n");
	printf("2.判断栈是否为空\n");
	printf("3.得到栈顶元素\n");
	printf("4.清空栈\n");
	printf("5.销毁栈\n");
	printf("6.检测栈长度\n");
	printf("7.入栈\n");
	printf("8.出栈\n");
	printf("9.退出并删除栈\n");
	printf("\n请输入对应的数字(1-9)：");
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
	//操作数
	int oper = 0;
	int* num = (int*)malloc(sizeof(int));
	LinkStack* s = NULL;
	do {
		operator();
		oper = cinNumber();

		switch (oper)
		{
		case 1:
			// 初始化栈
			if (initLStack(&s)) {
				printf("初始化成功");
			}
			else
			{
				printf("初始化失败");
			}
			break;
		case 2:
			//判断栈是否为空
			if (isEmptyLStack(&s))
			{
				printf("栈为空");
			}
			else
			{
				printf("栈非空");
			}
			break;
		case 3:
			//得到栈顶元素
			if (getTopLStack(&s, num))
			{
				printf("%d", *num);
			}
			else
			{
				printf("获取失败，栈可能不存在或为空");
			}
			break;
		case 4:
			//清空栈
			if (clearLStack(&s)) {
				printf("清空成功");
			}
			else
			{
				printf("清空失败，栈不存在");
			}
			break;
		case 5:
			//销毁栈
			if (destroyLStack(&s)) {
				printf("销毁成功");
			}
			else
			{
				printf("销毁失败，不存在栈");
			}
			break;
		case 6:
			//检测栈长度
			if (LStackLength(&s, num)) {
				printf("%d",*num);
			}
			else
			{
				printf("获取失败，栈不存在");
			}
			break;
		case 7:
			//入栈
			printf("输入数字");
			*num = cinNumber();
			if (pushLStack(&s, *num)) {
				printf("入栈成功");
			}
			else
			{
				printf("入栈失败，栈不存在");
			}
			break;
		case 8:
			//出栈
			if (popLStack(&s, num)) {
				printf("出栈成功，出栈元素为：%d", *num);
			}
			else
			{
				printf("出栈失败");
			}
			break;
		case 9:
			destroyLStack(&s);
			exit(0);

		default:
			printf("请输入正确操作数");
			break;
		}

	} while (oper != 9);
	return 0;


}