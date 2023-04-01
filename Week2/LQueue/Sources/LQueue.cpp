#include "LQueue.h" 
#include <stdlib.h>
#include<stdio.h>

/**
 *  @name        : void InitLQueue(LQueue *Q)
 *    @description : ��ʼ������
 *    @param         Q ����ָ��Q
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
 *    @description : ���ٶ���
 *    @param         Q ����ָ��Q
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
 *    @description : �������Ƿ�Ϊ��
 *    @param         Q ����ָ��Q
 *    @return         : ��-TRUE; δ��-FALSE
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
 *    @description : �鿴��ͷԪ��
 *    @param         Q e ����ָ��Q,��������ָ��e
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : �����Ƿ��
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
 *    @description : ȷ�����г���
 *    @param         Q ����ָ��Q
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
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
 *    @description : ��Ӳ���
 *    @param         Q ����ָ��Q,�������ָ��data
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : �����Ƿ�Ϊ��
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
 *    @description : ���Ӳ���
 *    @param         Q ����ָ��Q
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
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
 *    @description : ��ն���
 *    @param         Q ����ָ��Q
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
 *    @description : ������������
 *    @param         Q ����ָ��Q����������ָ��foo
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
 *    @description : ��������
 *    @param         q ָ��q

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

