#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define SIZE 10
#define NUMBER_OF_THREADS 3

void *sorter(void *params);    
void *merger(void *params);   

int list[SIZE] = {44,65,34,12,89,32,1,3,54,20};

int result[SIZE];

typedef struct
{
    int s;
    int e;
} parameters;

int main (int argc, const char * argv[])
{
    int i;
    pthread_t threadP[NUMBER_OF_THREADS];

    parameters *data = (parameters *) malloc (sizeof(parameters));
    data->s = 0;
    data->e = (SIZE/2) - 1;
    pthread_create(&threadP[0], 0, sorter, data);

    data = (parameters *) malloc (sizeof(parameters));
    data->s = (SIZE/2);
    data->e = SIZE - 1;
    pthread_create(&threadP[1], 0, sorter, data);

    for (i = 0; i < NUMBER_OF_THREADS - 1; i++)
        pthread_join(threadP[i], NULL);

    data = (parameters *) malloc(sizeof(parameters));
    data->s = 0;
    data->e = (SIZE/2);
    pthread_create(&threadP[2], 0, merger, data);

    pthread_join(threadP[2], NULL);

    return 0;
}

void *sorter(void *params)
{
    parameters*  p = (parameters *)params;

    int begin = p->s;
    int end = p->e+1;
    int i,j,t,k;

for(i=begin; i< end; i++)
{
    for(j=i+1; j< end; j++)
    {

        if(list[i] > list[j])
        {
            t = list[j];
            list[j] = list[i];
            list[i] = t;

        }
    }
}

for(k = begin; k< end; k++){
    result[k]=list[k];
}
    pthread_exit(0);
}

void *merger(void *params)
{
    parameters* p = (parameters *)params;

    int begin = p->s;
    int end = p->e+1;

int a[5],b[5];
int m,n,o;
for(m=0;m<5;m++){
	a[m]=result[m];
}
for(n=5,o=0;n<10;n++,o++){
	b[o]=result[n];
}

int i=0,j=0,r=0;
	while(i<5 && j<5){
	if(a[i]<=b[j]){
	result[r]=a[i];
	i++;
	r++;
	}
	else{
	result[r]=b[j];
	j++;
	r++;
	}
	}
	while(i<5){
	result[r]=a[i];
	r++;
	i++;
	}
	while(j<5){
	result[r]=b[j];
	r++;
	j++;
}
int d;
printf("Sorted array:\n");
for(d=0; d<SIZE; d++)
{
    printf("%d ", result[d]);
}
printf("\n");
    pthread_exit(0);
}

