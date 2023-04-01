#include "LinkStack.h"
#include <stdio.h>
#include <stdlib.h>


//��ջ

//��ʼ��ջ
Status initLStack(LinkStack** s) {
	//���sΪ��ָ�룬��malloc��s�ٳ�ʼ��
	*s = (LinkStack*)malloc(sizeof(LinkStack));
	if (*s == NULL) {
		return ERROR;
	}
	
	(*s)->count = 0;
	(*s)->top = NULL;

	return SUCCESS;
}

//�ж�ջ�Ƿ�Ϊ��
Status isEmptyLStack(LinkStack** s) {
	//���s�ǿջ���s��ָͷָ���ǿգ��򷵻�SUCCESS
	if (*s == NULL || (*s)->count == 0) {
		return SUCCESS;
	}
	return ERROR;

}

//�õ�ջ��Ԫ��
Status getTopLStack(LinkStack** s, ElemType* e) {
	//���ж�s�Ƿ�Ϊ����ȡֵ
	if (*s == NULL || (*s)->top == NULL) {
		return ERROR;
	}
	*e = (*s)->top->data;
	return SUCCESS;

}

//���ջ
Status clearLStack(LinkStack** s) {
	//���s�ǿշ���ERROR�������Ϊ�ձ���ɾ��
	if (*s == NULL) {
		return ERROR;
	}
	while ((*s)->top)
	{
		LinkStackPtr l = (*s)->top;
		(*s)->top = l->next;
		free(l);
	}
	return SUCCESS;

}

//����ջ
Status destroyLStack(LinkStack** s) {
	//���ú��������ɾ��
	if (clearLStack(s)==ERROR) {
		return ERROR;
	}
	free(*s);
	*s = NULL;
	return SUCCESS;
}

//���ջ����
Status LStackLength(LinkStack** s, int* length) {
	//�ж�s�Ƿ�Ϊ�գ���Ϊ�ո�ֵ
	if (*s == NULL) {
		return ERROR;
	}
	*length = (*s)->count;
	return SUCCESS;
}

//��ջ
Status pushLStack(LinkStack** s, ElemType data) {
	//�ж��Ƿ�Ϊ�գ���Ϊ�շ�װΪ�ڵ㸳ֵ
	if (*s == NULL) {
		return ERROR;
	}
	LinkStackPtr l = (LinkStackPtr)malloc(sizeof(StackNode));
	if (l == NULL) {
		return ERROR;
	}
	l->data = data;
	l->next = (*s)->top;
	(*s)->top = l;
	(*s)->count++;
	return SUCCESS;
}

//��ջ
Status popLStack(LinkStack** s, ElemType* data) {
	//���sΪ�ջ�û������ֱ�ӷ���ERROR������Ϊdata��ֵ����SUCCESS
	if (*s == NULL || (*s)->count == 0) {
		return ERROR;
	}
	LinkStackPtr l = (*s)->top;
	*data = l->data;
	(*s)->top = l->next;
	free(l);
	(*s)->count--;
	return SUCCESS;
}