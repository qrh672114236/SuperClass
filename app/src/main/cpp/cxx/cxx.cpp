#include<jni.h>

//命名空间
using namespace std;

namespace std_a{
     int a=9;
     namespace std_b{
        int a=12;
     }
     struct User{
        int age;
     };
}
//结构体 和类很像 但是结构体无法继承


//别名引用 数据地址的另外别名
void swap(int &a,int &b){
    int c=0;
    c=a;
    a=b;
    b=c;
}
int main(){
int x=5;
int y=6;
swap(x,y);
// std::cout <<"this is c plus plus" >>std::endl;
//      cout <<"this is c plus plus" >>endl;
//  std_a::std_b::a
return 0;
}
