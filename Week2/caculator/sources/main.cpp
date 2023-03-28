#include "stack.h"
#include<stdio.h>
#include<stdlib.h>

int priority(char s);
int main() {
	char exp[200];
	while (1) {
	restart:
		printf("请输入正确表达式\n");
		if (fgets(exp, sizeof(exp), stdin) == nullptr) {
			printf("请输入正确表达式\n");
			exit(EXIT_FAILURE);
		}
		//初始化
		LinkStack* s_num = NULL;
		LinkStack* s_opt = NULL;
		initLStack(&s_num);
		initLStack(&s_opt);
		int i = 0;
		double tem = 0;
		int mins = 0;
		double num1, num2;
		int cnt = 0;
		if (exp[0] == '-') {
			if (exp[1] == '(') {
				i++;
				pushLStack(&s_opt, '?');
			}
			else
			{
				mins = -1;
			}
			i++;
		}
		while (exp[i] != 0 && exp[i]!='\n') {
			//如果是数字
			if ((exp[i] >= '0' && exp[i] <= '9') || exp[i] == '.') {
				tem = atof(&exp[i]);
				if (mins == -1) {
					tem = -tem;
					mins = 0;
				}
				pushLStack(&s_num, tem);
				while ((exp[i] >= '0' && exp[i] <= '9') || exp[i] == '.') {
					i++;
					if (exp[i] == '.') {
						cnt++;
					}
				}
				if (cnt > 1) {
					printf("表达式有误\n");
					goto restart;
				}
				else
				{
					cnt = 0;
				}
			}
			else
			{
				//如果是符号
				if (exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/' || exp[i] == '(' || exp[i] == ')')
				{
					//如果是空直接入栈
					if (isEmptyLStack(&s_opt)) 
					{
						if (exp[i] == ')') {
							printf("输入有误");
							goto restart;
						}
						if (exp[i] == '(' && exp[i+1] == '-') {
							pushLStack(&s_opt, exp[i]);
							i++;
							mins = -1;
						}
						else
						{
							pushLStack(&s_opt, exp[i]);
						}
						i++;
					}
					//非空
					else 
					{
						//取值开算
						double top = 0;
						getTopLStack(&s_opt, &top);
						if (exp[i]==')' || (priority((char)top) >= priority(exp[i]) && (char)top!='('))
						{
							//括号消除
							popLStack(&s_opt, &top);
							if (exp[i] == ')' && (char)top == '(')
							{
								i++;
								continue;
							}
							//计算
							if (!popLStack(&s_num, &num1) || !popLStack(&s_num, &num2))
							{
								printf("表达式有误");
								goto restart;
							}
							switch ((char)top)
							{
							case '+':
								pushLStack(&s_num, num1 + num2);
								break;
							case '-':
								pushLStack(&s_num, num2 - num1);
								break;
							case '*':
								pushLStack(&s_num, num1 * num2);
								break;
							case '/':
								if (num2 == 0) {
									printf("非法计算");
									goto restart;
								}
								pushLStack(&s_num, num2 / num1);
								break;
							default:
								break;
							}
						}
						if (exp[i] == ')') {
							getTopLStack(&s_opt, &top);
							if (top == '(') {
								popLStack(&s_opt, &top);
							}
							else
							{
								pushLStack(&s_opt, exp[i]);
							}
						}
						else {
							pushLStack(&s_opt, exp[i]);
							if (exp[i] == '(' && exp[i + 1] == '-') {
								mins = -1;
								i++;
							}
						}
						i++;
					}
				}
				else {
					printf("表达式有误\n");
					goto restart;
				}
			}

		}
		double opt = 0;
		int isleap = 0;
		while(!isEmptyLStack(&s_opt)) {
			popLStack(&s_opt, &opt);
			if ((char)opt == ')') {
				isleap = 1;
				popLStack(&s_opt, &opt);
				if (opt == '(') {
					popLStack(&s_opt, &opt);
					continue;
				}
			}
			if ((char)opt == '(') {
				if (isleap) {
					popLStack(&s_opt, &opt);
					continue;
				}
				else
				{
					printf("表达式有误");
					goto restart;
				}
			}
			
			if ((!popLStack(&s_num, &num1) || !popLStack(&s_num, &num2) && s_opt->count != 0))
			{
				printf("表达式有误");
				goto restart;
			}
			if (opt) {
				switch ((char)opt)
				{
				case '+':
					pushLStack(&s_num, num1 + num2);
					break;
				case '-':
					pushLStack(&s_num, num2 - num1);
					break;
				case '*':
					pushLStack(&s_num, num1 * num2);
					break;
				case '/':
					if (num1 == 0) {
						printf("非法计算");
						goto restart;
					}
					pushLStack(&s_num, num2 / num1);
					break;
				default:
					break;
				}
			}
		}
		
		double ans;
		popLStack(&s_num, &ans);
		printf("%lf\n", ans);
	}
	return 0;
}

int priority(char s) {
	int ans = -1;
	switch (s)
	{
	case '+':
	case '-':
		ans = 1;
		break;
	case '*':
	case '/':
		ans = 2;
		break;
	case '(':
		ans = 3;
	default:
		break;
	}
	return ans;
}