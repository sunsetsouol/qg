#include "duLinkedList.h"
#include <stdio.h>
#include <stdlib.h>

//�����������
void* visit(ElemType e) {
	printf("%d\t", e);
}

//����̨����չʾ
void operator()
{
	printf("\n\n\n˫����ADT\n\n");
	printf("1.����������\n");
	printf("2.��������\n");
	printf("3.����ͷ��������\n");
	printf("4.����β��������\n");
	printf("5.ɾ������\n");
	printf("6.�����������\n");
	printf("7.�˳�\n");
	printf("\n�������Ӧ������(1-7)��");
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
	int oper = 0;
	int num;
	DuLinkedList head = NULL;
	do {
		operator();
		printf("�����������");
		oper = cinNumber();

		switch (oper)
		{
		case 1:
			//����������
			if (InitList_DuL(&head))
			{
				printf("���������ɹ�\n");
			}
			else
			{
				printf("��������ʧ��\n");
			}
			break;
		case 2:
			//ɾ������
			if (head == NULL) {
				printf("����������");
			}
			else
			{
				DestroyList_DuL(&head);
				printf("�����������\n");
			}
			break;
		case 3:
			//����ͷ��������
			if (head == NULL)
			{
				printf("����δ���������ȴ�������\n");
			}
			else
			{
				DuLinkedList newNode;
				if (InitList_DuL(&newNode))
				{
					printf("���������ݣ�");
					newNode->data = cinNumber();
					if (InsertBeforeList_DuL(head, newNode))
					{
						printf("���ݲ���ɹ�\n");
					}
					else
					{
						printf("���ݲ���ʧ��\n");
					}
				}
				else
				{
					printf("���ݲ���ʧ��\n");
				}
			}
			break;
		case 4:
			//����β��������
			if (head == NULL)
			{
				printf("����δ���������ȴ�������\n");
			}
			else
			{
				DuLinkedList newNode;
				if (InitList_DuL(&newNode))
				{
					printf("���������ݣ�");
					newNode->data = cinNumber();
					if (InsertAfterList_DuL(head, newNode))
					{
						printf("���ݲ���ɹ�\n");
					}
					else
					{
						printf("���ݲ���ʧ��\n");
					}
				}
				else
				{
					printf("���ݲ���ʧ��\n");
				}
			}
			break;
		case 5:
			//ɾ������
			if (head == NULL)
			{
				printf("����δ���������ȴ�������\n");
			}
			else
			{
				printf("����ɾ���ĸ����ݣ�");
				num = cinNumber();
				if (DeleteList_DuL(head, num))
				{
					printf("����ɾ���ɹ�\n");
				}
				else
				{
					printf("����ɾ��ʧ��\n");
				}
			}
			break;
		case 6:
			//�������
			if (head == NULL || head->next == NULL)
			{
				printf("�������ڻ���ֻ����һ���յ�ͷ���\n");
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