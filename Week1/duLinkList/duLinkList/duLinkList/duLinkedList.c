#include "duLinkedList.h"
#include <stdlib.h>
#include <stdio.h>

Status InitList_DuL(DuLinkedList* L)
{
	//malloc DuLinkedList����
	DuLinkedList list = (DuLinkedList)malloc(sizeof(DuLNode));
	if (list == NULL)
	{
		return ERROR;
	}
	list->next = NULL;
	list->prior = NULL;
	*L = list;
	return SUCCESS;
}

//����ɾ��
void DestroyList_DuL(DuLinkedList* L)
{
	DuLNode* tem = *L;
	while (*L != NULL) {
		tem = *L;
		*L = (*L)->next;
		free(tem);
	}
}

Status InsertBeforeList_DuL(DuLNode* p, DuLNode* q)
{
	//���ֻ��һ��Ԫ����ֱ�Ӳ嵽ǰ��
	if (p->next == NULL)
	{
		p->next = q;
		q->prior = p;
	}else{
		DuLNode* temp= p->next;
		p->next = q;
		q->next = temp;
		temp->prior = q;
		q->prior = p;
	}
	return SUCCESS;
}

Status InsertAfterList_DuL(DuLNode* p, DuLNode* q)
{
	DuLNode* cur = p;
	//������������
	while (cur->next != NULL)
	{
		cur = cur->next;
	}
	cur->next = q;
	q->prior = cur;
	return SUCCESS;
}

Status DeleteList_DuL(DuLNode* p, ElemType* e)
{
	//���������ҵ�Ԫ�ز�ɾ�������������ĩ�˻�δ�ҵ��򷵻�ERROR
	DuLNode* cur = p->next;
	while (cur) {
		if (cur->data != e) {
			cur = cur->next;
		}
		else {
			if (cur->next == NULL) {
				cur->prior->next = cur->next;
			}else {
				cur->prior->next = cur->next;
				cur->next->prior = cur->prior;
			}
			free(cur);
			return SUCCESS;
		}
	}
	return ERROR;
}

void TraverseList_DuL(DuLinkedList L, void(*visit)(ElemType e))
{
	if (L == NULL) {
		printf("����Ϊ��");
	}
	DuLNode* cur = L->next;
	while (cur) {
		(*visit)(cur->data);
		cur = cur->next;
	}
	printf("\n");
}

