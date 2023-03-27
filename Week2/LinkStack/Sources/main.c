#include "LinkStack.h"
#include<stdlib.h>
#include<stdio.h>

void operator() {
	printf("\n\n\n��ջ\n\n");
	printf("1.��ʼ��ջ\n");
	printf("2.�ж�ջ�Ƿ�Ϊ��\n");
	printf("3.�õ�ջ��Ԫ��\n");
	printf("4.���ջ\n");
	printf("5.����ջ\n");
	printf("6.���ջ����\n");
	printf("7.��ջ\n");
	printf("8.��ջ\n");
	printf("9.�˳���ɾ��ջ\n");
	printf("\n�������Ӧ������(1-9)��");
}

//��������
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

int main() {
	//������
	int oper = 0;
	int* num = (int*)malloc(sizeof(int));
	LinkStack* s = NULL;
	do {
		operator();
		oper = cinNumber();

		switch (oper)
		{
		case 1:
			// ��ʼ��ջ
			if (initLStack(&s)) {
				printf("��ʼ���ɹ�");
			}
			else
			{
				printf("��ʼ��ʧ��");
			}
			break;
		case 2:
			//�ж�ջ�Ƿ�Ϊ��
			if (isEmptyLStack(&s))
			{
				printf("ջΪ��");
			}
			else
			{
				printf("ջ�ǿ�");
			}
			break;
		case 3:
			//�õ�ջ��Ԫ��
			if (getTopLStack(&s, num))
			{
				printf("%d", *num);
			}
			else
			{
				printf("��ȡʧ�ܣ�ջ���ܲ����ڻ�Ϊ��");
			}
			break;
		case 4:
			//���ջ
			if (clearLStack(&s)) {
				printf("��ճɹ�");
			}
			else
			{
				printf("���ʧ�ܣ�ջ������");
			}
			break;
		case 5:
			//����ջ
			if (destroyLStack(&s)) {
				printf("���ٳɹ�");
			}
			else
			{
				printf("����ʧ�ܣ�������ջ");
			}
			break;
		case 6:
			//���ջ����
			if (LStackLength(&s, num)) {
				printf("%d",*num);
			}
			else
			{
				printf("��ȡʧ�ܣ�ջ������");
			}
			break;
		case 7:
			//��ջ
			printf("��������");
			*num = cinNumber();
			if (pushLStack(&s, *num)) {
				printf("��ջ�ɹ�");
			}
			else
			{
				printf("��ջʧ�ܣ�ջ������");
			}
			break;
		case 8:
			//��ջ
			if (popLStack(&s, num)) {
				printf("��ջ�ɹ�����ջԪ��Ϊ��%d", *num);
			}
			else
			{
				printf("��ջʧ��");
			}
			break;
		case 9:
			destroyLStack(&s);
			exit(0);

		default:
			printf("��������ȷ������");
			break;
		}

	} while (oper != 9);
	return 0;


}