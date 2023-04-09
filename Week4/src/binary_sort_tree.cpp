#include "binary_sort_tree.h"
#include<iostream>
#include<stack>
#include<queue>
using namespace std;

Status BST_init(BinarySortTreePtr* t)
{
	*t = new BinarySortTree;
	(*t)->root == nullptr;
	return succeed;
}

Status BST_insert(BinarySortTreePtr t, ElemType e)
{
	if (t == nullptr) {
		return failed;
	}

	NodePtr s = (Node*)malloc(sizeof(Node));
	s->value = e;
	s->left = nullptr;
	s->right = nullptr;
	NodePtr p = t->root;
	if (p == nullptr) {
		t->root = s;
		return succeed;
	}
	while (p ->left != nullptr || p->right != nullptr) {
		if (p->value == e) {
			return failed;
		}
		if (p->value > e) {
			if (p->left == nullptr) {
				break;
			}
			p = p->left;
		}
		else 
		{
			if (p->right == nullptr) {
				break;
			}
			p = p->right;
		}
	}
	if (p->value > e) {
		p->left = s;
	}
	else
	{
		if (p->value < e) {
			p->right = s;
		}
		else
		{
			return failed;
		}
	}
	return succeed;
}

Status BST_delete(BinarySortTreePtr t, ElemType e)
{
	if (t == NULL) {
		return failed;
	}
	NodePtr* cur = &t->root;
	NodePtr n;
	while (*cur != NULL) {
		n = *cur;
		if (n->value > e) {
			cur = &(n->left);
		}
		else
		{
			if (n->value < e) {
				cur = &(n->right);
			}
			else
			{
				if (!n->left) {
					*cur = n->right;
					free(n);
					return succeed;
				}
				else
				{
					if (!n->right) {
						*cur = n->left;
						free(n);
						return succeed;
					}
					else
					{
						NodePtr s = n->left;
						while (s->right) {
							s = s->right;
						}
						n->value = s->value;
						free(s);
						return succeed;
					}
				}
			}
		}
	}
	return failed;
}

Status BST_search(BinarySortTreePtr t, ElemType e)
{
	if (t == NULL) {
		return NULL;
	}
	NodePtr n = t->root;
	while (n && n->value != e) {
		if (n->value > e) {
			n = n->left;
		}
		else
		{
			n = n->right;
		}
	}
	if (!n) {
		return failed;
	}
	return succeed;
}

Status BST_preorderI(BinarySortTreePtr t, void(*visit)(NodePtr))
{
	if (t == NULL || t->root == NULL) {
		return failed;
	}
	stack<NodePtr> s;
	NodePtr n = t->root;
	s.push(n);
	while (!s.empty()) {
		n = s.top();
		s.pop();
		visit(n);
		if (n->right) {
			s.push(n->right);
		}
		if (n->left) {
			s.push(n->left);
		}
	}
	return succeed;
}

Status BST_preorderR(NodePtr t, void(*visit)(NodePtr))
{
	if (t == NULL) {
		return failed;
	}
	visit(t);
	if (t->left) {
		BST_preorderR(t->left, visit);
	}
	if (t->right) {
		BST_preorderR(t->right, visit);
	}
	return succeed;
}

Status BST_inorderI(BinarySortTreePtr t, void(*visit)(NodePtr))
{
	if (t == NULL || t->root == NULL) {
		return failed;
	}
	stack<NodePtr> s;
	NodePtr n = t->root;
	while (!s.empty() || n) {
		if (n) {
			s.push(n);
			n = n->left;
		}
		else
		{
			n = s.top();
			s.pop();
			visit(n);
			n = n->right;
		}
	}
	return succeed;
}

Status BST_inorderR(NodePtr t, void(*visit)(NodePtr))
{
	if (t == NULL) {
		return failed;
	}
	if (t->left) {
		BST_preorderR(t->left, visit);
	}
	visit(t);
	if (t->right) {
		BST_preorderR(t->right, visit);
	}
	return succeed;
}

Status BST_postorderI(BinarySortTreePtr t, void(*visit)(NodePtr))
{
	if (t == NULL || t->root == NULL) {
		return failed;
	}
	NodePtr n = t->root;
	stack <NodePtr> s;
	stack<NodePtr>pre;
	while (!s.empty() || n) {
		if (n) {
			s.push(n);
			n = n->left;
		}
		else
		{
			n = s.top();
			if (pre.empty() || pre.top() != n) {
				
				//右边存在
				if (n->right) {
					pre.push(n);
					n = n->right;
				}
				else
				{
					visit(n);
					s.pop();
					n = NULL;
					
				}
			}
			else
			{
				//遍历过的，直接visit
				visit(n);
				s.pop();
				pre.pop();
				n = NULL;
			}
			
		}
		
	}
	return succeed;
}

Status BST_postorderR(NodePtr t, void(*visit)(NodePtr))
{
	if (t == NULL) {
		return failed;
	}
	if (t->left) {
		BST_postorderR(t->left, visit);
	}
	if (t->right) {
		BST_postorderR(t->right, visit);
	}

	visit(t);
	return succeed;
}

Status BST_levelOrder(BinarySortTreePtr t, void(*visit)(NodePtr))
{
	if (t == NULL || t->root == NULL) {
		return failed;
	}
	queue<NodePtr> q;
	NodePtr n = t->root;
	q.push(n);
	while (!q.empty()) {
		n = q.front();
		q.pop();
		visit(n);
		if (n->left) {
			q.push(n->left);
		}
		if (n->right) {
			q.push(n->right);
		}
	}
	return succeed;
}


