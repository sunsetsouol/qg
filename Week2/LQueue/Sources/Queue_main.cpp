#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"LQueue.h">


void get_type();
void get_int(void** data);
void get_float(void** data);
void get_char(void** data);
void show();

char type;
char datatype[30];

int main()
{
	get_type();
	show();
	return 0;
}

void LPrint(void* q);
void get_type()
{
	while (1)
	{
		type = '\0';
		printf("Please give me what kind of the data you want to keep:\n");
		printf("'i' means int, 'f' means float while 'c' means char!\n");
		scanf_s("%c", &type,1);
		while (getchar() != '\n');
		if (type == 'i' || type == 'f' || type == 'c')
			break;
		else
		{
			system("cls");
			printf("Please don't give me others!\n");
		}
	}
}
void get_data(void** data)
{
	if (type == 'i')
		get_int(data);
	else if (type == 'f')
		get_float(data);
	else if (type == 'c')
		get_char(data);
}

void get_int(void** data)
{
	printf("Please give me a int number:\n");

	int* p = (int*)malloc(sizeof(int));
	char s[50];
	int flag = 1;
	while (1)
	{
		gets_s(s);
		if (*s == '-')
		{
			if (strlen(s) < 6)
			{
				int i;
				for (i = 1; i < 6 && s[i] != '\0'; i++)
					if (s[i] < '0' || s[i]>'9')
				{
					flag = 0;
					break;
				}
				if (flag == 1)
				{
					*p = 0;
					for (i = 1; i < 6 && s[i] != '\0'; i++)
						*p = (*p) * 10 + s[i] - '0';
					(*p) *= -1;
					*data = (void*)p;
					return;
				}
			}
		}
		else if (strlen(s) < 5)
		{
			int i;
			for (i = 0; i < 5 && s[i] != '\0'; i++)
				if (s[i] < '0' || s[i]>'9')
				{
					flag = 0;
					break;
				}
			if (flag == 1)
			{
				*p = 0;
				for (i = 0; i < 5 && s[i] != '\0'; i++)
					*p = (*p) * 10 + s[i] - '0';
				*data = (void*)p;
				return;
			}
		}
		flag = 1;
		printf("Please give me a right int number which is bigger than -10000 and smaller than 10000;\n");
	}
}

void get_float(void** data)
{
	printf("Please give me a float number:\n");
	char input[100];
	float* p = (float*)malloc(sizeof(float));
	do {
		if (fgets(input, sizeof(input), stdin) == nullptr) {
			printf("Please give me a right int number which is bigger than -10000.00 and smaller than 10000.00;\n");
			exit(EXIT_FAILURE);
		}
		if (sscanf_s(input, "%f", p) != 1) {
			printf("Error: input was not valid.\n");
			*input = 0;
			continue;
		}

		if (*p < -10000.00 || *p > 10000.00) {
			printf("Error: input must be between 0 and 100.\n");
			*input = 0;
			continue;
		}
	} while (*p < -10000.00 || *p > 10000.00);
	*data = p;
}


void get_char(void** data)
{
	printf("Please give me a char:\n");
	char* p = (char*)malloc(sizeof(char));
	*p = '0';
	scanf_s("%c", p);
	while (getchar() != '\n');
	*data = (void*)(p);
}
void show()
{
	int length;
	LQueue* Q = (LQueue*)malloc(sizeof(LQueue));
	void* data = nullptr;
	if (type == 'i')
		data = (void*)malloc(sizeof(int));
	else if (type == 'f')
		data = (void*)malloc(sizeof(float));
	else if (type == 'c')
		data = (void*)malloc(sizeof(char));
	void* addData;
	int flag;
	InitLQueue(Q);
	while (1)
	{
		while (1)
		{
			flag = 0;
			printf("You can do these function:");
			printf("1.Check whether the queue is empty or not;\n");	//检查是否空
			printf("2.Get the number of the queue head;\n");		//获取队列头
			printf("3.Add a number into the queue;\n");				//入队
			printf("4.Get a number out of the queue;\n");			//出队
			printf("5.Read all the node of the queue;\n");			//读取
			printf("6.Get the length of the queue;\n");				//得到队列长度
			printf("7.Delete the queue and quit;\n");				//删除队列退出
			printf("Now please give me the number to finish the function:\n");
			scanf_s("%d", &flag);
			while (getchar() != '\n');
			if (flag < 1 || flag>7)
			{
				system("cls");
				printf("Please give me the right number!");
			}
			else
				break;
		}
		switch (flag)
		{
		case(1):
			if (IsEmptyLQueue(Q) == TRUE)
				printf("The queue is empty now!You should add something!\n");
			else
				printf("The queue is not empty now.\n");
			break;
		case(2):
			if (GetHeadLQueue(Q, &data) == TRUE)
				LPrint(data);
			else
				printf("The queue is empty now!You should add something!\n");
			break;
		case(3):
			if (type == 'i')
				get_int(&addData);
			else if (type == 'f')
				get_float(&addData);
			else
				get_char(&addData);
			if (EnLQueue(Q, addData) == TRUE)
				printf("Printf we have add it into the queue!\n");
			else
			{
				printf("The room is full!!!\n");
				exit(-1);
			}
			break;
		case(4):
			if (DeLQueue(Q) == TRUE)
				printf("We have get the node out of the queue.\n");
			else
				printf("The queue is empty now!\n");
		case(5):
			TraverseLQueue(Q, LPrint);
			break;
		case(6):
			if (LengthLQueue(Q, &length) == TRUE)
				printf("The length of the queue is %d.\n", length);
			else
			{
				printf("There is some wrong in the queue!!!\n");
				exit(-1);
			}
			break;
		case(7):
			DestoryLQueue(Q);
			return;
		}
	}

}