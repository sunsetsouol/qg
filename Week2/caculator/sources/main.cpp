#include "stack.h"
#include<stdio.h>
#include<stdlib.h>

int priority(char s);
int main() {
	char exp[200];
	while (1) {
	restart:
		*exp = 0;
		printf("��������ȷ���ʽ\n");
		if (fgets(exp, sizeof(exp), stdin) == nullptr) {
			printf("��������ȷ���ʽ\n");
			exit(EXIT_FAILURE);
		}
		if (exp[0] == '\n' || exp[0] == 0) {
			printf("��������ȷ���ʽ\n");
			goto restart;
		}
		//��ʼ��
		LinkStack* s_num = NULL;
		LinkStack* s_opt = NULL;
		initLStack(&s_num);
		initLStack(&s_opt);
		int i = 0;
		double tem = 0;
		double num1 = 0, num2 = 0;
		int cnt = 0;
		if (exp[0] == '-') {
			pushLStack(&s_num, 0);
		}
		while (exp[i] != 0 && exp[i]!='\n') {
			//���������
			if ((exp[i] >= '0' && exp[i] <= '9') || exp[i] == '.') {
				tem = atof(&exp[i]);
				pushLStack(&s_num, tem);
				while ((exp[i] >= '0' && exp[i] <= '9') || exp[i] == '.') {
					i++;
					if (exp[i] == '.') {
						cnt++;
					}
				}
				if (cnt > 1) {
					printf("���ʽ����\n");
					goto restart;
				}
				else
				{
					cnt = 0;
				}
			}
			else
			{
				//����Ƿ���
				if (exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/' || exp[i] == '(' || exp[i] == ')')
				{
					//����ǿ�ֱ����ջ
					if (isEmptyLStack(&s_opt)) 
					{
						if (exp[i] == ')') {
							printf("��������");
							goto restart;
						}
						if (exp[i] == '(' && exp[i+1] == '-') {
							pushLStack(&s_opt, exp[i]);
							pushLStack(&s_num, 0);
						}
						else
						{
							pushLStack(&s_opt, exp[i]);
						}
						i++;
					}
					//�ǿ�
					else 
					{
						//ȡֵ����
						double top = 0;
						getTopLStack(&s_opt, &top);
						while(exp[i]==')' || (priority((char)top) >= priority(exp[i]) && top!='('))
						{
							//��������
							if (s_opt->count == 0) {
								break;
							}
							popLStack(&s_opt, &top);
							if (exp[i] == ')' && (char)top == '(')
							{
								i++;
								continue;
							}
							//����
							if (!popLStack(&s_num, &num1) || !popLStack(&s_num, &num2))
							{
								printf("���ʽ����");
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
									printf("�Ƿ�����");
									goto restart;
								}
								pushLStack(&s_num, num2 / num1);
								break;
							default:
								break;
							}
							getTopLStack(&s_opt, &top);

						}
						if (exp[i] != '\n' && exp[i] != 0) {
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
									pushLStack(&s_num, 0);
								}
							}
							i++;
						}
					}
				}
				else {
					printf("���ʽ����\n");
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
					continue;
				}
				else
				{
					printf("���ʽ����");
					goto restart;
				}
			}
			
			if (!popLStack(&s_num, &num1) || !popLStack(&s_num, &num2) )
			{
				printf("���ʽ����");
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
						printf("�Ƿ�����");
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