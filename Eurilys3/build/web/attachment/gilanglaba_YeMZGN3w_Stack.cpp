//File : Stack.cc

#include "Stack.h"

int Stack::nLife = 0;
int Stack::nDeath = 0;

Stack::Stack() {

	data = new int(size);
	nLife++;
	topStack=0;
}

Stack::Stack(int item) {

	data = new int(item);
	nLife++;
	topStack=0;
}

Stack::~Stack(){
	nDeath++;
}

void Stack::Pop(int& item){
	if (IsEmpty()) {cout<<"Stack Kosong"<<endl;}
	else {
		topStack--;
		item = data[topStack];
	}
}

void Stack::Push(int item){
	data [topStack] = item;
	topStack++;
}