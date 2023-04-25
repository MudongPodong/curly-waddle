#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> 
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>
	
#define BUF_SIZE 100
#define NAME_SIZE 20
	
void * send_msg(void * arg);
void * recv_msg(void * arg);
void error_handling(char * msg);
	
char name[NAME_SIZE]="[DEFAULT]";
char msg[BUF_SIZE];
	
int main(int argc, char *argv[])
{
	int sock;
	struct sockaddr_in serv_addr;
	pthread_t snd_thread, rcv_thread;
	void * thread_return;
	if(argc!=4) {     //if the number of argv is not 4, system exit
		printf("Usage : %s <IP> <port> <name>\n", argv[0]);  
		exit(1);
	 }
	
	sprintf(name, "[%s]", argv[3]);    //save member name
	sock=socket(PF_INET, SOCK_STREAM, 0);
	
	memset(&serv_addr, 0, sizeof(serv_addr));
	serv_addr.sin_family=AF_INET;     //address system = IPv4
	serv_addr.sin_addr.s_addr=inet_addr(argv[1]);  // my computer IP
	serv_addr.sin_port=htons(atoi(argv[2]));    //port num
	  
	if(connect(sock, (struct sockaddr*)&serv_addr, sizeof(serv_addr))==-1)
		error_handling("connect() error");
	pthread_create(&snd_thread, NULL, send_msg, (void*)&sock);   //thread making
	pthread_create(&rcv_thread, NULL, recv_msg, (void*)&sock);   //thread making
	pthread_join(snd_thread, &thread_return);   //main thread wait until snd_thread  finish
	pthread_join(rcv_thread, &thread_return);   //main thread wait until snd_thread  finish
	close(sock);  
	return 0;
}
	
void * send_msg(void * arg)   // send thread main
{
	int sock=*((int*)arg);
	char name_msg[NAME_SIZE+BUF_SIZE];
	int cnt=0;
	while(1) 
	{
		fgets(msg, BUF_SIZE, stdin);   //wait point
		if(!strcmp(msg,"q\n")||!strcmp(msg,"Q\n"))   //if sender input 'q' or 'Q' client connect disconnect 
		{
			close(sock);
			exit(0);
		}
		sprintf(name_msg,"%s %s", name, msg);    //name + sending msg
		write(sock, name_msg, strlen(name_msg));
	}
	
	return NULL;
}
	
void * recv_msg(void * arg)   // read thread main
{
	int sock=*((int*)arg);
	char name_msg[NAME_SIZE+BUF_SIZE];
	int str_len;
	while(1)
	{
		str_len=read(sock, name_msg, NAME_SIZE+BUF_SIZE-1);  //wait point
		if(str_len==-1) 
			return (void*)-1;
		name_msg[str_len]=0;
		fputs(name_msg, stdout);
	}
	return NULL;
}
	
void error_handling(char *msg)  //error case
{
	fputs(msg, stderr);
	fputc('\n', stderr);
	exit(1);
}
