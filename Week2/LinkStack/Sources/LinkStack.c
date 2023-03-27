#include "LinkStack.h"
#include <stdio.h>
#include <stdlib.h>


//链栈

//初始化栈
Status initLStack(LinkStack** s) {
	//如果s为空指针，先malloc出s再初始化
	*s = (LinkStack*)malloc(sizeof(LinkStack));
	if (*s == NULL) {
		return ERROR;
	}
	
	(*s)->count = 0;
	(*s)->top = NULL;

	return SUCCESS;
}

//判断栈是否为空
Status isEmptyLStack(LinkStack** s) {
	//如果s是空或者s所指头指针是空，则返回SUCCESS
	if (*s == NULL || (*s)->count == 0) {
		return SUCCESS;
	}
	return ERROR;

}

//得到栈顶元素
Status getTopLStack(LinkStack** s, ElemType* e) {
	//先判断s是否为空再取值
	if (*s == NULL || (*s)->top == NULL) {
		return ERROR;
	}
	*e = (*s)->top->data;
	return SUCCESS;

}

//清空栈
Status clearLStack(LinkStack** s) {
	//如果s是空返回ERROR，如果不为空遍历删除
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

//销毁栈
Status destroyLStack(LinkStack** s) {
	//调用函数清空再删除
	if (clearLStack(s)==ERROR) {
		return ERROR;
	}
	free(*s);
	*s = NULL;
	return SUCCESS;
}

//检测栈长度
Status LStackLength(LinkStack** s, int* length) {
	//判断s是否为空，不为空赋值
	if (*s == NULL) {
		return ERROR;
	}
	*length = (*s)->count;
	return SUCCESS;
}

//入栈
Status pushLStack(LinkStack** s, ElemType data) {
	//判断是否为空，不为空封装为节点赋值
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

//出栈
Status popLStack(LinkStack** s, ElemType* data) {
	//如果s为空或没有数据直接返回ERROR，否则为data赋值返回SUCCESS
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