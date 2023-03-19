#include "linkedList.h"
#include <stdlib.h>
#include <stdio.h>

Status InitList(LinkedList* L) {
	//malloc LinkedList并赋值给L
	LinkedList list = (LinkedList)malloc(sizeof(LNode));
	if (list==NULL)
	{
		return ERROR;
	}
	list->next = NULL;
	*L = list;
	return SUCCESS;
}

//遍历链表删除
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
	//如果p 为空，返回错误
	if (p == NULL) {
		printf("不能插入到空链表");
		return ERROR;
	}
	//创建q，如果q为NULL报错
	LinkedList q = (LinkedList)malloc(sizeof(LNode));
	if (q == NULL) {
		printf("内存分配失败");
		return ERROR;
	}
	//赋值并遍历添加
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
	//如果链表是空直接返回ERROR
	if (p==NULL)
	{
		return ERROR;
	}
	LNode* pre = p;
	LNode* cur = p->next;
	//遍历查找,如果找到则将节点删除，如果遍历完成还没找到返回ERROR
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
	//遍历输出
	LNode* tem = L->next;
	while (tem) {
		(*visit)(tem->data);
		tem = tem->next;
	}
	printf("\n");
}

Status SearchList(LinkedList L, ElemType e)
{
	//遍历链表，如果找到e返回SUCCESS
	LNode* tem = L->next;
	while (tem) {
		if (tem->data == e) {
			return SUCCESS;
		}
		tem = tem->next;
	}
	return ERROR;
}

//反转
Status ReverseList(LinkedList* L)
{
	//空链表或只有一个节点返回SUCCESS OR ERROR 
	if ((*L) == NULL || (*L)->next == NULL) {
		return SUCCESS;
	}
	//用三个节点迭代进行反转
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
	//如果链表为空直接返回ERROR
	if (L == NULL || L->next == NULL) {
		return ERROR;
	}
	//快慢指针遍历，如果不为空且二者指向统一节点则返回SUCCESS
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
//奇偶调换
LNode* ReverseEvenList(LinkedList* L)
{
	//如果链表为空或者只有一个节点，直接返回
	if (*L == NULL || (*L)->next == NULL || (*L)->next->next == NULL) {
		return *L;
	}
	//迭代两两交换
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
	//如果链表为空直接返回
	if (*L == NULL || (*L)->next==NULL) {
		return *L;
	}
	//快慢指针，快指针到结尾时满指针正好到中点
	LinkedList fast = (*L);
	LinkedList slow = (*L);
	while (fast && fast->next) {
		fast = fast->next->next;
		slow = slow->next;
	}
	return slow;
}

