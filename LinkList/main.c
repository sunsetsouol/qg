#include "linkedList.h"
#include <stdio.h>
#include <stdlib.h>

//travel�Ĳιۺ���
void* visit(ElemType e) {
	printf("%d\t", e);
}

//�������沢���û�������ȷ������
void operator() {
	printf("\n\n\n������ADT\n\n");
	printf("1.����������\n");
	printf("2.��������\n");
	printf("3.������\n");
	printf("4.ɾ�����\n");
	printf("5.�������\n");
	printf("6.�����Ƿ��������\n");
	printf("7.��ת����\n");
	printf("8.�ж������Ƿ�ɻ�\n");
	printf("9.��ż����\n");
	printf("10.�����м���\n");
	printf("11.�˳���ɾ������\n");
	printf("\n�������Ӧ������(1-11)��");
}
//��������
int cinNumber() {
	char temdata[100];
	int data = 0;

	int i = 0;
	//���������������������룬�������û�ж�Ӧ���������������룬������ȷ������
	scanf_s("%s", &temdata, 100);
	while (1)
	{here:
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
	int oper= 0;
	int num;
	LinkedList head = NULL;
	do {
		operator();
		printf("�����������");
		oper = cinNumber();

		switch (oper)
		{
		case 1:
			//����������
			if (InitList(&head)) {
				printf("�������ɹ�");
			}
			else
			{
				printf("������ʧ��");
			}
			break;
		case 2:
			//ɾ������
			if (head == NULL) {
				printf("����������");
			}
			else
			{
				DestroyList(&head);
				printf("�����������\n");
			}
			break;
		case 3:
			//��������
			if (head == NULL) {
				printf("���ȴ�������");
			}
			else
			{
				printf("����������");
				num = cinNumber();
				if (InsertList(head, num))
				{
					printf("���ݲ���ɹ�\n");
				}
				else
				{
					printf("���ݲ���ʧ��\n");
				}
			}
			break;
		case 4:
			//ɾ������
			if (head == NULL || head->next == NULL)
			{
				printf("�������ڻ���ֻ����һ���յ�ͷ���\n");
			}
			else
			{
				printf("����ɾ���ĸ����ݣ�");
				num = cinNumber();
				if (DeleteList(head, num))
				{
					printf("����ɾ���ɹ�\n");
				}
				else
				{
					printf("����ɾ��ʧ��\n");
				}
			}
			break;
		case 5:
			//�������
			if (head == NULL || head->next == NULL)
			{
				printf("�������ڻ���ֻ����һ���յ�ͷ���\n");
			}
			else
			{
				TraverseList(head, visit);
			}
			break;
		case 6:
			//�����Ƿ����
			if (head == NULL || head->next == NULL)
			{
				printf("�������ڻ���ֻ����һ���յ�ͷ���\n");
			}
			else
			{
				printf("��������Ҫ���ҵ����ݣ�");
				num = cinNumber();
				if (SearchList(head, num))
				{
					printf("��������\n");
				}
				else
				{
					printf("����������\n");
				}
			}
			
			break;
		case 7:
			//��ת
			if (head == NULL || head->next == NULL)
			{
				printf("����Ϊ�ջ�������ֻ�����������\n");
			}
			else
			{
				if (ReverseList(&head))
				{
					printf("��ת�ɹ�\n");
				}
				else
				{
					printf("��תʧ��\n");
				}

			}
			break;
		case 8:
			//�ж��Ƿ�ɻ�
			if (head == NULL || head->next == NULL)
			{
				printf("����Ϊ��\n");
			}
			else
			{
				if (IsLoopList(head))
				{
					printf("����ɻ�\n");
				}
				else
				{
					printf("����û�гɻ�\n");
				}
			}
			break;
		case 9:
			//��ż����
			if (head == NULL || head->next == NULL)
			{
				printf("���ǿ�����\n");
			}
			else {
				ReverseEvenList(&head);
			}
			break;
		case 10:
			//�м�ֵ
			if (head == NULL || head->next == NULL)
			{
				printf("���ǿ�����\n");
			}
			else
			{
				printf("�����м��ֵΪ%d\n", (FindMidNode(&head))->data);
			}
			break;
		case 11:
			//�˳�
			DestroyList(&head);
			exit(0);
		default:
			printf("��������ȷ������");
			break;
		}
	} while (oper != 11);
	
	return 0;


}
