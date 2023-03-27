#include "LQueue.h" 
#include <stdlib.h>
#include<stdio.h>

/**
 *  @name        : void InitLQueue(LQueue *Q)
 *    @description : 初始化队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void InitLQueue(LQueue* Q) {
	if (Q == nullptr) {
		Q = new LQueue ;
	}
	Q->front = nullptr;
	Q->length = 0;
	Q->rear = nullptr;
}

/**
 *  @name        : void DestoryLQueue(LQueue *Q)
 *    @description : 销毁队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void DestoryLQueue(LQueue* Q) {
	if (Q) {
		ClearLQueue(Q);
		free(Q);
	}
}

/**
 *  @name        : Status IsEmptyLQueue(const LQueue *Q)
 *    @description : 检查队列是否为空
 *    @param         Q 队列指针Q
 *    @return         : 空-TRUE; 未空-FALSE
 *  @notice      : None
 */
Status IsEmptyLQueue(const LQueue* Q) {
	if (!Q || Q->length == 0) {
		return TRUE;
	}
	return FALSE;
}

/**
 *  @name        : Status GetHeadLQueue(LQueue *Q, void *e)
 *    @description : 查看队头元素
 *    @param         Q e 队列指针Q,返回数据指针e
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : 队列是否空
 */
Status GetHeadLQueue(LQueue* Q, void** e) {
	if (!Q || Q->length == 0) {
		return FALSE;
	}
	*e =Q->front->data;
	return TRUE;
}

/**
 *  @name        : int LengthLQueue(LQueue *Q)
 *    @description : 确定队列长度
 *    @param         Q 队列指针Q
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : None
 */
Status LengthLQueue(LQueue* Q, int* num) {
	if (!Q) {
		return FALSE;
	}
	*num = Q->length;
	return TRUE;
}

/**
 *  @name        : Status EnLQueue(LQueue *Q, void *data)
 *    @description : 入队操作
 *    @param         Q 队列指针Q,入队数据指针data
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : 队列是否为空
 */
Status EnLQueue(LQueue* Q, void* data) {
	Node* n = new node;
	if (n == nullptr) {
		return FALSE;
	}
	n->next = nullptr;
	n->data = data;
	if (Q->length == 0) {
		Q->front = n;
		Q->length = 1;
		Q->rear = n;
	}
	else
	{
		Q->rear->next = n;
		Q->rear = n;
		Q->length++;
	}
	return TRUE;
}

/**
 *  @name        : Status DeLQueue(LQueue *Q)
 *    @description : 出队操作
 *    @param         Q 队列指针Q
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : None
 */
Status DeLQueue(LQueue* Q) {
	if (!Q->front) {
		return FALSE;
	}
	node* n = Q->front;
	Q->front = n->next;
	free(n);
	Q->length--;
	return TRUE;
}

/**
 *  @name        : void ClearLQueue(AQueue *Q)
 *    @description : 清空队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void ClearLQueue(LQueue* Q) {
	if (Q) {
		while (Q->front)
		{
			node* n = Q->front;
			Q->front = n->next;
			free(n);
		}
	}
}

/**
 *  @name        : Status TraverseLQueue(const LQueue *Q, void (*foo)(void *q))
 *    @description : 遍历函数操作
 *    @param         Q 队列指针Q，操作函数指针foo
 *    @return         : None
 *  @notice      : None
 */
Status TraverseLQueue(const LQueue* Q, void (*foo)(void* q)) {
	if (!Q) {
		return FALSE;
	}
	Node* n = Q->front;
	while (n) {
		foo(n->data);
		n = n->next;
	}
	return TRUE;
}

/**
 *  @name        : void LPrint(void *q)
 *    @description : 操作函数
 *    @param         q 指针q

 *  @notice      : None
 */
void LPrint(void* q) {
	if (type == 'i') {
		printf("%d", *(int*)q);
	}
	if (type == 'f') {
		printf("%f", *(float*)q);
	}
	if (type == 'c') {
		printf("%c", *(char*)q);
	}
}

/**************************************************************
 *    End-Multi-Include-Prevent Section
 **************************************************************/
// LQUEUE_H_INCLUDED

