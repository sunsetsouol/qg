#include "linkedList.h"
#include <stdlib.h>
#include <stdio.h>

Status InitList(LinkedList* L) {
	//malloc LinkedList����ֵ��L
	LinkedList list = (LinkedList)malloc(sizeof(LNode));
	if (list==NULL)
	{
		return ERROR;
	}
	list->next = NULL;
	*L = list;
	return SUCCESS;
}

//��������ɾ��
void DestroyList(LinkedList* L)
{
	LNode* tem ;
	while (*L != NULL) {
		tem = *L;
		*L = (*L)->next;
		free(tem);
	}
}

Status InsertList(LNode* p, ElemType data)
{
	//���p Ϊ�գ����ش���
	if (p == NULL) {
		printf("���ܲ��뵽������");
		return ERROR;
	}
	//����q�����qΪNULL����
	LinkedList q = (LinkedList)malloc(sizeof(LNode));
	if (q == NULL) {
		printf("�ڴ����ʧ��");
		return ERROR;
	}
	//��ֵ���������
	q->data = data;
	q->next = NULL;
	LinkedList cur = p;
	while (cur->next) {
		cur = cur->next;
	}
	cur->next = q;
	return SUCCESS;
}

Status DeleteList(LNode* p, ElemType* e)
{
	//��������ǿ�ֱ�ӷ���ERROR
	if (p==NULL)
	{
		return ERROR;
	}
	LNode* pre = p;
	LNode* cur = p->next;
	//��������,����ҵ��򽫽ڵ�ɾ�������������ɻ�û�ҵ�����ERROR
	while (cur) {
		if (cur->data==e)
		{
			pre->next = cur->next;
			free(cur);
			return SUCCESS;
		}
		else
		{
			pre = cur;
			cur = cur->next;
		}
	}
	return ERROR;
}

void TraverseList(LinkedList L, void(*visit)(ElemType e))
{
	//�������
	LNode* tem = L->next;
	while (tem) {
		(*visit)(tem->data);
		tem = tem->next;
	}
	printf("\n");
}

Status SearchList(LinkedList L, ElemType e)
{
	//������������ҵ�e����SUCCESS
	LNode* tem = L->next;
	while (tem) {
		if (tem->data == e) {
			return SUCCESS;
		}
		tem = tem->next;
	}
	return ERROR;
}

//��ת
Status ReverseList(LinkedList* L)
{
	//�������ֻ��һ���ڵ㷵��SUCCESS OR ERROR 
	if ((*L) == NULL || (*L)->next == NULL) {
		return SUCCESS;
	}
	//�������ڵ�������з�ת
	LNode* pre = NULL;
	LNode* cur = (*L)->next;
	LNode* aft ;
	while (cur) {
		aft = cur->next;
		cur->next = pre;
		pre = cur;
		cur = aft;
	}
	(*L)->next = pre;
	return SUCCESS;
}

Status IsLoopList(LinkedList L)
{
	//�������Ϊ��ֱ�ӷ���ERROR
	if (L == NULL || L->next == NULL) {
		return ERROR;
	}
	//����ָ������������Ϊ���Ҷ���ָ��ͳһ�ڵ��򷵻�SUCCESS
	LinkedList fast = L;
	LinkedList slow = L;
	while (fast && fast->next && slow) {
		fast = fast->next->next;
		slow = slow->next;
		if (fast != NULL && fast == slow) {
			return SUCCESS;
		}
	}
	return ERROR;
}
//��ż����
LNode* ReverseEvenList(LinkedList* L)
{
	//�������Ϊ�ջ���ֻ��һ���ڵ㣬ֱ�ӷ���
	if (*L == NULL || (*L)->next == NULL || (*L)->next->next == NULL) {
		return *L;
	}
	//������������
	LinkedList p1 = (*L)->next;
	LinkedList p2 = (*L)->next->next;
	p1->next = p2->next;
	p2->next = p1;
	(*L)->next = p2;
	p2 = p1->next;
	while (p2 && p2->next) {
		p1->next = p2->next;
		p2->next = p2->next->next;
		p1->next->next = p2;
		p1 = p2;
		p2 = p1->next;
	}

	return *L;
}

LNode* FindMidNode(LinkedList* L)
{
	//�������Ϊ��ֱ�ӷ���
	if (*L == NULL || (*L)->next==NULL) {
		return *L;
	}
	//����ָ�룬��ָ�뵽��βʱ��ָ�����õ��е�
	LinkedList fast = (*L);
	LinkedList slow = (*L);
	while (fast && fast->next) {
		fast = fast->next->next;
		slow = slow->next;
	}
	return slow;
}

