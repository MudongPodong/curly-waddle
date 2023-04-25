#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <pthread.h>

#define BUF_SIZE 100
#define MAX_CLNT 256

void * handle_clnt(void * arg);
void send_msg(char * msg, int len);
void error_handling(char * msg);

int clnt_cnt=0;             //The number of member 
int clnt_socks[MAX_CLNT];   //file descripter num 
char *clnt_name[MAX_CLNT];  //member name array
pthread_mutex_t mutx;

int main(int argc, char *argv[])
{
	char anystr[30]="[Press any word]\n";     //initial sentence
	int serv_sock, clnt_sock;
	struct sockaddr_in serv_adr, clnt_adr;
	int clnt_adr_sz;
	pthread_t t_id;
	if(argc!=2) {    //if the number of argv is not 2, system exit
		printf("Usage : %s <port>\n", argv[0]);
		exit(1);
	}
  
	pthread_mutex_init(&mutx, NULL);     //mutex intitialize
	serv_sock=socket(PF_INET, SOCK_STREAM, 0);     //make serv_socket

	memset(&serv_adr, 0, sizeof(serv_adr));      
	serv_adr.sin_family=AF_INET;     //address system = IPv4
	serv_adr.sin_addr.s_addr=htonl(INADDR_ANY);  // my computer IP
	serv_adr.sin_port=htons(atoi(argv[1]));    //port num
	
	if(bind(serv_sock, (struct sockaddr*) &serv_adr, sizeof(serv_adr))==-1)   //information binding
		error_handling("bind() error");
	if(listen(serv_sock, 5)==-1)    //wait until client request
		error_handling("listen() error");
	
	while(1)
	{
		clnt_adr_sz=sizeof(clnt_adr);
		clnt_sock=accept(serv_sock, (struct sockaddr*)&clnt_adr,&clnt_adr_sz);  //wait until client request
		
		pthread_mutex_lock(&mutx);  //only one thread(start)
		clnt_socks[clnt_cnt++]=clnt_sock;  //making client socknum
		write(clnt_socks[clnt_cnt-1],anystr,strlen(anystr));  //inform to member(initial)
		pthread_mutex_unlock(&mutx); //only one thread(end)
		
		pthread_create(&t_id, NULL, handle_clnt, (void*)&clnt_sock);   //make thread for communicatation with client
		pthread_detach(t_id);
		printf("Connected client num: %d \n", clnt_cnt-1);
	}
	close(serv_sock);
	return 0;
}
	
void * handle_clnt(void * arg)
{
	int clnt_sock=*((int*)arg);
	int str_len=0, i;
	char msg[BUF_SIZE];
	char tempmsg[BUF_SIZE];
	char* member_name="";
	
	while((str_len=read(clnt_sock, msg, sizeof(msg)))!=0)   //wait client msg
	{
		strcpy(tempmsg,msg);    
		member_name=strtok(msg," ");          //first argv(sender name)
		clnt_name[clnt_sock-4]=member_name;   //stored sender name in array 
		send_msg(tempmsg, str_len);
	}
	pthread_mutex_lock(&mutx);  //only one thread(start)
	for(i=0; i<clnt_cnt; i++)   // remove disconnected client
	{
		if(clnt_sock==clnt_socks[i])
		{
			while(i <clnt_cnt-1)
			{
				clnt_socks[i]=clnt_socks[i+1];
				clnt_name[i]=clnt_name[i+1];     //clnt_socks & clnt_name have same index
				  i++;

			}

			break;
		}
	}
	clnt_cnt--;   //decrease one client
	pthread_mutex_unlock(&mutx);  //only one thread(end)
	close(clnt_sock);
	return NULL;
}
void send_msg(char * msg, int len)   // send to all
{
	int i;
	char not_mem[30]="There's no such member\n";
	pthread_mutex_lock(&mutx);       //only one thread(start)
	char *member_name="";
	char tempmsg[BUF_SIZE];
	strcpy(tempmsg,msg);
	member_name=strtok(tempmsg," ");
	member_name=strtok(NULL," ");    //second argv('@'+"receiver name")
	member_name+=1;                  //send to receiver name(eliminate '@');
	int cnt=0;
	
	for(i=0;i<clnt_cnt;i++)
	{
		if(!strcmp(member_name,"[all]"))       //send to all
		{
			for(i=0; i<clnt_cnt; i++)
			{
				write(clnt_socks[i], msg, len);
			}
			break;
		}
		if(!strcmp(member_name,clnt_name[i]))  //send to specific person
		{
			write(clnt_socks[i], msg, len);
			break;
		}
		cnt++;
	}
	if(cnt==clnt_cnt)              //if no one wrote down, inform "There's no such member" to sender
	{
		member_name=strtok(msg," ");  //first argv(sender)
		for(i=0;i<clnt_cnt;i++){
			if(!strcmp(member_name,clnt_name[i])){
				write(clnt_socks[i],not_mem,strlen(not_mem));  //send to client with 'no member'msg
				break;
			}
		}
	}
	
	pthread_mutex_unlock(&mutx); //only one thread(end)
}
void error_handling(char * msg)   //error case
{
	fputs(msg, stderr);
	fputc('\n', stderr);
	exit(1);
}
