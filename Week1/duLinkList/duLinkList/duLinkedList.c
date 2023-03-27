#include "duLinkedList.h"
#include <stdlib.h>
#include <stdio.h>

Status InitList_DuL(DuLinkedList* L)
{
	//malloc DuLinkedList返回
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

//遍历删除
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
	//如果只有一个元素则直接插到前面
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
	//遍历到最后插入
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
	//遍历链表找到元素并删除，如果到链表末端还未找到则返回ERROR
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
		printf("链表为空");
	}
	DuLNode* cur = L->next;
	while (cur) {
		(*visit)(cur->data);
		cur = cur->next;
	}
	printf("\n");
}

